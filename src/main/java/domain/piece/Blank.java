package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.base.ChessPieceBase;

public class Blank extends ChessPieceBase {

    public Blank() {
        super(Color.NONE);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination, boolean canAttack) {
        throw new IllegalArgumentException("빈 칸으로, 움직일 방향이 없습니다.");
    }
}
