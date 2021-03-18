package chess.view;

import chess.domain.Position;

import java.util.Map;

public class OutputView {
    private static final String CHESS_START_MESSAGE = "체스 게임을 시작합니다.";

    private OutputView() {
    }

    public static void printChessStartMessage() {
        System.out.println(CHESS_START_MESSAGE);

    }

    public static void printChessBoard(final Map<Position, String> chessBoard) {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                System.out.print(chessBoard.getOrDefault(new Position(j, i), "."));
            }
            System.out.println();
        }
    }
}
