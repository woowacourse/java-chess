package chess.dto;

import java.util.ArrayList;
import java.util.List;

public class RoomsDTO {
    List<RoomDTO> roomDTOList;

    public RoomsDTO(List<RoomDTO> roomDTOList) {
        this.roomDTOList = new ArrayList<>(roomDTOList);
    }

    public List<RoomDTO> getRoomDTOList() {
        return roomDTOList;
    }
}
