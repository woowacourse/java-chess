package domain;

import domain.piece.Pawn;
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
        List<Square> routes = piece.findRoutes(source, destination);

        checkPawn(source, destination, piece);
        go(source, destination, piece, routes);
        turn.nextOrder();
    }

    private void checkPawn(Square source, Square destination, Piece piece) {
        if (piece.isPawn()) {
            Pawn pawn = (Pawn) piece;
            validatePawnMove(pawn, source, destination);
            pawn.changeState();
        }
    }

    private void validatePawnMove(Pawn pawn, Square source, Square destination) {
        if (pawn.isDiagonal(source, destination) && !canKill(destination, pawn, destination)) {
            throw new IllegalArgumentException("대각선으로 갈 수 없습니다.");
        }
        if (pawn.isLinear(source, destination) && chessBoard.hasPiece(destination)) {
            throw new IllegalArgumentException("폰은 기물이 있으면 앞으로 갈 수 없습니다.");
        }
    }

    private boolean canKill(Square destination, Piece piece, Square route) {
        return route == destination && chessBoard.hasPiece(route) && piece.isDifferentTeam(chessBoard.find(destination));
    }

    private void go(Square source, Square destination, Piece piece, List<Square> routes) {
        for (Square route : routes) {
            validateBlock(destination, piece, route);
        }
        chessBoard.update(source, destination);
    }

    private void validateBlock(Square destination, Piece piece, Square route) {
        if (chessBoard.hasPiece(route) && !canKill(destination, piece, route)) {
            throw new IllegalArgumentException("중간에 기물이 있어 이동할 수 없습니다.");
        }
    }
}
