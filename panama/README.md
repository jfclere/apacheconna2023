The slides are at https://www.slideshare.net/jfclere/panamapdf
```
git checkout https://github.com/jfclere/apacheconna2023.git
git checkout https://github.com/apache/tomcat.git
```

The demos are:
## /home/${USER}/apacheconna2023/panama/openssl (after slide 11).
```
make
```
show Makefile and java code.
```
openssl version
```
## /home/${USER}/apacheconna2023/panama/upcall (after slide 15).
```
bash java.sh
```
## /home/${USER}/tomcat/modules/openssl-foreign (after slide 27)
openssl-tomcat.conf (changed to prevent destroying running tomcat).

build tomcat (ant) and modules/openssl-foreign (maven) and copy the jar to output/build/lib (check the README from Tomcat).

## /home/${USER}/tomcat/output/build (after slide  47)
```
export PATH=/home/${USER}/JAVA/jdk-21/bin:$PATH
curl -v -k https://localhost:8443/
/etc/pki/CA/cacert.pem
curl -v --cacert /etc/pki/CA/cacert.pem https://localhost:8443/
``
