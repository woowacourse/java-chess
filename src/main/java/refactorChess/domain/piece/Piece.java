package refactorChess.domain.piece;

import java.util.List;
import refactorChess.domain.board.Direction;
import refactorChess.domain.board.Position;

public abstract class Piece {

    private final PieceType pieceType;
    private final PieceColor pieceColor;
    private Position position;

    public Piece(PieceType pieceType, PieceColor pieceColor, Position position) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
        this.position = position;
    }

    public boolean isBlank() {
        return this.pieceType == PieceType.NO_PIECE;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public Position getPosition() {
        return position;
    }

    protected abstract Direction findByDirection(Position from, Position to);

    protected abstract List<Direction> findByMovableDirection(Piece piece, Direction direction);
}
