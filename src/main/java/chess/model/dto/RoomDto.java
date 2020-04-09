package chess.model.dto;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RoomDto {

    private final Map<Integer, String> rooms;

    public RoomDto(Map<Integer, String> rooms) {
        this.rooms = rooms.keySet().stream()
            .sorted(Comparator.comparing(Integer::intValue))
            .collect(Collectors.toMap(room -> room, room -> "# " + room + " - " + rooms.get(room),
                (oldValue, newValue) -> oldValue, TreeMap::new));
    }

    public Map<Integer, String> getRooms() {
        return rooms;
    }
}
