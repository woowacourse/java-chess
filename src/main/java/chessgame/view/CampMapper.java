package chessgame.view;

import chessgame.domain.chessgame.Camp;

import java.util.HashMap;
import java.util.Map;

public class CampMapper {

    private static final Map<Camp, String> mapper = new HashMap<>();

    static {
        mapper.put(Camp.WHITE, "화이트");
        mapper.put(Camp.BLACK, "블랙");
    }

    public static String getTarget(final Camp targetCamp) {
        return mapper.keySet()
                     .stream()
                     .filter(camp -> camp.equals(targetCamp))
                     .map(mapper::get)
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 팀입니다."));
    }
}
