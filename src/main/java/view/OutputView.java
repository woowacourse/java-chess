package view;

import domain.Player;
import domain.Status;
import domain.chessgame.ChessBoard;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.Arrays;
import java.util.Collections;

public class OutputView {

    private static final String STATUS_MESSAGE = "%s 현재 점수 : %.1f";
    private static final String GAME_EXIT_MESSAGE = "게임이 종료되었습니다.";
    private static final String GAME_RESULT_MESSAGE = "[현재 게임 승패 결과]";
    private static final String RESULT_DRAW = "무승부";
    private static final String RESULT_WIN = "승자 : ";

    public static void printBoard(final ChessBoard chessBoard) {
        Rank[] ranks = Rank.values();
        Arrays.sort(ranks, Collections.reverseOrder());
        for (Rank rank : ranks) {
            printBoardByRank(chessBoard, rank);
        }
        System.out.println();
    }

    private static void printBoardByRank(final ChessBoard chessBoard, final Rank rank) {
        for (File file : File.values()) {
            System.out.print(chessBoard.findPiece(Position.of(file, rank)).symbolByPlayer());
        }
        System.out.println();
    }

    public static void printExitMessage() {
        System.out.println(GAME_EXIT_MESSAGE);
    }

    public static void printStatus(final Status status) {
        printWinner(status);
        System.out.printf(STATUS_MESSAGE, Player.WHITE, status.getWhiteScore());
        System.out.println();
        System.out.printf(STATUS_MESSAGE, Player.BLACK, status.getBlackScore());
        System.out.println();
    }

    private static void printWinner(Status status) {
        System.out.println(GAME_RESULT_MESSAGE);
        if (status.getWinner() == null) {
            System.out.println(RESULT_DRAW);
            return;
        }
        System.out.println(RESULT_WIN + status.getWinner());
    }

    public static void printErrorMessage(final String errorMessage) {
        System.out.println(errorMessage);
    }
}
