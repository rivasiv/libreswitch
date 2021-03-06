DESCRIPTION = "Network traffic statistics utility for all network interface \
cards (NICs)"
HOMEPAGE = "http://nicstat.sourceforge.net"
LICENSE = "Artistic-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=b4a94da2a1f918b217ef5156634fc9e0"

SRC_URI = "${SOURCEFORGE_MIRROR}/projects/nicstat/files/nicstat-${PV}.tar.gz"
SRC_URI[md5sum] = "9a0b87bbc670c1e738e5b40c7afd184d"
SRC_URI[sha256sum] = "c4cc33f8838f4523f27c3d7584eedbe59f4c587f0821612f5ac2201adc18b367"

do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} -o nicstat nicstat.c
}
do_install() {
    install -d ${D}/${bindir}/
    install -m 0755 ${S}/nicstat ${D}${bindir}/
}
