package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import domain.board.File;
import domain.board.Rank;
import domain.board.Square;
import domain.piece.Piece;
import domain.piece.slider.Slider;

public class OutputView {
    public static void printChessBoard(Map<Square, Piece> board) {
        if (board.values().stream().allMatch(Piece::isEmpty)) {
            return;
        }
        List<String> messages = new ArrayList<>();
        for (Rank value : Rank.values()) {
            StringBuilder stringBuilder = new StringBuilder();
            addPieceName(board, value, stringBuilder);
            messages.add(stringBuilder.toString());
        }
        Collections.reverse(messages);
        for (String message : messages) {
            System.out.println(message);
        }
    }

    private static void addPieceName(Map<Square, Piece> board, Rank value, StringBuilder stringBuilder) {
        for (File file : File.values()) {
            stringBuilder.append(pieceToString(board.get(new Square(file, value))));
        }
    }

    private static String pieceToString(Piece piece) {
        if (piece.isPawn()) {
            return Type.PAWN.convertToCamp(piece);
        }
        if (piece.isRook()) {
            return Type.ROOK.convertToCamp(piece);
        }
        if (piece.isBishop()) {
            return Type.BISHOP.convertToCamp(piece);
        }
        if (piece.isKnight()) {
            return Type.KNIGHT.convertToCamp(piece);
        }
        if (piece.isQueen()) {
            return Type.QUEEN.convertToCamp(piece);
        }
        if (piece.isKing()) {
            return Type.KING.convertToCamp(piece);
        }
        return Type.EMPTY.convertToCamp(piece);
    }

    public static void printChessInfo() {
        System.out.println("> 체스 게임을 시작합니다.\n"
            + "> 게임 시작 : start\n"
            + "> 게임 종료 : end\n"
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printErrorMessage(RuntimeException e) {
        System.out.println(e.getMessage());
    }

    private enum Type {
        PAWN("p"),
        ROOK("r"),
        BISHOP("b"),
        KNIGHT("n"),
        QUEEN("q"),
        KING("k"),
        EMPTY(".");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String convertToCamp(Slider slider) {
            if (slider.isWhite()) {
                return this.value;
            }

            return value.toUpperCase();
        }
    }

}
