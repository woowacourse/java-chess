package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.Square;

public class Pawn extends Piece {

    private static final int RANK_WEIGHT_ONE = 1;
    private static final int RANK_WEIGHT_TWO = 2;
    private static final int FILE_WEIGHT_ZERO = 0;
    private final boolean isMove;

    public Pawn(final Camp camp) {
        super(camp);
        this.isMove = false;
    }

    public Pawn(final Camp camp, final boolean isMove) {
        super(camp);
        this.isMove = isMove;
    }

    @Override
    public boolean isMovable(final Square source, final Square target) {
        if (isMove) {
            return source.isAble(
                    target,
                    camp.calculateDirection(FILE_WEIGHT_ZERO),
                    camp.calculateDirection(RANK_WEIGHT_ONE)
            );
        }
        if (!isMove) {
            return source.isAble(
                    target,
                    camp.calculateDirection(FILE_WEIGHT_ZERO),
                    camp.calculateDirection(RANK_WEIGHT_ONE)
            ) || source.isAble(
                    target,
                    camp.calculateDirection(FILE_WEIGHT_ZERO),
                    camp.calculateDirection(RANK_WEIGHT_TWO)
            );
        }
        return false;
    }

    public boolean isMovable(final Square source, final Square target, final boolean right) {
        if (right) {
            return source.isAble(target, camp.calculateDirection(1), camp.calculateDirection(1));
        }
        if (!right) {
            return source.isAble(target, camp.calculateDirection(-1), camp.calculateDirection(1));
        }
        return false;
    }
}
