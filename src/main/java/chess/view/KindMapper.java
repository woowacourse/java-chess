package chess.view;

import chess.domain.piece.normal.Piece;
import chess.domain.piece.property.Color;
import chess.domain.piece.property.Kind;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum KindMapper {
    BLACK_PAWN(Kind.PAWN, Color.BLACK, "P"),
    WHITE_PAWN(Kind.PAWN, Color.WHITE, "p"),
    BLACK_KING(Kind.KING, Color.BLACK, "K"),
    WHITE_KING(Kind.KING, Color.WHITE, "k"),
    BLACK_QUEEN(Kind.QUEEN, Color.BLACK, "Q"),
    WHITE_QUEEN(Kind.QUEEN, Color.WHITE, "q"),
    BLACK_BISHOP(Kind.BISHOP, Color.BLACK, "B"),
    WHITE_BISHOP(Kind.BISHOP, Color.WHITE, "b"),
    BLACK_ROOK(Kind.ROOK, Color.BLACK, "R"),
    WHITE_ROOK(Kind.ROOK, Color.WHITE, "r"),
    BLACK_KNIGHT(Kind.KNIGHT, Color.BLACK, "N"),
    WHITE_KNIGHT(Kind.KNIGHT, Color.WHITE, "n"),
    EMPTY(Kind.EMPTY, Color.NONE, ".");

    private final Kind kind;
    private final Color color;
    private final String pieceView;

    KindMapper(final Kind kind, final Color color, final String pieceView) {
        this.kind = kind;
        this.color = color;
        this.pieceView = pieceView;
    }

    public static String mapping(final Kind kind, final Color color) {
        return Arrays.stream(KindMapper.values())
                .filter(kindMapper -> kindMapper.kind == kind && kindMapper.color == color)
                .map(kindMapper -> kindMapper.pieceView)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("매핑 정보가 잘못되었습니다."));
    }

    public static List<String> mapToStrings(List<Piece> pieces) {
        return pieces.stream()
                .map(piece -> mapping(piece.getKind(), piece.getColor()))
                .collect(Collectors.toList());
    }
}
