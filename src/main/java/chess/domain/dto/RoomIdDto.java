package chess.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class RoomIdDto {

    private List<String> roomIDs;

    public RoomIdDto(List<String> roomIDs) {
        this.roomIDs = new ArrayList<>(roomIDs);
    }

    public List<String> getRoomIDs() {
        return roomIDs;
    }
}
