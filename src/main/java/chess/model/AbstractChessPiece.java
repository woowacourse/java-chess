package chess.model;

import java.util.List;

public abstract class AbstractChessPiece {

    private ChessPieceType type;
    private ChessPieceColor color;

    public abstract List<Point> getMovablePoints(Point source);
}
