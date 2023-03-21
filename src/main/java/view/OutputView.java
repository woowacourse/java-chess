package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import domain.board.File;
import domain.board.Rank;
import domain.board.Square;
import domain.piece.Piece;

public class OutputView {
    public static void printChessBoard(Map<Square, Piece> board) {
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
            stringBuilder.append(pieceToString(board.get(Square.of(file, value))));
        }
    }

    private static char pieceToString(Piece piece) {
        char label = piece.getType().getLabel();
        if (!piece.isWhite()) {
            return Character.toUpperCase(label);
        }
        return label;
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
}
