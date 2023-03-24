package chess.view;

import java.util.List;

import chess.domain.dto.GameStatusDto;
import chess.domain.piece.Team;

public class OutputView {

    private static final String MESSAGE_PREFIX = "> ";

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public static void printLoginMessage() {
        printMessage("로그인 : login");
        printMessage("회원가입 : register");
    }

    public static void printIdMessage() {
        System.out.print("id : ");
    }
    public static void printIdInputMessage() {
        System.out.print("사용하고 싶은 id를 입력하세요 : ");
    }

    public static void printNicknameInputMessage() {
        System.out.print("사용하고 싶은 닉네임을 입력하세요 : ");
    }

    public static void printStartMessage() {
        printMessage("체스 게임을 시작합니다.");
    }

    public static void printCommandMessage() {
        printMessage("게임 시작 : start");
        printMessage("게임 종료 : end");
        printMessage("게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    private static void printMessage(final String message) {
        System.out.println(MESSAGE_PREFIX + message);
    }

    public static void printGameStatus(final GameStatusDto gameStatusDto) {
        List<String> gameStatus = gameStatusDto.getGameStatus();
        gameStatus.forEach(OutputView::printRankStatus);
    }

    private static void printRankStatus(final String rankStatus) {
        System.out.println(rankStatus);
    }

    public static void printWinner(Team winner) {
        if (winner == Team.WHITE) {
            System.out.println("백이 승리했습니다!");
        }
        System.out.println("흑이 승리했습니다!");
    }

    public static void printWhitePoint(double point) {
        System.out.println("백의 기물 점수: " + point);
    }

    public static void printBlackPoint(double point) {
        System.out.println("흑의 기물 점수: " + point);
    }

}
