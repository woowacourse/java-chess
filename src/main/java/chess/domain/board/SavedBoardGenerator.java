package chess.domain.board;

import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.position.Square;

public class SavedBoardGenerator implements BoardGenerator{
    private final Map<Square, Piece> board;

    public SavedBoardGenerator(Map<Square, Piece> board) {
        this.board = board;
    }

    @Override
    public Map<Square, Piece> generate() {
        return board;
    }
}
