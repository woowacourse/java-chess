package view;

import domain.Board;
import domain.chesspiece.Chesspiece;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static final String NEW_LINE = "\n";

    public static void printBoard(Board board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (List<Chesspiece> row : board.getChess()) {
            String rowString = row.stream()
                    .map(Chesspiece::getInitial)
                    .collect(Collectors.joining());

            stringBuilder.append(rowString)
                    .append(NEW_LINE);
        }

        System.out.println(stringBuilder.toString());
    }
}
