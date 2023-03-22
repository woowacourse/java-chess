package chess.view;

import chess.domain.camp.TeamColor;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PieceName {

    QUEEN(PieceType.QUEEN, 'Q'),
    ROOK(PieceType.ROOK, 'R'),
    KNIGHT(PieceType.KNIGHT, 'N'),
    PAWN(PieceType.PAWN, 'P'),
    BISHOP(PieceType.BISHOP, 'B'),
    KING(PieceType.KING, 'K');

    private static final Map<PieceType, Character> CACHE = Stream.of(PieceName.values())
            .collect(Collectors.toUnmodifiableMap(pieceName -> pieceName.type,
                    pieceName -> pieceName.name));

    private final PieceType type;
    private final Character name;

    PieceName(final PieceType type, final Character name) {
        this.type = type;
        this.name = name;
    }

    public static Character findMessage(final Piece piece) {
        if (piece.isSameCamp(TeamColor.BLACK)) {
            return CACHE.get(piece.getPieceType());
        }
        return Character.toLowerCase(CACHE.get(piece.getPieceType()));
    }
}
