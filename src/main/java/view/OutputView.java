package view;

import domain.Player;
import domain.chessboard.ChessBoard;
import domain.dto.StatusDTO;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.Arrays;
import java.util.Collections;

public class OutputView {

    private static final String STATUS_MESSAGE = "%s 현재 점수 : %.1f (%s)" + System.lineSeparator();
    private static final String KING_ATTACK_MESSAGE = "%s의 King이 공격당하였습니다." + System.lineSeparator();
    private static final String GAME_EXIT_MESSAGE = "게임이 종료되었습니다.";

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
            System.out.print(chessBoard.getSymbol(Position.of(file, rank)));
        }
        System.out.println();
    }

    public static void printAttackKingMessage(final Player player) {
        System.out.printf(KING_ATTACK_MESSAGE, player);
        printExitMessage();
    }

    public static void printExitMessage() {
        System.out.println(GAME_EXIT_MESSAGE);
    }

    public static void printStatus(final StatusDTO statusDTO) {
        System.out.printf(STATUS_MESSAGE, statusDTO.getPlayer(), statusDTO.getScore(),
            statusDTO.getResult());
    }

    public static void printErrorMessage(final String errorMessage) {
        System.out.println(errorMessage);
    }
}
