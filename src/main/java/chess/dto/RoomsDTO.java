package chess.dto;

import chess.domain.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomsDTO {
    List<RoomDTO> roomDTOList;

    private RoomsDTO(List<RoomDTO> roomDTOList) {
        this.roomDTOList = new ArrayList<>(roomDTOList);
    }

    public List<RoomDTO> getRoomDTOList() {
        return roomDTOList;
    }

    public static RoomsDTO from(List<Room> roomList) {
        List<RoomDTO> roomDTOList = new ArrayList<>();

        for (Room room : roomList) {
            roomDTOList.add(RoomDTO.from(room));
        }

        return new RoomsDTO(roomDTOList);
    }
}
