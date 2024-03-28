package chess.view;

import chess.dto.BoardDTO;
import chess.dto.RankDTO;
import chess.dto.ScoreDTO;

import java.util.stream.Collectors;

public class OutputView {
    public void printGameIntro() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(BoardDTO boardDTO) {
        String board = boardDTO.board().stream()
                .map(RankDTO::line)
                .map(line -> String.join("", line))
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(board);
    }

    public void printScore(ScoreDTO scoreDTO) {
        System.out.printf("Black 점수: %.1f, White 점수: %.1f\n",
                scoreDTO.blackScore(), scoreDTO.whiteScore());
    }

    public void printWinner(String winnerColor) {
        System.out.println(winnerColor + "가 승리해서 게임이 종료되었습니다.");
    }

    public void printException(String message) {
        System.out.println("[ERROR] " + message);
    }
}
