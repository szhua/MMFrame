package com.mengma.asynchttp;

/**
 * Created by Administrator on 2015/10/31 0031.
 */

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JSON Mapper
 *
 * @author jade (originally)
 * @author zhe.yangz imported.
 */
public class JsonUtil {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final JsonFactory jf = new JsonFactory();

    static {
        jf.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        jf.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    private static final ObjectMapper objectMapper = new ObjectMapper(jf);

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // SerializationConfig sf = objectMapper.getSerializationConfig();
        // objectMapper.configure(sf.with(new SimpleDateFormat(DATE_FORMAT)), true);
        // DeserializationConfig df = objectMapper.getDeserializationConfig();
        //	objectMapper.setDeserializationConfig(df.with(new SimpleDateFormat(DATE_FORMAT)));
    }

    public static <T> T json2pojo(String jsonAsString, Class<T> pojoClass) throws JsonMappingException,
            JsonParseException, IOException {
        return objectMapper.readValue(jsonAsString, pojoClass);
    }

    public static Map<?, ?> json2map(String jsonAsString) throws JsonMappingException, JsonParseException, IOException {
        return objectMapper.readValue(jsonAsString, Map.class);
    }

    public static Map<?, ?> json2map(InputStream istream) throws JsonMappingException, JsonParseException, IOException {
        return objectMapper.readValue(istream, Map.class);
    }

    public static JsonNode json2node(String jsonAsString) throws JsonProcessingException, IOException {
        return objectMapper.readTree(jsonAsString);
    }

    public static JsonNode json2node(InputStream istream) throws JsonProcessingException, IOException {
        return objectMapper.readTree(istream);
    }

    public static JsonNode json2node(Reader reader) throws JsonProcessingException, IOException {
        return objectMapper.readTree(reader);
    }

    public static <T> T json2value(Reader reader, Class<T> type) throws IOException, JsonParseException,
            JsonMappingException {
        return objectMapper.readValue(reader, type);
    }

    public static Map<?, ?> node2map(JsonNode json) throws JsonProcessingException, IOException {
        if (json == null) {
            return null;
        }
        JsonParser jp = null;
        try {
            jp = json.traverse();
            return objectMapper.readValue(jp, Map.class);
        } finally {
            if (jp != null) {
                try {
                    jp.close();
                } catch (IOException ioe) {
                }
            }
        }
    }

    public static <T> T node2pojo(JsonNode json, Class<T> pojoClass) throws JsonProcessingException, IOException {
        if (json == null) {
            return null;
        }
        JsonParser jp = null;
        try {
            jp = json.traverse();
            return objectMapper.readValue(jp, pojoClass);
        } finally {
            if (jp != null) {
                try {
                    jp.close();
                } catch (IOException ioe) {
                }
            }
        }
    }

    public static <T> T node2pojo(JsonNode json, TypeReference<T> type) throws JsonProcessingException, IOException {
        if (json == null) {
            return null;
        }
        JsonParser jp = null;
        try {
            jp = json.traverse();
            return objectMapper.readValue(jp, type);
        } finally {
            if (jp != null) {
                try {
                    jp.close();
                } catch (IOException ioe) {
                }
            }
        }
    }

    public static <T> List<T> node2pojoList(JsonNode json, Class clazz) throws IOException {
        if (json == null) {
            return null;
        }
        JsonParser jp = null;
        TypeFactory t = objectMapper.getTypeFactory();

        try {
            jp = json.traverse();
            return objectMapper.readValue(jp, t.constructCollectionType(ArrayList.class, clazz));
        } finally {
            if (jp != null) {
                try {
                    jp.close();
                } catch (IOException ioe) {
                }
            }
        }
    }

    public static String pojo2json(Object pojo) throws JsonGenerationException, JsonMappingException, IOException {
        final StringWriter sw = new StringWriter();
        JsonGenerator jg = null;
        try {
            jg = jf.createGenerator(sw);
            objectMapper.writeValue(jg, pojo);
            return sw.toString();
        } finally {
            if (jg != null) {
                try {
                    jg.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static String node2json(JsonNode node) throws JsonProcessingException, IOException {
        final StringWriter sw = new StringWriter();
        JsonGenerator jg = null;
        try {
            jg = jf.createGenerator(sw);
            objectMapper.writeTree(jg, node);
            return sw.toString();
        } finally {
            if (jg != null) {
                try {
                    jg.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static void node2json(JsonNode node, Writer w) throws JsonGenerationException, JsonMappingException,
            IOException {
        JsonGenerator jg = null;
        try {
            jg = jf.createGenerator(w);
            objectMapper.writeTree(jg, node);
        } finally {
            if (jg != null) {
                try {
                    jg.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static ObjectNode createObjectNode() {
        return objectMapper.createObjectNode();
    }

    public static ArrayNode createArrayNode() {
        return objectMapper.createArrayNode();
    }

    public static JsonNode parser2node(JsonParser jp) throws JsonProcessingException, IOException {
        return objectMapper.readTree(jp);
    }
}