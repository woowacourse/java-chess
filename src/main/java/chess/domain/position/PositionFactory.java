package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class PositionFactory {
    
    public static final String POSITION_NOT_IN_BOARD_ERROR_MESSAGE = "[POSITION ERROR] 보드에 존재하지 않는 좌표입니다.";
    private static final Map<String, Position> POSITION_MAP = new TreeMap<>();
    
    static {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                POSITION_MAP.put(file.getLabel() + rank.getLabel(), Position.from(file, rank));
            }
        }
    }
    
    private PositionFactory() {
    }
    
    public static Position from(String position) {
        if (!POSITION_MAP.containsKey(position)) {
            throw new IllegalArgumentException(POSITION_NOT_IN_BOARD_ERROR_MESSAGE);
        }
        return POSITION_MAP.get(position);
    }
    
    public static List<Position> getPositions() {
        return new ArrayList<>(POSITION_MAP.values());
    }
}
