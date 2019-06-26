package chess.conroller;

import java.util.HashMap;
import java.util.Map;

/**
 * 응답 상태를 추상화한 타입
 * OK: 요청이 정상적으로 처리됨
 * FAIL: 클라이언트측 사유에 의한 요청 처리 실패
 * ERROR: 서버측 사유에 의한 요청 처리 실패
 */
public enum ResultState {
    OK("ok"), FAIL("fail"), ERROR("error");

    private String resultString;

    ResultState(String string) {
        this.resultString = string;
    }

    public String getResultString() {
        return resultString;
    }

    public Map<String, Object> createResMap(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", resultString);
        map.put("message", message);
        return map;
    }
}
