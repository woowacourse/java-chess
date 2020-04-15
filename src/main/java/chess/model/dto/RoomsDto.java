package chess.model.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RoomsDto {

    private final Map<Integer, String> rooms;

    public RoomsDto(Map<Integer, String> rooms) {
        this.rooms = Collections.unmodifiableMap(new HashMap<>(rooms));
        /*this.rooms = rooms.keySet().stream()
            .sorted(Comparator.comparing(Integer::intValue))
            .collect(Collectors.toMap(room -> room, room -> "# " + room + " - " + rooms.get(room),
                (oldValue, newValue) -> oldValue, TreeMap::new));*/
    }

    public Map<Integer, String> getRooms() {
        return rooms;
    }
}
