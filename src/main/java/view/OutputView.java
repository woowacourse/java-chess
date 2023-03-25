package view;

import java.util.Arrays;
import java.util.List;

import org.w3c.dom.ls.LSOutput;

import dto.BoardResponseDto;
import dto.ScoreResponseDto;

public class OutputView {
    private OutputView() {
    }

    public static void printChessInfo() {
        System.out.println("> 체스 게임을 시작합니다.\n"
            + "> 게임 시작 : start\n"
            + "> 게임 종료 : end\n"
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(BoardResponseDto boardResponseDto) {
        List<String> board = boardResponseDto.getBoard();
        if (board.stream()
            .allMatch(rank -> Arrays.stream(rank.split(""))
                .allMatch(piece -> piece.equals(".")))) {
            return;
        }

        for (String rank : board) {
            System.out.println(rank);
        }
    }

    public static void printScore(ScoreResponseDto scoreResponseDto) {
        scoreResponseDto.getScore().forEach((camp, score) -> System.out.println(camp + " : " + score));
    }

    public static void printErrorMessage(RuntimeException e) {
        System.out.println(e.getMessage());
    }
}
