package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public class Board {

    private Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board of(BoardInitializer boardInitializer) {
        return new Board(boardInitializer.create());
    }
}
