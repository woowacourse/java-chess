package dto;

import java.util.Map;

public class PieceDto {
    private final Map<String, String> map;

    public PieceDto(Map<String, String> map) {
        this.map = map;
    }

    public Map<String, String> getMap() {
        return map;
    }
}
