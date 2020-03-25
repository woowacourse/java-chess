package view;

import domain.board.Board;
import domain.pieces.Piece;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEW_LINE = "\n";

    public static void printStart() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void printBoard(Board board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (List<Piece> row : board.getChess()) {
            String rowString = row.stream()
                    .map(Piece::getInitial)
                    .collect(Collectors.joining());

            stringBuilder.append(rowString)
                    .append(NEW_LINE);
        }

        System.out.println(stringBuilder.toString());
    }
}
