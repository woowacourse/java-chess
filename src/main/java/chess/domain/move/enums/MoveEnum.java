package chess.domain.move.enums;

public interface MoveEnum {

    boolean isMove(final int dx, final int dy);

    int getDx();

    int getDy();
}
