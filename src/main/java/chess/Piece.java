package chess;

public enum Piece {
    KING(new KingMovingStrategy()),
    QUEEN(new QueenMovingStrategy()),
    ROOK(new RookMovingStrategy()),
    BISHOP(new BishopMovingStrategy()),
    KNIGHT(new KnightMovingStrategy()),
    PAWN(new PawnMovingStrategy()),
    EMPTY(new EmptyMovingStrategy());

    private MovingStrategy movingStrategy;

    Piece(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public boolean canMove(Point source, Point destination) {
        return movingStrategy.canMove(source, destination);
    }
}
