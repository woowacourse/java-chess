package piece;

import coordinate.Coordinate;
import java.util.List;

public interface Piece {

    // todo: isSameColor와 isBlack의 코드가 모든 구현 클래스에서 동일함! 퉁칠 방법 있을까?
    boolean isSameColor(Color color);

    List<Integer> getDirection(Coordinate coordinate, Coordinate destination, boolean canAttack);

    boolean isBlack();
}
