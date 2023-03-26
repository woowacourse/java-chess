package chess;

import chess.domain.PieceDto;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public class KingDiedException extends IllegalStateException {
    private static final String message = "왕이 잡혔습니다. 게임이 종료됩니다.";
    private Map<Position, PieceDto> board;

    public KingDiedException(Map<Position, PieceDto> board) {
        super(message);
        this.board = board;
    }

    public Map<Position, PieceDto> getBoard() {
        return board;
    }
}
