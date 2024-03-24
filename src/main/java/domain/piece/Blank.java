package domain.piece;

import domain.coordinate.Coordinate;
import domain.piece.base.ChessPieceBase;
import java.util.List;

public class Blank extends ChessPieceBase {

    public Blank(Color color) {
        super(color);
    }

    @Override
    public List<Integer> getDirection(Coordinate start, Coordinate destination, boolean canAttack) {
        throw new IllegalArgumentException("빈 칸으로, 움직일 방향이 없습니다.");
    }
}
