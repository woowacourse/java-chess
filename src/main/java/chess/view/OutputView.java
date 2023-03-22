package chess.view;

import chess.controller.BoardDTO;

public class OutputView {
    
    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.\n"
            + "> 게임 시작 : start\n"
            + "> 게임 종료 : end\n"
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    
    public void printGameStartMessage() {
        System.out.println(GAME_START_MESSAGE);
        System.out.println();
    }
    
    
    public void printBoard(final BoardDTO boardDTO) {
        System.out.println(boardDTO.getBoard());
        System.out.println();
    }
    
    public void printError(final String message) {
        System.out.println(message);
        System.out.println();
    }
}
