package chess.domain.piece;

import chess.domain.movepattern.KnightMovePattern;
import chess.domain.movepattern.MovePattern;
import java.util.Arrays;
import java.util.List;

public class Knight extends ImmediatePiece {

    private final List<MovePattern> movePatterns;

    public Knight(final Type type, final Side side) {
        super(type, side);
        this.movePatterns = Arrays.asList(KnightMovePattern.values());
    }

    @Override
    protected void validate(final Type type, final Side side) {
        validateType(type);
        validateSide(side);
    }

    @Override
    protected List<MovePattern> getMovePatterns() {
        return movePatterns;
    }

    private void validateType(final Type type) {
        if (type != Type.KNIGHT) {
            throw new IllegalArgumentException("나이트의 타입이 잘못되었습니다.");
        }
    }

    private void validateSide(final Side side) {
        if (side == Side.NEUTRALITY) {
            throw new IllegalArgumentException("나이트는 중립적인 기물이 아닙니다.");
        }
    }
}
