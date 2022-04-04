package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class BoardDto {

    private final Map<Position, Piece> board;

    public BoardDto(Map<Position, Piece> board) {
        this.board = board;
    }
}
