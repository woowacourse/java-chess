package domain;

import domain.piece.Piece;
import java.util.List;

public class ChessGame {

    private final ChessBoard chessBoard;
    private final Turn turn;

    public ChessGame() {
        chessBoard = new ChessBoard();
        turn = new Turn();
    }

    public Piece find(Square square) {
        return chessBoard.find(square);
    }

    public void move(Square source, Square destination) {
        Piece piece = chessBoard.find(source);
        turn.validateOrder(piece);
        List<Square> routes = piece.findRoutes(source, destination, chessBoard.find(destination));

        validateRoutes(destination, piece, routes);
        chessBoard.update(source, destination);
        turn.nextOrder();
    }

    private boolean canNotKill(Square destination, Piece piece, Square route) {
        return route != destination || chessBoard.isBlank(route) || piece.isSameTeam(chessBoard.find(destination));
    }

    private boolean canKill(Square destination, Piece piece, Square route) {
        return route == destination && chessBoard.hasPiece(route) && piece.isDifferentTeam(chessBoard.find(destination));
    }

    private void validateRoutes(Square destination, Piece piece, List<Square> routes) {
        for (Square route : routes) {
            validateBlock(destination, piece, route);
        }
    }

    private void validateBlock(Square destination, Piece piece, Square route) {
        if (chessBoard.hasPiece(route) && canNotKill(destination, piece, route)) {
            throw new IllegalArgumentException("중간에 기물이 있어 이동할 수 없습니다.");
        }
    }
}
