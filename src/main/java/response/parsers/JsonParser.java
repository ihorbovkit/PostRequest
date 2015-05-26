package response.parsers;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JsonParser {

	private ObjectMapper objectMapper;

	public JsonParser() {
		setObjectMapper();
	}

	private void setObjectMapper() {
		MappingJsonFactory jsonFactory = new MappingJsonFactory();
		jsonFactory.enable(Feature.ALLOW_COMMENTS);
		jsonFactory.enable(Feature.ALLOW_SINGLE_QUOTES);
		jsonFactory.enable(Feature.AUTO_CLOSE_SOURCE);
		objectMapper = new ObjectMapper(jsonFactory);
	}

	public <T> T readJsonToObject(Class<T> clazz, InputStream input) {
		T parsedDTOList = null;
		try {
			/*
			 * objectMapper
			 * .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			 */
			objectMapper
					.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			TypeFactory typeFactory = TypeFactory.defaultInstance();
			parsedDTOList = objectMapper.readValue(input,
					typeFactory.constructType(clazz));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parsedDTOList;
	}

}
