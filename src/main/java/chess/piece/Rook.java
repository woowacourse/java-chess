package chess.piece;

import chess.Position;

import java.util.List;

public class Rook extends ChessPiece {

    public Rook(final Position position) {
        super(position);
    }

    @Override
    protected List<Position> calculateCanMovePositions() {
        return List.of();
    }
}
