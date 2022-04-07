package chess.util;

import static org.assertj.core.api.Assertions.*;

import java.util.LinkedHashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JsonUtilTest {
    @Test
    @DisplayName("자바의 Map<String, String> 타입을 Json 문자열로 직렬화 한다.")
    void serialize() {
        LinkedHashMap<String, String> jsonData = new LinkedHashMap<>();
        jsonData.put("name", "alex");
        jsonData.put("part", "backend");
        assertThat(JsonUtil.serialize(jsonData)).isEqualTo("{\"name\":\"alex\",\"part\":\"backend\"}");
    }
}