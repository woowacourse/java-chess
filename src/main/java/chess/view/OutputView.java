package chess.view;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String SEPARATOR = System.lineSeparator();
    private static final String ARROW = "> ";
    private static final String INIT_MESSAGE =
            ARROW + "체스 게임을 시작합니다." + SEPARATOR
                    + ARROW + "게임 시작 : start" + SEPARATOR
                    + ARROW + "게임 종료 : end" + SEPARATOR
                    + ARROW + "게임 이동 : move source위치 target위치 - 예. move b2 b3";

    public static void printInitMessage() {
        System.out.println(INIT_MESSAGE);
    }

    public static void printChessBoard(Board board) {
        List<Rank> ranks = Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        for (Rank rank : ranks) {
            for (File file : File.values()) {
                System.out.print(board.findByPosition(new Position(file, rank)).getName());
            }
            System.out.println();
        }
        System.out.println();
    }
}
