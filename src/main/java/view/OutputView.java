package view;

import dto.BoardDto;
import dto.StatusDto;

public class OutputView {

    private OutputView() {
    }

    public static void showBoard(BoardDto boardDto) {
        boardDto.getBoardResult().forEach(System.out::print);
    }

    public static void showGuide() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void showEndMessage() {
        System.out.println("체스가 종료됩니다.");
    }

    public static void showStatus(StatusDto statusDto) {
        System.out.println("BLACK : " + statusDto.getBlackScore());
        System.out.println("WHITE : " + statusDto.getWhiteScore());
    }

    public static void invalidInputMenu() {
        System.out.println("메뉴를 잘못 입력하였습니다.");
    }

    public static void printError(String message) {
        System.out.println(message);
    }
}
