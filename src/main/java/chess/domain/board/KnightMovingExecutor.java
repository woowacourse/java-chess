package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.route.Route;

import java.util.Map;

public class KnightMovingExecutor extends AbstractMovingExecutor {
    public KnightMovingExecutor(Piece piece) {
        super(piece);
    }

    @Override
    public void move(Route route, Map<Position, Piece> board, Position fromPosition, Position toPosition) {
        removePieceOnTheWay(route, board);

        board.remove(fromPosition);
        board.put(toPosition, piece);
    }

    private void removePieceOnTheWay(Route route, Map<Position, Piece> board) {
        for (Position position : route.getRoute()) {
            Piece pieceToRemove = board.get(position);
            board.remove(pieceToRemove);
        }
    }
}
