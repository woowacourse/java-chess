package chess.view.messsage;

import chess.model.piece.Camp;
import chess.model.piece.PieceType;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum PieceMessageConverter {

    PAWN(PieceType.PAWN, "p"),
    INITIAL_PAWN(PieceType.INITIAL_PAWN, "p"),
    BISHOP(PieceType.BISHOP, "b"),
    KNIGHT(PieceType.KNIGHT, "n"),
    ROOK(PieceType.ROOK, "r"),
    QUEEN(PieceType.QUEEN, "q"),
    KING(PieceType.KING, "k");

    private static final String EMPTY_MESSAGE = ".";

    private final PieceType pieceType;
    private final String message;

    private static final Map<PieceType, String> CACHE = Arrays.stream(values())
            .collect(Collectors.toMap(
                    converter -> converter.pieceType,
                    converter -> converter.message)
            );

    PieceMessageConverter(final PieceType pieceType, final String message) {
        this.pieceType = pieceType;
        this.message = message;
    }

    public static String convert(final PieceType pieceType, final Camp camp) {
        final String message = CACHE.getOrDefault(pieceType, EMPTY_MESSAGE);

        if (Camp.BLACK.isSameCamp(camp)) {
            return message.toUpperCase();
        }
        return message;
    }
}
