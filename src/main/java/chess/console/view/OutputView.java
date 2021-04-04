package chess.console.view;

import chess.console.view.dto.BoardDto;
import chess.console.view.dto.PieceDto;

import java.util.Arrays;

public class OutputView {
    private static final String START_MESSAGE = "체스 게임을 시작합니다." + System.lineSeparator() +
            "게임 시작은 start, 종료는 end 명령을 입력하세요.";
    private static final int BOARD_COLUMN_SIZE = 8;
    private static final String SCORE_FORMAT = "백: %.1f 흑: %.1f %n";
    private static final String WINNER_FORMAT = "%s이 승리했습니다!%n";

    public static void drawBoard(final BoardDto boardDto) {
        String[] boardStatus = createBoardStatus(boardDto);

        for (int i = 0; i < boardStatus.length; i++) {
            lineSeparatorIfSatisfyCondition(i);
            System.out.print(boardStatus[i]);
        }
        System.out.println("  " + 1);

        System.out.println();
        System.out.println("abcdefgh");
    }

    private static String[] createBoardStatus(BoardDto boardDto) {
        String[] board = new String[boardDto.getColumn() * boardDto.getRow()];
        Arrays.fill(board, ".");

        for (final PieceDto pieceDto : boardDto.getPieceDtos()) {
            board[pieceDto.getRow() * BOARD_COLUMN_SIZE + pieceDto.getColumn()] = pieceDto.getNotation();
        }

        return board;
    }

    private static void lineSeparatorIfSatisfyCondition(final int lineSeparateThreshold) {
        if (lineSeparateThreshold != 0 && lineSeparateThreshold % BOARD_COLUMN_SIZE == 0) {
            System.out.println("  " + (BOARD_COLUMN_SIZE - lineSeparateThreshold / BOARD_COLUMN_SIZE + 1));
        }
    }

    public static void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public static void printExceptionMessage(final String message) {
        System.out.println(message);
    }

    public static void printScore(final double whiteScore, final double blackScore) {
        System.out.printf(SCORE_FORMAT, whiteScore, blackScore);
    }

    public static void printWinner(String color) {
        System.out.printf(WINNER_FORMAT, color);
    }

}
