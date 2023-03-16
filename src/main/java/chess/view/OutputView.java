package chess.view;

import chess.domain.Color;
import chess.domain.piece.*;

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
        if (piece instanceof Pawn) {
            return convertSide((MovablePiece) piece, "p");
        }
        if (piece instanceof Rook) {
            return convertSide((MovablePiece) piece, "r");
        }
        if (piece instanceof Knight) {
            return convertSide((MovablePiece) piece, "n");
        }
        if (piece instanceof Bishop) {
            return convertSide((MovablePiece) piece, "b");
        }
        if (piece instanceof Queen) {
            return convertSide((MovablePiece) piece, "q");
        }
        if (piece instanceof King) {
            return convertSide((MovablePiece) piece, "k");
        }
        return ".";
    }

    private String convertSide(final MovablePiece piece, final String convertedPiece) {
        if (piece.getColor().equals(Color.BLACK)) {
            return convertedPiece.toUpperCase();
        }
        return convertedPiece.toLowerCase();
    }
}
