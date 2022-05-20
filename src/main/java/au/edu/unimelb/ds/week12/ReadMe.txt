You can use any linux terminal or windows powershell to access the keytool.

#Run this command to create a certificate and private key:
keytool -genkey -keyalg RSA -alias selfsigned -keystore keystore.jks -storepass password -validity 360 -keysize 2048

#Compile:
javac *.java

#To start the server:
java -Djavax.net.ssl.keyStore=keystore.jks -Djavax.net.ssl.keyStorePassword=password -Djavax.net.ssl.trustStore=keystore.jks -Djavax.net.ssl.trustStorePassword=password EchoServer

#To start the client:
java -Djavax.net.ssl.keyStore=keystore.jks -Djavax.net.ssl.keyStorePassword=password -Djavax.net.ssl.trustStore=keystore.jks -Djavax.net.ssl.trustStorePassword=password EchoClient


Note: You can also use IDE to run both client and Server, you have to pass above parameters as VM arguments in settings

#To read/ get info about keystore:
keytool -list -v -keystore keystore.jks