package chess.dto;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardFindAllResponse {

    private final Map<String, BoardFindResponse> response;

    public BoardFindAllResponse(Map<String, BoardFindResponse> response) {
        this.response = response;
    }

    public Map<String, BoardFindResponse> getResponse() {
        return response;
    }

    public Board getBoard() {
        Map<Position, Piece> collect = response.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> Position.valueOf(e.getKey()),
                        e -> e.getValue().getPiece()));
        return new Board(collect);
    }
}
