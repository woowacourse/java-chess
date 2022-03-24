package chess.domain.board.strategy;

import java.util.Map;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

public class CreateMockBoardStrategy implements BoardInitializeStrategy {
    private final Map<Position, Piece> pieces;

    public CreateMockBoardStrategy(final Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    @Override
    public Map<Position, Piece> createPieces() {
        return pieces;
    }
}
