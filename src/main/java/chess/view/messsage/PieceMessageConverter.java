package chess.view.messsage;

import chess.model.piece.Camp;
import chess.model.piece.Piece;
import chess.model.piece.type.Bishop;
import chess.model.piece.type.InitialPawn;
import chess.model.piece.type.King;
import chess.model.piece.type.Knight;
import chess.model.piece.type.Pawn;
import chess.model.piece.type.Queen;
import chess.model.piece.type.Rook;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum PieceMessageConverter {

    PAWN(Pawn.class, "p"),
    INITIAL_PAWN(InitialPawn.class, "p"),
    BISHOP(Bishop.class, "b"),
    KNIGHT(Knight.class, "n"),
    ROOK(Rook.class, "r"),
    QUEEN(Queen.class, "q"),
    KING(King.class, "k");

    private static final String EMPTY_MESSAGE = ".";

    private final Class<? extends Piece> pieceType;
    private final String message;

    private static final Map<Class<? extends Piece>, String> CACHE = Arrays.stream(values())
            .collect(Collectors.toMap(
                    converter -> converter.pieceType,
                    converter -> converter.message)
            );

    PieceMessageConverter(final Class<? extends Piece> pieceType, final String message) {
        this.pieceType = pieceType;
        this.message = message;
    }

    public static String convert(final Class<? extends Piece> pieceType, final Camp camp) {
        final String message = CACHE.getOrDefault(pieceType, EMPTY_MESSAGE);

        if (Camp.BLACK.isSameCamp(camp)) {
            return message.toUpperCase();
        }
        return message;
    }
}
