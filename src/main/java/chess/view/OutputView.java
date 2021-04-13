package chess.view;

import chess.controller.console.dto.board.BoardResponseDto;
import chess.controller.console.dto.position.MovablePathResponseDto;
import chess.controller.console.dto.position.PositionResponseDto;
import chess.controller.console.dto.result.GameResultResponseDto;

import java.util.List;

public class OutputView {

    private static final int CHESS_BOARD_LINE_PIECE_COUNT = 8;
    private static final int CONVERT_PRINT_VERTICAL_NUMBER = 8;
    private static final int CONVERT_PRINT_HORIZONTAL_NUMBER = 1;
    private static final String MOVABLE_PATH = "*";

    private OutputView() {
    }

    public static void printGuideStartGame() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작은 start, 재시작은 restart, 종료는 end 명령을 입력하세요.");
        System.out.println("> 현재 게임 점수 확인 : status");
        System.out.println("> 이동 가능 경로 확인 : show source위치 - 예. show b2");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(final BoardResponseDto boardResponseDto) {
        int count = 0;
        for (String symbol : boardResponseDto.getPieces()) {
            System.out.print(symbol);
            printLineSeparatorByPieceCount(++count);
        }
    }

    private static void printLineSeparatorByPieceCount(final int count) {
        if (count % CHESS_BOARD_LINE_PIECE_COUNT == 0) {
            System.out.println();
        }
    }

    public static void printRestartGame(final BoardResponseDto board) {
        System.out.println("게임을 재시작합니다.");
        printBoard(board);
    }

    public static void printMovablePath(final BoardResponseDto boardResponseDto,
                                        final MovablePathResponseDto movablePathResponseDto) {
        List<PositionResponseDto> path = movablePathResponseDto.getPath();
        List<String> pieces = parseMovablePathOrPiece(boardResponseDto.getPieces(), path);
        int count = 0;
        for (String symbol : pieces) {
            System.out.print(symbol);
            printLineSeparatorByPieceCount(++count);
        }
    }

    private static List<String> parseMovablePathOrPiece(final List<String> pieces,
                                                        final List<PositionResponseDto> path) {
        for (PositionResponseDto positionResponseDto : path) {
            pieces.set(((CONVERT_PRINT_VERTICAL_NUMBER - positionResponseDto.getY()) * CONVERT_PRINT_VERTICAL_NUMBER)
                    + ((positionResponseDto.getX() - CONVERT_PRINT_HORIZONTAL_NUMBER)), MOVABLE_PATH);
        }
        return pieces;
    }


    public static void printStatus(final GameResultResponseDto gameResultResponseDto) {
        System.out.println("White score : " + gameResultResponseDto.getWhiteScore());
        System.out.println("Black score : " + gameResultResponseDto.getBlackScore());
    }

    public static void printGameResult(final GameResultResponseDto gameResultResponseDto) {
        printEndGame();
        System.out.println("White score : " + gameResultResponseDto.getWhiteScore());
        System.out.println("Black score : " + gameResultResponseDto.getBlackScore());

        System.out.println("=== 게임 결과 ===");

        String winner = gameResultResponseDto.getWinner();
        if (winner.equals("NONE")) {
            System.out.println("무승부 입니다.");
            return;
        }
        System.out.printf("%s의 승리입니다.", winner);
    }

    public static void printEndGame() {
        System.out.println("게임을 종료합니다.");
    }

    public static void printError(final RuntimeException runtimeException) {
        System.out.println(runtimeException.getMessage());
    }
}
