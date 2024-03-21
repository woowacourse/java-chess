package chess.view;

import chess.model.board.ChessBoard;
import chess.model.position.ChessPosition;
import chess.model.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {
    private static final int BOARD_SIZE = 8;
    private static final String NONE = ".";

    public void printChessBoard(ChessBoard chessBoard) {
        Map<ChessPosition, Piece> board = chessBoard.getBoard();
        List<List<String>> result = new ArrayList<>();
        initializeChessBoard(result);
        changeNoneToPiece(board, result);
        String text = convertChessBoardTextInOneLine(result);
        System.out.println(text);
    }

    private String convertChessBoardTextInOneLine(List<List<String>> result) {
        return result.stream()
                .map(strings -> String.join("", strings))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private void changeNoneToPiece(Map<ChessPosition, Piece> board, List<List<String>> result) {
        for (Entry<ChessPosition, Piece> entry : board.entrySet()) {
            int file = entry.getKey().getFile().getCoordinate();
            int rank = entry.getKey().getRank().getCoordinate();
            List<String> nowFile = result.get(BOARD_SIZE - rank);
            nowFile.set(file - 1, getPieceText(entry));
        }
    }

    private void initializeChessBoard(List<List<String>> result) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            List<String> strings = IntStream.range(0, BOARD_SIZE)
                    .mapToObj(index -> NONE)
                    .collect(Collectors.toList());
            result.add(strings);
        }
    }

    private String getPieceText(Entry<ChessPosition, Piece> entry) {
        return entry.getValue().getText();
    }

    public void printException(String message) {
        System.out.println("[ERROR] " + message);
    }
}
