package chess.view;

import chess.domain.Position;
import chess.dto.PositionDto;

import java.util.Map;

public class OutputView {
    public static final int BOARD_START = 0;
    public static final int BOARD_END = 7;

    private OutputView() {
    }

    public static void printChessStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(final Map<PositionDto, String> chessBoard) {
        for (int row = BOARD_END; row >= BOARD_START; row--) {
            printChessBoardSingleLine(chessBoard, row);
            System.out.println();
        }
    }

    private static void printChessBoardSingleLine(final Map<PositionDto, String> chessBoard, final int row) {
        for (int column = BOARD_START; column <= BOARD_END; column++) {
            System.out.print(chessBoard.getOrDefault(new PositionDto(column, row), "."));
        }
    }

    public static void printChessGameResult(final double blackTeamScore, final double whiteTeamScore) {
        System.out.println("블랙팀 점수: " + blackTeamScore);
        System.out.println("화이트팀 점수: " + whiteTeamScore);
    }
}
