package chess.domain.game;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.InitialBlackPawn;
import chess.domain.piece.pawn.InitialWhitePawn;
import chess.domain.piece.pawn.WhitePawn;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PieceMapper {

    private static final Map<PieceType, Function<Team, Piece>> CACHE = new HashMap<>();

    static {
        CACHE.put(PieceType.ROOK, Rook::instance);
        CACHE.put(PieceType.KNIGHT, Knight::instance);
        CACHE.put(PieceType.BISHOP, Bishop::instance);
        CACHE.put(PieceType.QUEEN, Queen::instance);
        CACHE.put(PieceType.KING, King::instance);
        CACHE.put(PieceType.INITIAL_BLACK_PAWN, piece -> InitialBlackPawn.instance());
        CACHE.put(PieceType.INITIAL_WHITE_PAWN, piece -> InitialWhitePawn.instance());
        CACHE.put(PieceType.BLACK_PAWN, piece -> BlackPawn.instance());
        CACHE.put(PieceType.WHITE_PAWN, piece -> WhitePawn.instance());
        CACHE.put(PieceType.EMPTY, piece -> Empty.instance());
    }

    public static Piece get(final PieceType pieceType, final Team team) {
        return CACHE.get(pieceType).apply(team);
    }
}
