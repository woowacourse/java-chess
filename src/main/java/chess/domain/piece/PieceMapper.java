package chess.domain.piece;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum PieceMapper {
    BISHOP('b', Bishop::new),
    KING('k', King::new),
    PAWN('p', Pawn::new),
    QUEEN('q', Queen::new),
    ROOK('r', Rook::new),
    KNIGHT('n', Knight::new),
    EMPTY('.', Blank::new);

    private final char name;
    private final BiFunction<Color, Character, Piece> builder;

    PieceMapper(final char name, final BiFunction<Color, Character, Piece> builder) {
        this.name = name;
        this.builder = builder;
    }

    public static Piece of(final char name) {
        return Arrays.stream(values())
            .filter(mapper -> mapper.name == Character.toLowerCase(name))
            .findAny()
            .map(pieceMapper -> pieceMapper.builder.apply(Color.of(name), name))
            .orElseThrow(() -> new IllegalArgumentException("매칭 되는 피스가 존재하지 않습니다."));
    }
}