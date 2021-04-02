package chess.mapper;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Color;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public enum PieceMapper {
    BISHOP("b", Bishop::new),
    KING("k", King::new),
    PAWN("p", Pawn::new),
    QUEEN("q", Queen::new),
    ROOK("r", Rook::new),
    KNIGHT("n", Knight::new),
    BLANK(".", (color) -> Blank.getInstance());

    private final String name;
    private final Function<Color, Piece> builder;

    PieceMapper(String name, Function<Color, Piece> builder) {
        this.name = name;
        this.builder = builder;
    }

    public static Optional<Piece> of(String name) {
        return Arrays.stream(values())
                .filter(mapper -> mapper.name.equalsIgnoreCase(name))
                .findAny()
                .map(pieceMapper -> pieceMapper.builder.apply(Color.of(name)));
    }
}
