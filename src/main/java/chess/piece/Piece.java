package chess.piece;

import chess.Board;
import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.List;

public abstract class Piece {

    protected final Position position;
    protected final Color color;
    protected final PieceType pieceType;

    public Piece(final Position position, final Color color, final PieceType pieceType) {
        this.position = position;
        this.color = color;
        this.pieceType = pieceType;
    }

    public Position getPosition() {
        return this.position;
    }

    public abstract Piece move(final Board board, final Position destination);

    protected Movement findMovement(final Position destination, final List<Movement> movements) {
        return movements.stream()
                .filter(movement -> position.canMove(movement) && position.move(movement).equals(destination))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("규칙에 맞지 않는 이동입니다"));
    }

    public boolean isSameType(PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
