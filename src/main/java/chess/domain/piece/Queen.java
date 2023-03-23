package chess.domain.piece;

import chess.domain.movepattern.QueenMovePattern;

import java.util.Arrays;

public class Queen extends LinearPiece {

    public Queen(final Type type, final Side side) {
        super(type, side, Arrays.asList(QueenMovePattern.values()));
    }

    @Override
    protected void validate(final Type type, final Side side) {
        validateType(type);
        validateSide(side);
    }

    private void validateType(final Type type) {
        if (type != Type.QUEEN) {
            throw new IllegalArgumentException("퀸의 타입이 잘못되었습니다.");
        }
    }

    private void validateSide(final Side side) {
        if (side == Side.NEUTRALITY) {
            throw new IllegalArgumentException("퀸은 중립적인 기물이 아닙니다.");
        }
    }
}
