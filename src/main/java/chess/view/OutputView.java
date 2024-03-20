package chess.view;

import chess.dto.PieceResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class OutputView {

    public static final char EMPTY_SQUARE = '.';

    public void printGameStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public void printBoard(final List<PieceResponse> pieces) {
        char[][] board = setUpBoard();
        addPieceToBoard(pieces, board);
        printBoardStatus(board);
    }

    private char[][] setUpBoard() {
        char[][] board = new char[8][8];
        for (char[] line : board) {
            Arrays.fill(line, EMPTY_SQUARE);
        }
        return board;
    }

    private void addPieceToBoard(final List<PieceResponse> pieces, final char[][] board) {
        for (PieceResponse piece : pieces) {
            int y = piece.rankIndex();
            int x = piece.fileIndex();
            board[y][x] = getPieceDisplay(piece.type(), piece.color());
        }
    }

    private char getPieceDisplay(final String type, final String color) {
        return PieceView.valueOf(type).getDisplayOf(color);
    }

    private void printBoardStatus(final char[][] board) {
        IntStream.range(0, board.length)
                .mapToObj(i -> board[board.length - 1 - i])
                .map(String::new)
                .forEach(System.out::println);
    }
}
