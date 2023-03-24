package chess.domain.piece;

import chess.domain.piece.moveRule.MoveRule;
import chess.domain.position.Position;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractPiece implements Piece {
    private final PieceData pieceData;
    private final MoveRule moveRule;

    public AbstractPiece(MoveRule moveRule, Color color) {
        this.moveRule = moveRule;
        this.pieceData = new PieceData(color, moveRule.pieceType());
    }

    protected Color color() {
        return pieceData.getColor();
    }

    public void validateSameColor(Piece other) {
        if (isSameColor(other)) {
            throw new IllegalArgumentException("아군 기물이 있는 위치로 이동할 수 없습니다.");
        }
    }

    @Override
    public void validateMovement(Position currentPosition, Position nextPosition) {
        moveRule.validateMovement(currentPosition, nextPosition);
    }

    @Override
    public boolean isOpponent(Color other) {
        return pieceData.isDifferentColor(other);
    }

    @Override
    public boolean isSameColor(Color other) {
        return pieceData.isSameColor(other);
    }

    @Override
    public boolean isOpponent(Piece other) {
        if (other.isEmpty()) {
            return false;
        }
        AbstractPiece piece = (AbstractPiece) other;
        return pieceData.isDifferentColor(piece.color());
    }

    @Override
    public boolean isSameColor(Piece other) {
        if (other.isEmpty()) {
            return false;
        }
        AbstractPiece piece = (AbstractPiece) other;
        return pieceData.isSameColor(piece.color());
    }

    @Override
    public String formatName() {
        return pieceData.formatName();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isPiece() {
        return true;
    }

    @Override
    public void addPieceType(Map<PieceType, Integer> pieceCounter) {
        PieceType pieceType = pieceData.getPieceType();
        int pieceCount = pieceCounter.getOrDefault(pieceType, 0);
        pieceCounter.put(pieceType, pieceCount + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractPiece piece = (AbstractPiece) o;
        return Objects.equals(pieceData, piece.pieceData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceData);
    }
}
