package chess.domain.board.piece;

import chess.domain.board.Horizontal;
import chess.domain.board.Square;

public abstract class Pawn extends Piece{
    private static final int FIRST_MOVE_DISTANCE = 2;

    private static final Pawn BLACK_PAWN = new Pawn(Owner.BLACK){
        @Override
        public boolean isFirstLine(Horizontal horizontal) {
            return Horizontal.SEVEN.equals(horizontal);
        }
    };

    private static final Pawn WHITE_PAWN = new Pawn(Owner.WHITE) {
        @Override
        public boolean isFirstLine(Horizontal horizontal) {
            return Horizontal.TWO.equals(horizontal);
        }
    };

    private Pawn(Owner owner) {
        super(owner);
    }

    public static Pawn getInstanceOf(Owner owner){
        if (owner.equals(Owner.BLACK)){
            return BLACK_PAWN;
        }

        if (owner.equals(Owner.WHITE)){
            return WHITE_PAWN;
        }

        throw new IllegalArgumentException("Invalid pawn");
    }

    public abstract boolean isFirstLine(Horizontal horizontal);

    @Override
    public void validateMove(Square source, Square target) {
        if (!(source.isForward(owner, target))){
            throw new IllegalArgumentException();
        }

        if(!source.isStraight(target)){
            throw new IllegalArgumentException();
        }

        // 그냥 한칸 이동의 경우
        if (!(source.getDistance(target) == 1)) {
            throw new IllegalArgumentException();
        }

        // 첫번째 라인인 경우
        if (!(isFirstLine(source.getHorizontal())
                && source.getDistance(target) == FIRST_MOVE_DISTANCE)) {
            throw new IllegalArgumentException();
        }

        // 적이 있는 경우
        if(!source.isEnemy(target)){
            throw new IllegalArgumentException();
        }

        if(!(source.isDiagonal(target))
                && source.getDistance(target) == 1){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}