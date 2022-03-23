package chess.view;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;

public class OutputView {
    private static final String SEPARATOR = System.lineSeparator();
    private static final String INIT_MESSAGE = "체스 게임을 시작합니다." + SEPARATOR
            + "게임 시작은 start, 종료는 end 명령을 입력하세요.";

    public static void printInitMessage() {
        System.out.println(INIT_MESSAGE);
    }

    public static void printChessBoard(Board board) {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                System.out.print(board.findByPosition(new Position(file, rank)).getName());
            }
            System.out.println();
        }
        System.out.println();
    }
}
