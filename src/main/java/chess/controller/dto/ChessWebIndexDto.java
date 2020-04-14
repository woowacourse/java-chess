package chess.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

public class ChessWebIndexDto {

    private List<WebDto> roomId;

    private ChessWebIndexDto(List<WebDto> roomId) {
        this.roomId = roomId;
    }

    public static ChessWebIndexDto of(List<Long> roomId) {
        return new ChessWebIndexDto(getRoomDto(roomId));
    }

    private static List<WebDto> getRoomDto(List<Long> roomIds) {
        return roomIds.stream()
                .map(room -> {
                    String key = String.valueOf(room);
                    String value = String.valueOf(room);
                    return new WebDto(key, value);
                })
                .collect(Collectors.toList());
    }

    public List<WebDto> getRoomId() {
        return roomId;
    }
}
