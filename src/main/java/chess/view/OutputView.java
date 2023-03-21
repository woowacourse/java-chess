package chess.view;

import chess.domain.Color;
import chess.domain.piece.Piece;

import java.util.Collections;
import java.util.List;

import static chess.domain.Role.*;

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
        if (piece.hasSameRole(PAWN) || piece.hasSameRole(INITIAL_PAWN)) {
            return convertSide(piece, "p");
        }
        if (piece.hasSameRole(ROOK)) {
            return convertSide(piece, "r");
        }
        if (piece.hasSameRole(KNIGHT)) {
            return convertSide(piece, "n");
        }
        if (piece.hasSameRole(BISHOP)) {
            return convertSide(piece, "b");
        }
        if (piece.hasSameRole(QUEEN)) {
            return convertSide(piece, "q");
        }
        if (piece.hasSameRole(KING)) {
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
