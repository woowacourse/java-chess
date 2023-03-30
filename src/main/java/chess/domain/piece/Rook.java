package chess.domain.piece;

import chess.domain.movepattern.NormalMovePattern;

public class Rook extends LinearPiece {

    public Rook(final Type type, final Side side) {
        super(type, side, NormalMovePattern.rookMovePattern());
    }

    @Override
    protected void validate(final Type type, final Side side) {
        validateType(type);
        validateSide(side);
    }

    private void validateType(final Type type) {
        if (type != Type.ROOK) {
            throw new IllegalArgumentException("룩의 타입이 잘못되었습니다.");
        }
    }

    private void validateSide(final Side side) {
        if (side == Side.NEUTRALITY) {
            throw new IllegalArgumentException("룩은 중립적인 기물이 아닙니다.");
        }
    }
}
