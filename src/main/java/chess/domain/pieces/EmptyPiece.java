package chess.domain.pieces;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.math.Direction;

public final class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(Team.NEUTRALITY);
    }

    @Override
    public void validateDirection(final Direction direction) {
        throw new IllegalArgumentException("기물이 존재하지 않습니다.");
    }

    @Override
    public void validateDistance(final Position current, final Position target) {
        throw new IllegalArgumentException("기물이 존재하지 않습니다.");
    }
}
