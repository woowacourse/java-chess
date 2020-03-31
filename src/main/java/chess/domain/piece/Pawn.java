package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends Piece {
    public Pawn(Position position, Team team) {
        super(position, Name.PAWN, team);
    }

    @Override
    public boolean canNotMoveTo(Piece that) {
        int columnGap = Math.abs(this.position.getColumnGap(that.position));
        if (columnGap == 0 && team.isEnemy(that.team)) {
            throw new IllegalArgumentException("폰은 전방의 적을 공격할 수 없습니다.");
        }
        if (columnGap == 1 && team.isNotEnemy(that.team)) {
            throw new IllegalArgumentException("폰은 공격이 아니면 대각선 이동이 불가합니다.");
        }
        return isSameTeam(that.team) || !createMovableArea().contains(that.position);
    }

    @Override
    protected List<Position> createMovableArea() {
        List<Position> movableArea = Position.getPositions()
                .stream()
                .filter(position -> !position.equals(this.position))
                .filter(this::isPawnArea)
                .collect(Collectors.toList());

        PawnMoveInfo moveInfo = PawnMoveInfo.of(team);
        if (moveInfo.isInitialRow(position)) {
            movableArea.add(moveInfo.getJumpedPositionOf(position));
        }

        return movableArea;
    }

    private boolean isPawnArea(Position position) {
        return this.position.getColumnGap(position) <= 1 &&
                PawnMoveInfo.of(team).isValidRowGap(this.position.getRowGap(position));
    }

    @Override
    public boolean isNotEmpty() {
        return true;
    }
}
