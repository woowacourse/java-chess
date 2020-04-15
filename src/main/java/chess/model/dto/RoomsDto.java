package chess.model.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RoomsDto {

    private final Map<Integer, String> rooms;

    public RoomsDto(Map<Integer, String> rooms) {
        this.rooms = Collections.unmodifiableMap(new HashMap<>(rooms));
    }

    public Map<Integer, String> getRooms() {
        return rooms;
    }
}
