package chess.domain.piece;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.WayPointsWithCondition;

public abstract class Piece implements Cloneable {

    protected final Color color;
    protected PiecePosition piecePosition;

    public Piece(final Color color, final PiecePosition piecePosition) {
        this.color = color;
        this.piecePosition = piecePosition;
    }

    public boolean existIn(final PiecePosition piecePosition) {
        return this.piecePosition.equals(piecePosition);
    }

    public WayPointsWithCondition wayPointsWithCondition(final PiecePosition destination) {
        return wayPointsWithCondition(Path.of(piecePosition, destination));
    }

    protected abstract WayPointsWithCondition wayPointsWithCondition(final Path path);

    @Override
    public Piece clone() {
        try {
            return (Piece) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Color color() {
        return color;
    }

    public PiecePosition piecePosition() {
        return piecePosition;
    }

    public boolean isEnemy(final Piece enemy) {
        return color != enemy.color;
    }

    public void move(final PiecePosition destination) {
        this.piecePosition = destination;
    }
}
