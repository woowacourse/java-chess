package chess.model.piece;

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

public enum PieceScoreConverter {

    PAWN(Pawn.class, PieceScore.PAWN),
    INITIAL_PAWN(InitialPawn.class, PieceScore.PAWN),
    BISHOP(Bishop.class, PieceScore.BISHOP),
    KNIGHT(Knight.class, PieceScore.KNIGHT),
    ROOK(Rook.class, PieceScore.ROOK),
    QUEEN(Queen.class, PieceScore.QUEEN),
    KING(King.class, PieceScore.ZERO);

    private final Class<? extends Piece> pieceType;
    private final PieceScore score;

    private static final Map<Class<? extends Piece>, PieceScore> CACHE = Arrays.stream(values())
            .collect(Collectors.toMap(
                    converter -> converter.pieceType,
                    converter -> converter.score)
            );

    PieceScoreConverter(final Class<? extends Piece> pieceType, final PieceScore score) {
        this.pieceType = pieceType;
        this.score = score;
    }

    public static PieceScore convert(Class<? extends Piece> pieceType) {
        return CACHE.get(pieceType);
    }
}
