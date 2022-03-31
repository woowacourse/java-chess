package chess.domain.refactorBoard;

import chess.domain.refactorPiece.Piece;
import chess.domain.refactorPosition.Position;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> pieces;

    public ChessBoard(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public boolean isEmptyPosition(Position position) {
        return !pieces.containsKey(position);
    }
}
