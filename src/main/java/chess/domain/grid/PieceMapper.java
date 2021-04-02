package chess.domain.grid;

import chess.domain.piece.*;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum PieceMapper {
    WHITE_BISHOP('b', Color.WHITE, Bishop::new),
    BLACK_BISHOP('B', Color.BLACK, Bishop::new),
    WHITE_KING('k', Color.WHITE, King::new),
    BLACK_KING('K', Color.BLACK, King::new),
    WHITE_PAWN('p', Color.WHITE, Pawn::new),
    BLACK_PAWN('P', Color.BLACK, Pawn::new),
    WHITE_QUEEN('q', Color.WHITE, Queen::new),
    BLACK_QUEEN('Q', Color.BLACK, Queen::new),
    WHITE_ROOK('r', Color.WHITE, Rook::new),
    BLACK_ROOK('R', Color.BLACK, Rook::new),
    WHITE_KNIGHT('n', Color.WHITE, Knight::new),
    BLACK_KNIGHT('N', Color.BLACK, Knight::new),
    EMPTY('.', Color.BLACK, Empty::new);

    private final char name;
    private final Color color;
    private final BiFunction<Color, Position, Piece> pieceBuilder;

    PieceMapper(final char name, final Color color, final BiFunction<Color, Position, Piece> pieceBuilder) {
        this.name = name;
        this.color = color;
        this.pieceBuilder = pieceBuilder;
    }

    public static Piece of(final char name, final Position position) {
        return Arrays.stream(values())
                .filter(mapper -> mapper.name == name)
                .findAny()
                .map(pieceMapper -> pieceMapper.pieceBuilder.apply(pieceMapper.color, position))
                .orElseThrow(() -> new IllegalArgumentException("매칭 되는 피스가 존재하지 않습니다."));
    }
}