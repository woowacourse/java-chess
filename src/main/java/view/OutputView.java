package view;

import constant.ErrorCode;
import dto.ChessBoardDto;
import model.Camp;
import view.message.ErrorCodeMessage;

public class OutputView {

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(final ChessBoardDto chessBoardDto) {
        System.out.printf(chessBoardDto.getBoard());
        System.out.printf("현재 턴: %s%n%n", chessBoardDto.getCurrentTurn());
    }

    public void printException(final ErrorCode errorCode) {
        System.out.printf("[ERROR] %s%n", ErrorCodeMessage.from(errorCode).getMessage());
    }
}
