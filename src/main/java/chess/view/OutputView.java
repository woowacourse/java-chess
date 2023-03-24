package chess.view;

import java.util.List;

import chess.domain.Game;
import chess.domain.dto.GameStatusDto;
import chess.domain.piece.Team;

public class OutputView {

    private static final String MESSAGE_PREFIX = "> ";

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public static void printLoginMessage() {
        System.out.println("체스 게임에 오신 걸 환영합니다!");
        printMessage("로그인 : login");
        printMessage("회원가입 : register");
        System.out.println();
    }

    public static void printIdMessage() {
        System.out.print("id를 입력해주세요. : ");
    }
    public static void printIdInputMessage() {
        System.out.print("사용하고 싶은 id를 입력하세요 : ");
    }

    public static void printNicknameInputMessage() {
        System.out.print("사용하고 싶은 닉네임을 입력하세요 : ");
    }

    public static void printPlayNewGameMessage() {
        System.out.println("새롭게 게임을 시작할 지 진행 중이던 게임을 이어할 지 선택해주세요.");
        printMessage("새로운 게임 : new");
        printMessage("이어하기 : exist");
        System.out.println();
    }

    public static void printNoGameExistMessage() {
        System.out.println("진행 중이던 게임이 없습니다. 게임을 새로 시작합니다.");
    }

    public static void printGames(List<Game> games) {
        printMessage("진행 중인 게임 목록");
        games.forEach(OutputView::printGame);
    }

    private static void printGame(Game game) {
        System.out.printf("게임 번호 : %s, 게임 생성 일시 : %s%n", game.getGameId(), game.getCreatedAt());
    }

    public static void printStartMessage() {
        System.out.println();
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void printInitialCommandMessage() {
        printMessage("게임 시작 : start");
        printMessage("게임 종료 : end");
        System.out.println();
    }

    public static void printGameCommandMessage() {
        printMessage("게임 이동 : move source위치 target위치 - 예. move b2 b3");
        printMessage("기물 점수 : status");
        System.out.println();
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

    public static void printSelectGameMessage() {
        System.out.print("진행하실 게임 번호를 입력해주세요. : ");
    }
}
