package chess.view;

import chess.view.dto.BoardDto;
import chess.view.dto.PieceDto;

import java.util.Arrays;

public class OutputView {

    public static final int BOARD_COLUMN_SIZE = 8;

    public static void drawBoard(final BoardDto boardDto) {
        String[] boardStatus = createBoardStatus(boardDto);

        for (int i = 0; i < boardStatus.length; i++) {
            lineSeparatorIfSatisfyCondition(i);
            System.out.print(boardStatus[i]);
        }

        System.out.println();
    }

    private static void lineSeparatorIfSatisfyCondition(final int i) {
        if(i % BOARD_COLUMN_SIZE == 0) {
            System.out.println();
        }
    }

    private static String[] createBoardStatus(BoardDto boardDto) {
        String[] board = new String[boardDto.getColumn() * boardDto.getRow()];
        Arrays.fill(board, ".");

        for (final PieceDto pieceDto : boardDto.getPieceDtos()) {
            board[pieceDto.getRow() * BOARD_COLUMN_SIZE + pieceDto.getColumn()] = pieceDto.getNotation();
        }

        return board;
    }

    public static void printExceptionMessage(final String message) {
        System.out.println(message);
    }
}
