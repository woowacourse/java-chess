package chess.domain.piece;

import java.util.List;
import java.util.Map;

public abstract class Piece {

    protected PieceType pieceType;
    protected Position position;
    protected Color color;

    public Piece(PieceType pieceType, Position position, Color color) {
        this.pieceType = pieceType;
        this.position = position;
        this.color = color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public boolean isExist(Column column, Row row) {
        return position.hasSameRowAndColumn(column, row);
    }

    public boolean isExist(Position destination) {
        return this.position.equals(destination);
    }

    public void move(Map.Entry<Column, Row> destination, List<Piece> allPiecesExceptMovingPiece) {
        Position destinationPosition = new Position(destination.getKey(), destination.getValue());
        if (!isMoveablePosition(destinationPosition)) {
            throw new IllegalArgumentException("이동할 수 없는 좌표입니다.");
        }
        if (!isMoveablePath(destinationPosition, allPiecesExceptMovingPiece)) {
            throw new IllegalArgumentException("이동할 수 없는 경로입니다.");
        }
        this.position = destinationPosition;
    }

    protected Map.Entry<Integer, Integer> calculateDistance(Position destination) {
        return position.calculateColumnAndRowDistance(destination);
    }

    protected abstract boolean isMoveablePosition(Position destinationPosition);

    protected abstract boolean isMoveablePath(Position destinationPosition, List<Piece> allPiecesExceptMovingPiece);
}
