package chess.view;

import chess.controller.dto.BoardResponseDto;
import chess.controller.dto.PositionResponseDto;
import chess.controller.dto.ShowPathResponseDto;
import chess.manager.Status;

import java.util.List;

public class OutputView {

    private static final int CHESS_BOARD_LINE_PIECE_COUNT = 8;

    private OutputView() {
    }

    public static void printGuideStartGame() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작은 start, 재시작은 restart, 종료는 end 명령을 입력하세요.");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(final BoardResponseDto boardResponseDto) {
        int count = 0;
        for (String symbol : boardResponseDto.getPieces()) {
            System.out.print(symbol);
            printLineSeperatorByPieceCount(++count);
        }
    }

    private static void printLineSeperatorByPieceCount(int count) {
        if (count % CHESS_BOARD_LINE_PIECE_COUNT == 0) {
            System.out.println();
        }
    }

    public static void printRestartGame(final BoardResponseDto board) {
        System.out.println("게임을 재시작합니다.");
        printBoard(board);
    }

    public static void printMovablePath(final BoardResponseDto boardResponseDto,
                                        final ShowPathResponseDto showPathResponseDto) {
        List<PositionResponseDto> path = showPathResponseDto.getPath();
        List<String> pieces = parseMovablePathOrPiece(boardResponseDto.getPieces(), path);
        int count = 0;
        for (String symbol : pieces) {
            System.out.print(symbol);
            printLineSeperatorByPieceCount(++count);
        }
    }

    private static List<String> parseMovablePathOrPiece(final List<String> pieces, final List<PositionResponseDto> path) {
        for (PositionResponseDto positionResponseDto : path) {
            System.out.println("y = " + positionResponseDto.getY() + ", x = " + positionResponseDto.getX());
            pieces.set(((8 - positionResponseDto.getY()) * 8) + ((positionResponseDto.getX() - 1)), "*");
        }
        return pieces;
    }


    public static void printStatus(final Status status) {
        System.out.println("White score : " + status.whiteScore());
        System.out.println("Black score : " + status.blackScore());
    }

    public static void printGameResult(final Status status) {
        printStatus(status);

        System.out.println("=== 게임 결과 ===");

        if (status.whiteScore() > status.blackScore()) {
            System.out.println("화이트의 승리입니다.");
        }

        if (status.whiteScore() < status.blackScore()) {
            System.out.println("블랙의 승리입니다.");
        }

        if (status.whiteScore() == status.blackScore()) {
            System.out.println("무승부입니다.");
        }
    }

    public static void printEndGame() {
        System.out.println("게임을 종료합니다.");
    }

    public static void printError(final RuntimeException runtimeException) {
        System.out.println(runtimeException.getMessage());
    }
}
