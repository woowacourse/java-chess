package chess.domain.piece.move;

import chess.domain.board.Board;
import chess.domain.piece.pawn.InitializedPawn;
import chess.domain.piece.state.Initialized;
import chess.domain.position.Position;

public class InitializedPawnHasHindrance extends HasHindrance {
    @Override
    public boolean canNotMove(Initialized initializedPiece, Position to, Board board) {
        //질문: 이렇게 타입 캐스팅 해도 괜찮을까요?
        InitializedPawn initializedPawn = (InitializedPawn) initializedPiece;
        return initializedPawn.hasHindrance(to, board);
    }
}
