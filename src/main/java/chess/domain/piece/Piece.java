package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Piece {

    // TODO: 빈 공간을 나타내는 Piece 생성 고려
    private static final Map<String, Piece> CACHE = new HashMap<>();

    private final PieceType pieceType;
    private final Color color;

    static {
        for (final PieceType pieceType : PieceType.values()) {
            for (Color color : Color.values()) {
                CACHE.put(toKey(pieceType, color), new Piece(pieceType, color));
            }
        }
    }

    // TODO: private로 변경
    public Piece(final PieceType pieceType, final Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    // TODO: 각 인자로 null이 올 수 없도록 검증 추가 필요
    public static Piece of(final PieceType pieceType, final Color color) {
        return CACHE.get(toKey(pieceType, color));
    }

    private static String toKey(final PieceType pieceType, final Color color) {
        return pieceType.name() + color.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return pieceType == piece.pieceType && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, color);
    }
}
