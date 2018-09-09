package com.sizake.ebank;


import org.junit.Test;
import parser.JsonTool;
import parser.XmlAndJsonTool;
import parser.XmlTool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SizkeTest {
    @Test
    public void testXmlParser1() {
        XmlTool.create();
    }

    @Test
    public void testXmlParser2() {
        List friendList = new ArrayList();
        friendList.add("sizake");
        friendList.add("kafka");
        XmlTool.create(friendList);
    }

    @Test
    public void testXmlParser3() {
        XmlTool.createWithNameSpace();
    }

    @Test
    public void testXmlParser4() throws IOException {
        String result = XmlAndJsonTool.xmlToJson("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<errors>\n" +
                "  <error>\n" +
                "    <status>400</status>\n" +
                "    <message>The field 'quantity' is invalid.</message>\n" +
                "    <details>\n" +
                "      <invalid_reason>The quantity specified is greater than the quantity of the product that is available to ship.</invalid_reason>\n" +
                "      <available_quantity>0</available_quantity>\n" +
                "      <order_product_id>12525</order_product_id>\n" +
                "    </details>\n" +
                "  </error>\n" +
                "</errors>");

        System.out.println(result);
    }


    @Test
    public void testJsonParser1() {
        JsonTool.create();
    }

    @Test
    public void testJsonParser2() throws IOException {
        Map map = XmlAndJsonTool.jsonToMap("{\"state\":{\"capital\":\"Denver\",\"majorCities\":[\"Denver\",\"Colorado Springs\",\"Fort Collins\"],\"others\":{\"language\":\"english\",\"food\":\"beef\"},\"contacts\":[{\"FirstName\":\"A\",\"LastName\":\"B\"},{\"FirstName\":\"D\",\"LastName\":\"E\"},{\"FirstName\":\"G\",\"LastName\":\"H\"}],\"lists\":[{\"name\":\"sizake\",\"order\":\"1\"},{\"name\":\"kafka\",\"order\":\"2\"}]}}\n");
        System.out.println(map);
    }

}
