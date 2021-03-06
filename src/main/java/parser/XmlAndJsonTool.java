package parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.util.Map;

public class XmlAndJsonTool {
    public static String xmlToJson(String xml) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        Map map = xmlMapper.readValue(xml, Map.class);//xml to json

        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.writeValueAsString(map);//map to json
    }

    public static Map xmlToMap(String xml) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(xml, Map.class);
    }

    public static Map jsonToMap(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Map.class);
    }


}
