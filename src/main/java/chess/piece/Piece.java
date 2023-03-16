package chess.piece;

import chess.board.Position;
import java.util.List;

public abstract class Piece {

    protected final Position position;
    protected final Side side;

    public Piece(final Position position, Side side) {
        this.position = position;
        this.side = side;
    }

    public abstract boolean isMovable(Position targetPosition);

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }

    public Piece move(final Position positionToMove) {
        if (this instanceof King) {
            return new King(positionToMove, this.side);
        }
        if (this instanceof Queen) {
            return new Queen(positionToMove, this.side);
        }
        if (this instanceof Rook) {
            return new Rook(positionToMove, this.side);
        }
        if (this instanceof Knight) {
            return new Knight(positionToMove, this.side);
        }
        if (this instanceof Bishop) {
            return new Bishop(positionToMove, this.side);
        }
        return new Pawn(positionToMove, this.side);
    }

    public abstract List<Position> getPaths(Position targetPosition);

    public boolean isSameSide(Piece piece) {
        return this.side == piece.side;
    }

    public int getFile() {
        return position.getFile();
    }

    public int getRank() {
        return position.getRank();
    }

    public Side getSide() {
        return side;
    }
}
