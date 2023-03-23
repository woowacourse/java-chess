package chess.view;

import chess.dto.response.ChessBoardDto;

import java.util.List;

public final class OutputView {

    private static final String START_MESSAGE = "> 체스 게임을 시작합니다.\n" +
            "> 게임 시작 : start\n" +
            "> 게임 종료 : end \n" +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public static void printNotStartedGameMessage() {
        System.out.println("아직 게임이 시작되지 않았습니다.");
    }

    public static void printChessBoard(ChessBoardDto chessBoardDto) {
        List<String> chessBoard = chessBoardDto.getChessBoard();
        System.out.println();
        for (String oneRow : chessBoard) {
            System.out.println(oneRow);
        }
        System.out.println();
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }
}
