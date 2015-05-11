SUMMARY = "Quagga Routing Daemon"
LICENSE = "GPL-2.0 & LGPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=81bcece21748c91ba9992349a91ec11d\
                    file://COPYING.LIB;md5=01ef24401ded36cd8e5d18bfe947240c"

DEPENDS = "halonutils halon-ovsdb ncurses perl-native openssl"

# the "ip" command from busybox is not sufficient (flush by protocol flushes all routes)
RDEPENDS_${PN} += "iproute2"

SRC_URI = "git://git.openhalon.io/openhalon/quagga;protocol=http \
    file://zebra.service file://bgpd.service \
"

CFLAGS += "-DHALON"
SRCREV="${AUTOREV}"
# For debugging/development purposes
EXTERNALSRC_BUILD="${S}/build"

S = "${WORKDIR}/git"

inherit autotools pkgconfig systemd

EXTRA_OECONF = "--disable-doc --disable-ripd \
 --disable-ripngd --disable-ospfd --disable-ospf6d --disable-babeld \
 --disable-watchquagga --disable-opaque-lsa --disable-ospfapi \
 --disable-ospfclient --disable-ospf-te --disable-rtadv --disable-rusage \
 --enable-user=root --enable-group=root \
 --enable-ovsdb \
"

do_install_append() {
     install -d ${D}${systemd_unitdir}/system
     install -m 0644 ${WORKDIR}/zebra.service ${D}${systemd_unitdir}/system/
     install -m 0644 ${WORKDIR}/bgpd.service ${D}${systemd_unitdir}/system/
     # Remove non-ovs configuration files
     rm -Rf ${D}${sysconfdir}*
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "zebra.service bgpd.service"


