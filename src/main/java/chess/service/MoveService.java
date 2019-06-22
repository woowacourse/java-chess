package chess.service;

import chess.domain.board.Board;
import chess.domain.direction.Route;
import chess.domain.direction.core.Square;
import chess.domain.piece.core.Piece;

public class MoveService {
    public static MoveService getInstance() {
        return MovieServiceHolder.INSTANCE;
    }

    public Board request(Square source, Square target) {
        Board board = Board.drawBoard();
        Piece piece = board.getPiece(source);
        Route route = piece.getRoute(source, target);
        return board.changeBoard(route);
    }

    private static class MovieServiceHolder {
        static final MoveService INSTANCE = new MoveService();
    }

}
