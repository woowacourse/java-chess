package chess.view;

import chess.controller.BoardDto;

public class OutputView {
    
    private static final String GAME_START_MESSAGE = "체스 게임을 시작합니다.";
    
    public void printGameStartMessage() {
        System.out.println(GAME_START_MESSAGE);
    }
    
    public void printBoard(final BoardDto boardDto) {
        for (String stringRank : boardDto.getStringPieces()) {
            System.out.println(stringRank);
        }
        
    }
}
