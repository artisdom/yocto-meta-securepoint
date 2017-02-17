# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)
SUMMARY = "Library to make writing a vnc server easy"
DESCRIPTION = "\
LibVNCServer makes writing a VNC server (or more correctly, a program\
exporting a framebuffer via the Remote Frame Buffer protocol) easy."
HOMEPAGE = "https://github.com/LibVNC/libvncserver"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=361b6b837cad26c6900a926b62aada5f"
SECTION = "system/libraries"
DEPENDS = "openssl libpng jpeg"
PR = "r0"

#${SOURCEFORGE_MIRROR}/libvncserver/libvncserver/${PV}/LibVNCServer-${PV}.tar.gz
SRC_URI = " \
    https://github.com/LibVNC/libvncserver/archive/LibVNCServer-${PV}.tar.gz \
    file://libvncserver-nodh.patch \
    file://no_hixie.patch \
"

SRC_URI[md5sum] = "7f06104d5c009813e95142932c4ddb06"
SRC_URI[sha256sum] = "193d630372722a532136fd25c5326b2ca1a636cbb8bf9bb115ef869c804d2894"
S = "${WORKDIR}/libvncserver-LibVNCServer-${PV}"

inherit autotools-brokensep pkgconfig

EXTRA_OECONF += "\
    --without-x11vnc \
    --with-jpeg=${STAGING_LIBDIR}/.. \
    --with-png=${STAGING_LIBDIR}/.. \
    --without-x \
    --without-gnutls --without-gcrypt --without-crypt \
"


do_configure_prepend () {
    cd  ${S}
    sed -i -e "s/^SUBDIRS.*/SUBDIRS=libvncserver/" Makefile.am
    sed -i -e "s/AC_PROG_LIBTOOL/LT_INIT/" configure.ac
}

FILES_${PN}-dev += " /usr/bin/libvncserver-config"
INSANE_SKIP_${PN} += " rpaths"
