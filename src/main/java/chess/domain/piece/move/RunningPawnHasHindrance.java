package chess.domain.piece.move;

import chess.domain.board.Board;
import chess.domain.piece.pawn.RunningPawn;
import chess.domain.piece.state.Initialized;
import chess.domain.position.Position;

public class RunningPawnHasHindrance extends HasHindrance {
    @Override
    public boolean canNotMove(Initialized initializedPiece, Position to, Board board) {
        RunningPawn pawn = (RunningPawn) initializedPiece;
        return pawn.hasHindrance(to, board);
    }
}
