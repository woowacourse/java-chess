package chess.view;

import chess.controller.command.CommandType;
import chess.dto.ChessBoardDto;
import chess.dto.StatusDto;
import java.util.List;

public final class OutputView {

    private OutputView() {
    }

    public static void printWelcomeMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작: " + CommandType.START.value());
        System.out.println("게임 종료: " + CommandType.END.value());
        System.out.println("게임 현황: " + CommandType.STATUS.value());
        System.out.println("게임 이동: move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(final ChessBoardDto chessBoardDto) {
        final List<List<String>> chessBoard = chessBoardDto.getChessBoard();
        for (int i = 0; i < chessBoard.size(); i++) {
            printEachRank(chessBoard.get(i), chessBoard.size() - i);
        }
        System.out.println("________");
        System.out.println("abcdefgh");
    }

    private static void printEachRank(final List<String> rank, int rankCount) {
        for (String square : rank) {
            System.out.print(square);
        }
        System.out.println("  |" + rankCount);
    }

    public static void printErrorMessage(final String errorMessage) {
        System.out.println("[ERROR] " + errorMessage + System.lineSeparator());
    }

    public static void printStatus(final StatusDto statusDto) {
        System.out.println(System.lineSeparator() + "게임 점수 현황");
        statusDto.getStatus().forEach((key, value) -> {
            System.out.println(key + " :" + value);
        });
        System.out.println();
    }
}
