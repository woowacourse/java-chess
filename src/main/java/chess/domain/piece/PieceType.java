package chess.domain.piece;

import chess.domain.piece.nonsliding.King;
import chess.domain.piece.nonsliding.Knight;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.BlackStartPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.piece.pawn.WhiteStartPawn;
import chess.domain.piece.sliding.Bishop;
import chess.domain.piece.sliding.Queen;
import chess.domain.piece.sliding.Rook;

import java.util.function.Function;

public enum PieceType {
    KING(King::new, 0),
    QUEEN(Queen::new, 9),
    ROOK(Rook::new, 5),
    KNIGHT(Knight::new, 2.5),
    BISHOP(Bishop::new, 3),
    WHITE_START_PAWN(WhiteStartPawn::new, 1),
    BLACK_START_PAWN(BlackStartPawn::new, 1),
    WHITE_PAWN(WhitePawn::new, 1),
    BLACK_PAWN(BlackPawn::new, 1),
    EMPTY(ignore -> Empty.getInstance(), 0);
    
    private final Function<Team, Piece> pieceMaker;
    private final double score;
    
    PieceType(Function<Team, Piece> pieceMaker, double score) {
        this.pieceMaker = pieceMaker;
        this.score = score;
    }
    
    public Piece makePiece(Team team) {
        return pieceMaker.apply(team);
    }
    
    public double score() {
        return score;
    }
}
