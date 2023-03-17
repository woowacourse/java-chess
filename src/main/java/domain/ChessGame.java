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
        validateNotExist(piece);
        rule.validateOrder(piece);
        List<Square> routes = piece.findRoutes(src, dest);

        checkPawn(src, dest, piece);
        go(src, dest, piece, routes);
        rule.nextOrder();
    }

    private void validateNotExist(Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다.");
        }
    }

    private void checkPawn(Square src, Square dest, Piece piece) {
        if (piece instanceof Pawn) {
            Pawn pawn = (Pawn) piece;
            validatePawnMove(pawn, src, dest);
            pawn.start();
        }
    }

    private void validatePawnMove(Pawn pawn, Square src, Square dest) {
        if (pawn.isDiagonal(src, dest) && !canKill(dest, pawn, dest)) {
            throw new IllegalArgumentException("대각선으로 갈 수 없습니다.");
        }
        if (!pawn.isDiagonal(src, dest) && canKill(dest, pawn, dest)) {
            throw new IllegalArgumentException("앞으로 갈 수 없습니다.");
        }
    }

    private boolean canKill(Square dest, Piece piece, Square route) {
        return route == dest && hasPiece(route) && piece.isDifferentTeam(chessBoard.find(dest));
    }

    private boolean hasPiece(Square route) {
        return chessBoard.containsKey(route);
    }

    private void go(Square src, Square dest, Piece piece, List<Square> routes) {
        for (Square route : routes) {
            validateBlock(dest, piece, route);
        }
        chessBoard.update(src, dest);
    }

    private void validateBlock(Square dest, Piece piece, Square route) {
        if (hasPiece(route) && !canKill(dest, piece, route)) {
            throw new IllegalArgumentException("갈 수 없습니다!");
        }
    }
}
