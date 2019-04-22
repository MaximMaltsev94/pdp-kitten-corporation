package pdp.kitten.corporation.repository.util;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.types.ObjectId;

public class DocumentUtils {

    public static void putIfNotNull(Document command, String key, Object value) {
        if (value != null) {
            command.put(key, value);
        }
    }

    public static void putObjectId(Document command, String key, String hexValue) {
        if(StringUtils.isNotBlank(hexValue)) {
            command.put(key, new ObjectId(hexValue));
        }
    }
}