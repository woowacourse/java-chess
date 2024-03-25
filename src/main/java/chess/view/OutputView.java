package chess.view;

import chess.domain.dto.PieceResponse;
import chess.domain.piece.Color;
import chess.domain.piece.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class OutputView {
    private static final char EMPTY_SQUARE = '.';
    private static final int BOARD_SIZE = 8;
    private static final char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    public void printBoard(final List<PieceResponse> pieces) {
        setUpBoard();
        addPieceToBoard(pieces, board);
        printBoard(board);
        System.out.println();
    }

    private void setUpBoard() {
        for (char[] line : board) {
            Arrays.fill(line, EMPTY_SQUARE);
        }
    }

    private void addPieceToBoard(final List<PieceResponse> pieces, final char[][] board) {
        for (PieceResponse response : pieces) {
            int y = response.getRankIndex();
            int x = response.getFileIndex();
            board[y][x] = getPieceDisplay(response.getType(), response.getColor());
        }
    }

    private char getPieceDisplay(final Type type, final Color color) {
        return PieceView.findByType(type).changeToView(color);
    }

    private void printBoard(final char[][] board) {
        IntStream.range(0, board.length)
                .mapToObj(lineCount -> board[board.length - 1 - lineCount])
                .forEach(System.out::println);
    }
}
