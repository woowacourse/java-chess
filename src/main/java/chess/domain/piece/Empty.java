package chess.domain.piece;

import chess.domain.game.Position;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.Collections;
import java.util.List;

public final class Empty extends Piece {

    private Empty(final Team team, final PieceType pieceType, final MovingStrategies strategies) {
        super(team, pieceType, strategies);
    }

    public static Empty create() {
        final MovingStrategies emptyStrategies = new MovingStrategies(Collections.emptyList());
        return new Empty(Team.EMPTY, PieceType.EMPTY, emptyStrategies);
    }

    @Override
    public List<Position> calculatePath(final MovingStrategy strategy, final Position source, final Position target, final Team targetTeam) {
        throw new IllegalArgumentException("기물이 없는 곳을 선택하셨습니다.");
    }

    @Override
    public boolean isInitialPawn() {
        return false;
    }
}
