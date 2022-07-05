package com.iti.mediation;

import com.chilkatsoft.CkAsn;
import com.chilkatsoft.CkXml;
import org.w3c.dom.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ChilkatExample {

    static {
        try {
            System.load("C:\\Program Files\\Java\\jdk1.8.0_311\\bin\\chilkat.dll");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[]){
        CkAsn asn = new CkAsn();

        boolean success;

        //  Begin with loading ASN.1 from a binary DER/BER format file.
        success = asn.LoadBinaryFile("src\\ASNcdrs\\upstream_server1_New_CDR.ber");
        if (success != true) {
            System.out.println("error with load: "+asn.lastErrorText());
            return;
        }

        //  Convert ASN.1 to XML:
        String strXml = asn.asnToXml();
        if (asn.get_LastMethodSuccess() != true) {
            System.out.println(asn.lastErrorText());
            return;
        }

        //  The XML returned by AsnToXml will be compact and not pretty-formatted.
        //  Use Chilkat XML to format the XML better:
        CkXml xml = new CkXml();
        success = xml.LoadXml(strXml);
        //  Assuming success for this example..
        //  This is formatted better for human viewing:
        System.out.println(xml.getXml());

        try {
            File myObj = new File("src\\XMLcdrs\\filename.xml");
            if (myObj.createNewFile()) {
                FileWriter myWriter = new FileWriter("src\\XMLcdrs\\filename.xml");
                myWriter.write(xml.getXml());
                myWriter.close();
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //C:\Users\moham\.jdks\openjdk-18\bin

        //  Now get the ASN.1 in base64 format.  Any encoding supported
        //  by Chilkat can be passed, such as "hex", "uu", "quoted-printable", "base32", "modbase64", etc.
        String strBase64 = asn.getEncodedDer("base64");

        //  Load the ASN.1 from XML:
        CkAsn asn2 = new CkAsn();
        success = asn2.LoadAsnXml(xml.getXml());
        if (success != true) {
            System.out.println(asn2.lastErrorText());
            return;
        }

        //  Load the ASN.1 from an encoded string, such as base64:
        CkAsn asn3 = new CkAsn();
        success = asn3.LoadEncoded(strBase64, "base64");
        if (success != true) {
            System.out.println(asn3.lastErrorText());
            return;
        }
    }
}
