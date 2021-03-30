package chess.dto;

import chess.domain.board.Point;
import chess.domain.board.Square;

import java.util.HashMap;
import java.util.Map;

public class WebBoardDto {
    private Map<String, Object> boardDto;

    public WebBoardDto(Map<Point, Square> board) {
        boardDto = makeDto(board);
    }

    private Map<String, Object> makeDto(Map<Point, Square> board) {
        Map<String, Object> dto = new HashMap<>();
        board.keySet().forEach(point -> {
            dto.put(point.getPoint(), new PieceDto(board.get(point)));
        });
        return dto;
    }

    public Map<String, Object> getBoardDto() {
        return boardDto;
    }
}
