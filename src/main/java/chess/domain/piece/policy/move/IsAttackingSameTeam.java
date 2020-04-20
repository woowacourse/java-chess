package chess.domain.piece.policy.move;


import chess.domain.piece.Piece;
import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Position;
import chess.domain.piece.state.piece.Initialized;

public class IsAttackingSameTeam implements CanNotMoveStrategy {
    //피스가 나음
    //todo: refac
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        Initialized initializedPiece = (Initialized) piecesState.getPiece(from);
        Piece exPiece = piecesState.getPiece(to);
        return initializedPiece.isSameTeam(exPiece);
    }
}
