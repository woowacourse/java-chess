package chess.view;

import chess.controller.dto.BoardDto;
import chess.controller.dto.TeamDto;
import chess.controller.dto.StatusDto;

import java.util.List;

public final class OutputView {

    public static void printInitialMessage() {
        System.out.println(
                "> 체스 게임을 시작합니다.\n"
                        + "> 게임 시작 : start\n"
                        + "> 게임 종료 : end\n"
                        + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(final BoardDto boardDto) {
        boardDto.getBoard().forEach(line -> System.out.println(printLine(line)));
        System.out.println();
    }

    private static String printLine(final List<String> line) {
        return String.join("", line);
    }

    public static void printErrorMessage(final String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void printStatus(final StatusDto statusDto) {
        String team = statusDto.getTeam();
        String score = statusDto.getScore();
        System.out.println("> " + team + ": " + score + "점");
    }

    public static void printTurn(final TeamDto teamDto) {
        String turn = teamDto.getTeam();
        if(turn.isBlank()){
            return;
        }
        System.out.println("> " + turn + " 기물을 움직일 차례입니다.");
    }


    public static void printWinTeam(final TeamDto teamDto) {
        String winTeam = teamDto.getTeam();
        if (winTeam.isBlank()) {
            System.out.println("> 무승부 입니다!");
            return;
        }
        System.out.println("> " + winTeam + "이 이겼습니다!");
    }

    public static void printFinishMessage() {
        System.out.println("> 게임이 종료되었습니다.");
    }
}
