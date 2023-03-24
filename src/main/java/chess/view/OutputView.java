package chess.view;

import chess.controller.dto.BoardDto;
import chess.controller.dto.RankDto;
import chess.domain.Color;
import java.util.Map;

public class OutputView {
    
    public static final String ERROR_PREFIX = "[ERROR] ";
    private static final String GAME_HEADER = "> 체스 게임을 시작합니다.";
    private static final String GAME_START_INFO_MESSAGE = "> 게임 시작 : start";
    private static final String GAME_END_INFO_MESSAGE = "> 게임 종료 : end";
    private static final String GAME_MOVE_INFO_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String STATUS_MESSAGE = "현재 %s의 점수는 %.1f입니다.";
    private static final String KING_CATCH_MESSAGE = "King을 잡았습니다. 게임이 종료됩니다.";
    private static final String WINNER_MESSAGE = "우승자는 %s입니다.";
    private static final String TIE_MESSAGE = "무승부입니다.";
    private static final Map<Color, String> color = Map.of(Color.WHITE, "화이트", Color.BLACK, "블랙");

    public static void printGameStartMessage() {
        System.out.println(GAME_HEADER);
        System.out.println(GAME_START_INFO_MESSAGE);
        System.out.println(GAME_END_INFO_MESSAGE);
        System.out.println(GAME_MOVE_INFO_MESSAGE);
    }
    
    public static void printBoard(final BoardDto boardDto) {
        for (RankDto rank : boardDto.getRanks()) {
            System.out.println(rank.getStringRank());
        }
        System.out.println();
    }

    public static void printStatus(double whitePoint, double blackPoint) {
        System.out.println(String.format(STATUS_MESSAGE, color.get(Color.WHITE), whitePoint));
        System.out.println(String.format(STATUS_MESSAGE, color.get(Color.BLACK), blackPoint));
        printWinner(whitePoint, blackPoint);
    }

    private static void printWinner(double whitePoint, double blackPoint) {
        if (whitePoint > blackPoint) {
            System.out.println(String.format(WINNER_MESSAGE, color.get(Color.WHITE)));
            System.out.println();
            return;
        }
        if (whitePoint < blackPoint) {
            System.out.println(String.format(WINNER_MESSAGE, color.get(Color.BLACK)));
            System.out.println();
            return;
        }
        System.out.println(TIE_MESSAGE);
        System.out.println();
    }

    public static void printResultWhenKingCatch(Color winner) {
        System.out.println(KING_CATCH_MESSAGE);
        System.out.println(String.format(WINNER_MESSAGE, color.get(winner)));
    }

    public static void printError(final String message) {
        System.out.println(ERROR_PREFIX + message);
        System.out.println();
    }
}
