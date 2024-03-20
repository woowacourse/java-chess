package chess.view;

import chess.dto.BoardDto;

public final class OutputView {

    private static final String NEWLINE = System.lineSeparator();
    private static final String ERROR_PREFIX = "[ERROR] ";

    private OutputView() {
    }

    public static void printException(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage() + NEWLINE);
    }

    public static void printChessBoard(BoardDto boardDto) {
        System.out.println(boardDto.toString() + NEWLINE);
    }
}
