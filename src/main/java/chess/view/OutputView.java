package chess.view;

import chess.controller.BoardDto;
import chess.controller.RankDto;

public class OutputView {
    
    public static final String ERROR_PREFIX = "[ERROR] ";
    private static final String GAME_HEADER = "> 체스 게임을 시작합니다.";
    private static final String GAME_START_INFO_MESSAGE = "> 게임 시작 : start";
    private static final String GAME_END_INFO_MESSAGE = "> 게임 종료 : end";
    private static final String GAME_MOVE_INFO_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";

    public void printGameStartMessage() {
        System.out.println(GAME_HEADER);
        System.out.println(GAME_START_INFO_MESSAGE);
        System.out.println(GAME_END_INFO_MESSAGE);
        System.out.println(GAME_MOVE_INFO_MESSAGE);
    }
    
    public void printBoard(final BoardDto boardDto) {
        for (RankDto rank : boardDto.getRanks()) {
            System.out.println(rank.getStringRank());
        }
        System.out.println();
    }
    
    public void printError(final String message) {
        System.out.println(ERROR_PREFIX + message);
        System.out.println();
    }
}
