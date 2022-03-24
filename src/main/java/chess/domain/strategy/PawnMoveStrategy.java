package chess.domain.strategy;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.domain.piece.Team;
import chess.domain.position.Position;

public class PawnMoveStrategy implements MoveStrategy {

    private final Team team;

    public PawnMoveStrategy(Team team) {
        this.team = team;
    }
    @Override
    public void isMovable(Position source, Position target) {
        if (source.isSameFile(target) && source.isSameRank(target)) {
            throw new IllegalStateException("제자리에 머무를 수 없습니다.");
        }

        if (!source.isSameFile(target)) {
            throw new IllegalStateException("제자리에 머무를 수 없습니다.");
        }

        if (team == WHITE && source.isReductionRank(target)) {
            throw new IllegalStateException("잘못된 이동입니다.");
        }

        if (team == BLACK && source.isIncreaseRank(target)) {
            throw new IllegalStateException("잘못된 이동입니다.");
        }
    }
}
