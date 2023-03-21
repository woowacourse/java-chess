package chess.domain.piece;

import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.RookMovePattern;
import java.util.Arrays;
import java.util.List;

public class Rook extends LinearPiece {

    private final List<MovePattern> movePatterns;

    public Rook(final Type type, final Side side) {
        super(type, side);
        this.movePatterns = Arrays.asList(RookMovePattern.values());
    }

    @Override
    protected List<MovePattern> getMovePatterns() {
        return movePatterns;
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
