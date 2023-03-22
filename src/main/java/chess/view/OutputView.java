package chess.view;

import chess.controller.BoardDTO;
import chess.domain.game.Status;
import chess.domain.piece.Color;
import chess.domain.piece.Score;
import java.util.Map;

public class OutputView {
    
    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.\n"
            + "> 게임 시작 : start\n"
            + "> 게임 종료 : end\n"
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    
    private static final Map<Color, String> COLOR_MAP = Map.of(
            Color.WHITE, "흰색",
            Color.BLACK, "검은색"
    );
    
    
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
    
    public void printStatus(final Status status) {
        this.printColorScore(status, Color.WHITE);
        this.printColorScore(status, Color.BLACK);
        this.printWinner(status.getWinner(), status.getScoreDifference());
        System.out.println();
    }
    
    private void printWinner(final Color winner, final double scoreDifference) {
        if (winner == Color.NONE) {
            System.out.println("비기고 있습니다.");
            return;
        }
        System.out.println(COLOR_MAP.get(winner) + "이 " + scoreDifference + "점 앞서고 있습니다.");
    }
    
    private void printColorScore(final Status status, final Color color) {
        Score score = status.getScore(color);
        System.out.println(COLOR_MAP.get(color) + "의 점수: " + score.getScore());
    }
    
}
