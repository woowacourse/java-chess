package chess.piece;

import chess.ChessBoard;
import chess.Color;
import chess.Position;
import java.util.Objects;

public abstract class Piece {

    private final Color color;
    protected Position position;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public void move(ChessBoard chessBoard, Color color, Position to) {
        if (!isColor(color)) {
            throw new IllegalArgumentException("자기팀의 기물이 아닙니다.");
        }
        if (chessBoard.isColorInPosition(color, to)) {
            throw new IllegalArgumentException("목적지에 같은 팀의 기물이 있습니다.");
        }
        if (!isMovable(chessBoard, to)) {
            throw new IllegalArgumentException("해당 목적지로 갈 수 없습니다.");
        }
        if (chessBoard.isExistPiece(to)) {
            chessBoard.remove(to);
        }
        position = to;
    }

    public boolean isColor(Color color) {
        return this.color == color;
    }

    public boolean isPosition(Position position) {
        return this.position.equals(position);
    }

    abstract protected boolean isMovable(ChessBoard chessBoard, Position to);

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Piece piece = (Piece) object;
        return color == piece.color && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(color);
        result = 31 * result + Objects.hashCode(position);
        return result;
    }
}
