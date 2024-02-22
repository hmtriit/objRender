package com.htm.android_3d.engine.util.xml;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

public class XmlParser {

    /**
     * Parses an XML file and returns the root node.
     * @param in - the input stream of the XML file
     * @return The root node of the XML file
     */
    public static XmlNode parse(InputStream in)  {
        try {
            XmlPullParser xpp = Xml.newPullParser();
            xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xpp.setInput(in, null);
            int eventType = xpp.getEventType();
            if (eventType == XmlPullParser.START_DOCUMENT) {
                XmlNode parent = new XmlNode("xml");
                loadNode(xpp, parent);
                return parent.getChild("COLLADA");
            }
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * Loads a node from the XML file and adds it to the parent node.
     * @param xpp - the XML parser
     * @param parentNode - the parent node
     * @throws XmlPullParserException
     * @throws IOException
     */
    private static void loadNode(XmlPullParser xpp, XmlNode parentNode) throws XmlPullParserException, IOException {
        int eventType = xpp.next();
        while(eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                XmlNode childNode = new XmlNode(xpp.getName());
                for (int i=0; i<xpp.getAttributeCount(); i++){
                    childNode.addAttribute(xpp.getAttributeName(i), xpp.getAttributeValue(i));
                }
                parentNode.addChild(childNode);
                loadNode(xpp, childNode);
            } else if (eventType == XmlPullParser.END_TAG) {
                return;
            } else if (eventType == XmlPullParser.TEXT) {
                parentNode.setData(xpp.getText());
            }
            eventType = xpp.next();
        }
    }

}
