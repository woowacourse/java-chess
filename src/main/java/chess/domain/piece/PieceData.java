package chess.domain.piece;

import java.util.Objects;

public class PieceData {
    private final Color color;
    private final PieceType pieceType;

    public PieceData(Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public String formatName() {
        return pieceType.formatName(color);
    }

    public boolean isDifferentColor(PieceData other) {
        return color.isDifferent(other.color);
    }

    public boolean isSameColor(PieceData other) {
        return color.isEqual(other.color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PieceData pieceData = (PieceData) o;
        return color == pieceData.color && pieceType == pieceData.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType);
    }
}
