# Copyright (C) 2015 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "pam_krb5 is designed to allow smooth integration of Kerberos 5 passwordchecking with applications built using PAM. It also supports session-specific ticket files (which are neater), Kerberos IV ticket file grabbing, and AFS token-grabbing."
HOMEPAGE = "https://git.fedorahosted.org/cgit/pam_krb5.git/"
LICENSE = "MIT"
SECTION = "base"
DEPENDS = "krb5 libpam"
PR = "r0"

SRC_URI = "git://git.fedorahosted.org/git/pam_krb5.git;protocol=https;tag=pam_krb5-${PV}"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=5288d1454395f7ed938af11db77eaf91"

#libPAM installs under /lib not /usr/lib
EXTRA_OECONF = "--libdir=/lib"

inherit gettext autotools

S = "${WORKDIR}/git"

FILES_${PN}-dbg += "/lib/security/pam_krb5/.debug /lib/security/.debug"
FILES_${PN} += "/lib/security/pam_krb5/pam_krb5_cchelper /lib/security"