package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.route.Route;
import chess.domain.route.RouteToAttack;

import java.util.Map;

public class PawnMovingExecutor extends AbstractMovingExecutor {
    public PawnMovingExecutor(Piece piece) {
        super(piece);
    }

    @Override
    public void move(Route route, Map<Position, Piece> board, Position fromPosition, Position toPosition) {
        if (route instanceof RouteToAttack) {
            Piece pieceToAttack = board.get(toPosition);
            if (pieceToAttack != null || pieceToAttack.isOppositeTeam(piece.getTeam())) {
                board.remove(fromPosition);
                board.put(toPosition, piece);
                return;
            }
            return;
        }

        if (board.get(toPosition) == null) {
            board.remove(fromPosition);
            board.put(toPosition, piece);
            return;
        }
    }
}
