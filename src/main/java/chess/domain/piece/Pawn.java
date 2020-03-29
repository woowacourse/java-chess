package chess.domain.piece;

import chess.domain.position.MovableAreaFactory;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.List;

public class Pawn extends Piece {
    public Pawn(Position position, Team team) {
        super(position, Name.PAWN, team);
    }

    //todo
    @Override
    public void canPawnMove(Piece that) {
        int columnGap = Math.abs(this.position.getColumnGap(that.position));
        if (columnGap == 0 && isEnemy(that)) {
            throw new IllegalArgumentException("폰은 전방의 적을 공격할 수 없습니다.");
        }
        if (columnGap == 1 && !isEnemy(that)) {
            throw new IllegalArgumentException("폰은 공격이 아니면 대각선 이동이 불가합니다.");
        }
    }

    @Override
    protected boolean isNotMovableTo(Position start, Position destination) {
        List<Position> movable = MovableAreaFactory.pawnOf(start);
        if (isInitialPawnRow(start.getRow())) {
            movable.add(Position.of(start.getColumn(), Row.FOUR));
        }
        return !movable.contains(destination);
    }

    private boolean isInitialPawnRow(Row row) {
        return row.equals(Row.TWO);
    }
}
