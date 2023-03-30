package chess.domain.piece;

import chess.domain.movepattern.NormalMovePattern;

public class King extends ImmediatePiece {

    public King(final Type type, final Side side) {
        super(type, side, NormalMovePattern.kingMovePattern());
    }

    @Override
    protected void validate(final Type type, final Side side) {
        validateType(type);
        validateSide(side);
    }

    private void validateType(final Type type) {
        if (type != Type.KING) {
            throw new IllegalArgumentException("킹의 타입이 잘못되었습니다.");
        }
    }

    private void validateSide(final Side side) {
        if (side == Side.NEUTRALITY) {
            throw new IllegalArgumentException("킹은 중립적인 기물이 아닙니다.");
        }
    }
}
