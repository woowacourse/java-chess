package chess.dao;

import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

public class PieceMapper {

    private static final Map<PieceType, Supplier<Piece>> BLACK_PIECE = new HashMap<>();

    private static final Map<PieceType, Supplier<Piece>> WHITE_PIECE = new HashMap<>();

    static {
        BLACK_PIECE.put(PieceType.KING, () -> King.from(BLACK));
        BLACK_PIECE.put(PieceType.QUEEN, () -> Queen.from(BLACK));
        BLACK_PIECE.put(PieceType.ROOK, () -> Rook.from(BLACK));
        BLACK_PIECE.put(PieceType.KNIGHT, () -> Knight.from(BLACK));
        BLACK_PIECE.put(PieceType.BISHOP, () -> Bishop.from(BLACK));
        BLACK_PIECE.put(PieceType.PAWN, () -> Pawn.from(BLACK));

        WHITE_PIECE.put(PieceType.KING, () -> King.from(WHITE));
        WHITE_PIECE.put(PieceType.QUEEN, () -> Queen.from(WHITE));
        WHITE_PIECE.put(PieceType.ROOK, () -> Rook.from(WHITE));
        WHITE_PIECE.put(PieceType.KNIGHT, () -> Knight.from(WHITE));
        WHITE_PIECE.put(PieceType.BISHOP, () -> Bishop.from(WHITE));
        WHITE_PIECE.put(PieceType.PAWN, () -> Pawn.from(WHITE));
    }

    public static Piece toPiece(final PieceType pieceType, final Team team) {
        if (team.isBlack()) {
            return BLACK_PIECE.get(pieceType).get();
        }
        if (team.isWhite()) {
            return WHITE_PIECE.get(pieceType).get();
        }
        if (pieceType == PieceType.EMPTY) {
            return new EmptyPiece();
        }
        throw new UnsupportedOperationException("그런 말은 없습니다");
    }

}
