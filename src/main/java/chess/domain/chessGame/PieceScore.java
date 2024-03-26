package chess.domain.chessGame;

import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import java.util.Arrays;

enum PieceScore {

    EMPTY(EmptyPiece.class, 0.0),
    BLACK_PAWN(BlackPawn.class, 1.0),
    WHITE_PAWN(WhitePawn.class, 1.0),
    ROOK(Rook.class, 5.0),
    KNIGHT(Knight.class, 2.5),
    BISHOP(Bishop.class, 3.0),
    QUEEN(Queen.class, 9.0),
    KING(King.class, 0.0);

    private final Class<? extends Piece> pieceClass;
    private final double score;

    PieceScore(Class<? extends Piece> pieceClass, double score) {
        this.pieceClass = pieceClass;
        this.score = score;
    }

    public static double findScore(Piece piece) {
        return Arrays.stream(values())
                .filter(value -> value.pieceClass == piece.getClass())
                .map(value -> value.score)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("점수 계산할 수 없는 Piece입니다"));
    }
}
