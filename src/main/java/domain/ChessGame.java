package domain;

import domain.piece.Pawn;
import domain.piece.Piece;
import java.util.List;

public class ChessGame {

    private final ChessBoard chessBoard;
    private final Rule rule;

    public ChessGame() {
        chessBoard = new ChessBoard();
        rule = new Rule();
    }

    public Piece find(Square square) {
        return chessBoard.find(square);
    }

    public void move(Square src, Square dest) {
        Piece piece = chessBoard.find(src);
        rule.validateOrder(piece);
        List<Square> routes = piece.findRoutes(src, dest);

        checkPawn(src, dest, piece);
        go(src, dest, piece, routes);
        rule.nextOrder();
    }

    private void checkPawn(Square src, Square dest, Piece piece) {
        if (piece.isPawn()) {
            Pawn pawn = (Pawn) piece;
            validatePawnMove(pawn, src, dest);
            pawn.start();
        }
    }

    private void validatePawnMove(Pawn pawn, Square src, Square dest) {
        if (pawn.isDiagonal(src, dest) && !canKill(dest, pawn, dest)) {
            throw new IllegalArgumentException("대각선으로 갈 수 없습니다.");
        }
        if (pawn.isLinear(src, dest) && chessBoard.hasPiece(dest)) {
            throw new IllegalArgumentException("폰은 기물이 있으면 앞으로 갈 수 없습니다.");
        }
    }
    
    private boolean canKill(Square dest, Piece piece, Square route) {
        return route == dest && chessBoard.hasPiece(route) && piece.isDifferentTeam(chessBoard.find(dest));
    }

    private void go(Square src, Square dest, Piece piece, List<Square> routes) {
        for (Square route : routes) {
            validateBlock(dest, piece, route);
        }
        chessBoard.update(src, dest);
    }

    private void validateBlock(Square dest, Piece piece, Square route) {
        if (chessBoard.hasPiece(route) && !canKill(dest, piece, route)) {
            throw new IllegalArgumentException("중간에 기물이 있어 이동할 수 없습니다.");
        }
    }
}
