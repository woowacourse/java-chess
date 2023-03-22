package chess.view;

import chess.controller.dto.PieceDto;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PieceName {

    QUEEN("QUEEN", 'Q'),
    ROOK("ROOK", 'R'),
    KNIGHT("KNIGHT", 'N'),
    PAWN("PAWN", 'P'),
    BISHOP("BISHOP", 'B'),
    KING("KING", 'K');

    private static final Map<String, Character> CACHE = Stream.of(PieceName.values())
            .collect(Collectors.toUnmodifiableMap(pieceName -> pieceName.type,
                    pieceName -> pieceName.name));

    private final String type;
    private final Character name;

    PieceName(final String type, final Character name) {
        this.type = type;
        this.name = name;
    }

    public static Character findMessage(final PieceDto pieceDto) {
        if (pieceDto.isSameCamp("BLACK")) {
            return CACHE.get(pieceDto.getPieceType());
        }
        return Character.toLowerCase(CACHE.get(pieceDto.getPieceType()));
    }
}
