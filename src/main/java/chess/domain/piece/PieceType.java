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
    KING(King::new),
    QUEEN(Queen::new),
    ROOK(Rook::new),
    KNIGHT(Knight::new),
    BISHOP(Bishop::new),
    WHITE_START_PAWN(WhiteStartPawn::new),
    BLACK_START_PAWN(BlackStartPawn::new),
    WHITE_PAWN(WhitePawn::new),
    BLACK_PAWN(BlackPawn::new),
    EMPTY(ignore -> Empty.getInstance());
    
    private final Function<Team, Piece> pieceMaker;
    
    PieceType(Function<Team, Piece> pieceMaker) {
        this.pieceMaker = pieceMaker;
    }
    
    public Piece makePiece(Team team) {
        return pieceMaker.apply(team);
    }
}
