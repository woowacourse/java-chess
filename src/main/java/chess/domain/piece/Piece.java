package chess.domain.piece;

import chess.domain.piece.moveRule.MoveRule;
import chess.domain.position.Position;
import java.util.Map;
import java.util.Objects;

public final class Piece {
    private final PieceData pieceData;
    private final MoveRule moveRule;

    public Piece(MoveRule moveRule, Color color) {
        this.moveRule = moveRule;
        this.pieceData = new PieceData(color, moveRule.pieceType());
    }

    public void move(Position currentPosition, Position nextPosition, Map<Position, Piece> board) {
        moveRule.move(currentPosition, nextPosition, board);
    }

    public boolean isOpponent(Piece other) {
        return pieceData.isDifferentColor(other.pieceData);
    }

    public boolean isSameColor(Piece other){
        return pieceData.isSameColor(other.pieceData);
    }

    public String formatName() {
        return pieceData.formatName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return Objects.equals(pieceData, piece.pieceData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceData);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "pieceData=" + pieceData +
                ", moveRule=" + moveRule +
                '}';
    }
}
