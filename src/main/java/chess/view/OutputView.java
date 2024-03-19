package chess.view;

import chess.model.ChessBoard;
import chess.model.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {
    private static final int BOARD_SIZE = 8;
    private static final String NONE = ".";

    public void printChessBoard(ChessBoard chessBoard) {
        List<Piece> board = chessBoard.getBoard();
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            List<String> strings = IntStream.range(0, BOARD_SIZE)
                    .mapToObj(index -> NONE)
                    .collect(Collectors.toList());
            result.add(strings);
        }
        for (Piece piece : board) {
            int file = piece.getChessPosition().getFile().getCoordinate();
            int rank = piece.getChessPosition().getRank().getCoordinate();
            List<String> nowFile = result.get(BOARD_SIZE - rank);
            nowFile.set(file - 1, piece.getText());
        }
        String text = result.stream()
                .map(strings -> String.join("", strings))
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(text);
    }
}
