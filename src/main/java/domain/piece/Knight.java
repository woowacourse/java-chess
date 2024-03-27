package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.base.ChessPieceBase;
import domain.piece.strategy.PieceStrategy;

public class Knight extends ChessPieceBase {

    private final PieceStrategy pieceStrategy;

    public Knight(Color color) {
        super(color);
        this.pieceStrategy = new PieceStrategy(Direction.KNIGHT_DIRECTION);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination, boolean isAttack) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        return pieceStrategy.findDirection(rowDifference, columnDifference);
    }
}
