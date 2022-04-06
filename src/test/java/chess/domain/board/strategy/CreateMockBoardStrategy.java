package chess.domain.board.strategy;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class CreateMockBoardStrategy implements CreateBoardStrategy {
    private final Map<Position, Piece> pieces;

    public CreateMockBoardStrategy(final Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    @Override
    public Map<Position, Piece> createPieces() {
        return pieces;
    }
}
