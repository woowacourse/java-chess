package domain;

import java.util.List;

import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.PieceLocations;

public class ChessBoard {

    private final PieceLocations pieceLocations;

    public ChessBoard() {
        pieceLocations = new PieceLocations();
    }

    public Piece find(Square square) {
        return pieceLocations.find(square);
    }

    public void move(Square src, Square dest) {
        Piece piece = pieceLocations.find(src);
        validateNotExist(piece);

        List<Square> routes = piece.findRoutes(src, dest);
        validateNoRoutes(routes);

        prepareWhenPawn(src, dest, piece);

        go(src, dest, piece, routes);
    }

    private void validateNotExist(Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다.");
        }
    }

    private void validateNoRoutes(List<Square> routes) {
        if (routes.isEmpty()) {
            throw new IllegalArgumentException("행마가 달라 갈 수 없습니다!");
        }
    }

    private void prepareWhenPawn(Square src, Square dest, Piece piece) {
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
        return route == dest && hasPiece(route) && pieceLocations.find(dest).isDifferentTeam(piece);
    }

    private void go(Square src, Square dest, Piece piece, List<Square> routes) {
        for (Square route : routes) {
            validateSameTeamNoKill(dest, piece, route);
        }
        pieceLocations.update(src, dest);
    }

    private void validateSameTeamNoKill(Square dest, Piece piece, Square route) {
        if (hasPiece(route) && !canKill(dest, piece, route)) {
            throw new IllegalArgumentException("기물에 막혀 있어 갈 수 없습니다!");
        }
    }

    private boolean hasPiece(Square route) {
        return pieceLocations.hasPiece(route);
    }
}
