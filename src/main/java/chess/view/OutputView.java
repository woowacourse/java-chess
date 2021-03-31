package chess.view;

import chess.controller.dto.*;
import chess.domain.piece.Owner;

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
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(final BoardResponseDto boardResponseDto) {
        int count = 0;
        for (String symbol : boardResponseDto.getPieces()) {
            System.out.print(symbol);
            printLineSeparatorByPieceCount(++count);
        }
    }

    private static void printLineSeparatorByPieceCount(int count) {
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
            printLineSeparatorByPieceCount(++count);
        }
    }

    private static List<String> parseMovablePathOrPiece(final List<String> pieces,
                                                        final List<PositionResponseDto> path) {
        for (PositionResponseDto positionResponseDto : path) {
            System.out.println("y = " + positionResponseDto.getY() + ", x = " + positionResponseDto.getX());
            pieces.set(((CONVERT_PRINT_VERTICAL_NUMBER - positionResponseDto.getY()) * CONVERT_PRINT_VERTICAL_NUMBER)
                    + ((positionResponseDto.getX() - CONVERT_PRINT_HORIZONTAL_NUMBER)), MOVABLE_PATH);
        }
        return pieces;
    }


    public static void printStatus(final StatusResponseDto statusResponseDto) {
        System.out.println("White score : " + statusResponseDto.getWhiteScore());
        System.out.println("Black score : " + statusResponseDto.getBlackScore());
    }

    public static void printGameResult(final GameResultDto gameResultDto) {
        System.out.println("White score : " + gameResultDto.getWhiteScore());
        System.out.println("Black score : " + gameResultDto.getBlackScore());

        System.out.println("=== 게임 결과 ===");

        Owner winner = gameResultDto.getWinner();
        if (winner == Owner.WHITE) {
            System.out.println("화이트의 승리입니다.");
        }

        if (winner == Owner.BLACK) {
            System.out.println("블랙의 승리입니다.");
        }

        if (winner == Owner.NONE) {
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
