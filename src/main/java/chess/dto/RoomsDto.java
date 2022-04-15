package chess.dto;

import java.util.List;

public class RoomsDto {

    private final List<RoomDto> roomsDto;

    public RoomsDto(List<RoomDto> roomsDto) {
        this.roomsDto = roomsDto;
    }

    public List<RoomDto> getRoomsDto() {
        return roomsDto;
    }
}
