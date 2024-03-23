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

    public void printBoard(final List<PieceResponse> pieces) {
        char[][] board = setUpBoard();
        addPieceToBoard(pieces, board);
        printBoardStatus(board);
        System.out.println();
    }

    private char[][] setUpBoard() {
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
        for (char[] line : board) {
            Arrays.fill(line, EMPTY_SQUARE);
        }
        return board;
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

    private void printBoardStatus(final char[][] board) {
        IntStream.range(0, board.length)
                .mapToObj(lineCount -> board[board.length - 1 - lineCount])
                .map(String::new)
                .forEach(System.out::println);
    }
}
