package chess.dto;

import java.util.List;

public class BoardsDto {

    private final List<RoomDto> boardsDto;

    public BoardsDto(List<RoomDto> boardsDto) {
        this.boardsDto = boardsDto;
    }

    public List<RoomDto> getBoardsDto() {
        return boardsDto;
    }
}
