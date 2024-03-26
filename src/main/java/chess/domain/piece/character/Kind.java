package chess.domain.piece.character;

import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import chess.domain.piece.abstractPiece.Piece;
import java.util.function.BiFunction;

public enum Kind {
    PAWN(1, (team, isMoved) -> {
        if (team == Team.WHITE) {
            return new WhitePawn(isMoved);
        }
        return new BlackPawn(isMoved);
    }),
    KNIGHT(2.5, Knight::new),
    BISHOP(3, Bishop::new),
    ROOK(5, Rook::new),
    QUEEN(9, Queen::new),
    KING(0, King::new),
    ;

    private final double point;
    private final BiFunction<Team, Boolean, Piece> createPiece;

    Kind(double point, BiFunction<Team, Boolean, Piece> createPiece) {
        this.point = point;
        this.createPiece = createPiece;
    }

    public double point() {
        return point;
    }

    public Piece createPiece(Team team, boolean isMoved) {
        return createPiece.apply(team, isMoved);
    }
}
