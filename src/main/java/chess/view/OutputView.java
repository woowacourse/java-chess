package chess.view;

import chess.controller.BoardResponse;
import java.util.List;

public class OutputView {

    private static final int MAX_RANK_INDEX = 7;
    private static final int FILE_SIZE = 8;
    private static final int RANK_SIZE = 8;

    public void printChessBoard(final BoardResponse boardResponse) {
        final List<String> squareResponses = boardResponse.getSquares();

        for (int rankIndex = MAX_RANK_INDEX; rankIndex >= 0; rankIndex--) {
            for (int fileIndex = 0; fileIndex < FILE_SIZE; fileIndex++) {
                final int squareIndex = rankIndex * RANK_SIZE + fileIndex;
                System.out.print(squareResponses.get(squareIndex));
            }
            System.out.println();
        }
    }
}
