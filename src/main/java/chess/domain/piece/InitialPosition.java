package chess.domain.piece;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

@FunctionalInterface
public interface InitialPosition {

    boolean isRightOn(Position position, Side side);

    static boolean king(Position position, Side side) {
        return isNotPawn(position, side)
            && position.isOn(Column.E);
    }

    static boolean queen(Position position, Side side) {
        return isNotPawn(position, side)
            && position.isOn(Column.D);
    }

    static boolean rook(Position position, Side side) {
        return isNotPawn(position, side)
            && (position.isOn(Column.A) || position.isOn(Column.H));
    }

    static boolean bishop(Position position, Side side) {
        return isNotPawn(position, side)
            && (position.isOn(Column.C) || position.isOn(Column.F));
    }

    static boolean knight(Position position, Side side) {
        return isNotPawn(position, side)
            && (position.isOn(Column.B) || position.isOn(Column.G));
    }

    static boolean pawn(Position position, Side side) {
        return side == Side.WHITE && position.isOn(Row.TWO)
            || side == Side.BLACK && position.isOn(Row.SEVEN);
    }

    static boolean isNotPawn(final Position position, final Side side) {
        return side == Side.WHITE && position.isOn(Row.ONE)
            || side == Side.BLACK && position.isOn(Row.EIGHT);
    }

}
