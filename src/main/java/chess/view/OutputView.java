package chess.view;

import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import chess.domain.side.Color;

import java.util.Collections;
import java.util.List;

public class OutputView {
    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(final List<List<Piece>> pieces) {
        Collections.reverse(pieces);
        for (List<Piece> rank : pieces) {
            for (Piece piece : rank) {
                System.out.print(convertPiece(piece));
            }
            System.out.println();
        }
    }

    private String convertPiece(final Piece piece) {
        if (piece.isRole(Role.PAWN) || piece.isRole(Role.INITIAL_PAWN)) {
            return convertSide(piece, "p");
        }
        if (piece.isRole(Role.ROOK)) {
            return convertSide(piece, "r");
        }
        if (piece.isRole(Role.KNIGHT)) {
            return convertSide(piece, "n");
        }
        if (piece.isRole(Role.BISHOP)) {
            return convertSide(piece, "b");
        }
        if (piece.isRole(Role.QUEEN)) {
            return convertSide(piece, "q");
        }
        if (piece.isRole(Role.KING)) {
            return convertSide(piece, "k");
        }
        return ".";
    }

    private String convertSide(final Piece piece, final String convertedPiece) {
        if (piece.getColor().equals(Color.BLACK)) {
            return convertedPiece.toUpperCase();
        }
        return convertedPiece.toLowerCase();
    }
}
