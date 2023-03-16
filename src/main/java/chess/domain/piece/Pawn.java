package chess.domain.piece;

import chess.domain.Side;
import chess.domain.Type;
import chess.domain.movepattern.KnightMovePattern;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.PawnMovePattern;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {

    private final List<MovePattern> movePatterns;

    public Pawn(final Type type, final Side side) {
        super(type, side);
        movePatterns = Arrays.asList(PawnMovePattern.values());
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
        if (type != Type.PAWN) {
            throw new IllegalArgumentException("폰의 타입이 잘못되었습니다.");
        }
    }

    private void validateSide(final Side side) {
        if (side == Side.NEUTRALITY) {
            throw new IllegalArgumentException("폰은 중립적인 기물이 아닙니다.");
        }
    }
}
