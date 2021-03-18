package chess.domain.piece;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;

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

    public void validateMove(Position source, Position target, boolean isEnemy) {
        if (!(source.isForward(owner, target))){
            throw new IllegalArgumentException();
        }

        // 첫번째 라인으로 두칸을 움직이고 싶은 경우
        if (isValidStraightMove(source, target) || isValidDiagonalMove(source, target, isEnemy)) {
            return;
        }

        throw new IllegalArgumentException();
    }

    private boolean isValidStraightMove(Position source, Position target) {
        return source.isStraight(target) && (isValidBasicMove(source, target) || isValidSpecialMove(source, target));
    }

    private boolean isValidBasicMove(Position source, Position target) {
        return source.getDistance(target) == 1;
    }

    private boolean isValidSpecialMove(Position source, Position target){
        return isFirstLine(source.getHorizontal(target))
                && source.getDistance(target) == FIRST_MOVE_DISTANCE;
    }

    private boolean isValidDiagonalMove(Position source, Position target, boolean isEnemy){
        return source.getDistance(target) == 1 && isEnemy;
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