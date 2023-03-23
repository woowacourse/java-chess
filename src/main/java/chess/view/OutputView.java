package chess.view;

import chess.controller.BoardResponse;
import chess.controller.ScoreResponses;
import java.util.List;

public class OutputView {

    private static final int MAX_RANK_INDEX = 7;
    private static final int FILE_SIZE = 8;
    private static final int RANK_SIZE = 8;

    public void printChessBoard(final BoardResponse boardResponse) {
        final List<String> squareResponses = boardResponse.getSquares();

        for (int rankIndex = MAX_RANK_INDEX; rankIndex >= 0; rankIndex--) {
            System.out.println(convertChessBoardMessage(squareResponses, rankIndex));
        }
    }

    private String convertChessBoardMessage(final List<String> squareResponses, final int rankIndex) {
        final StringBuilder chessBoardMessageBuilder = new StringBuilder();

        for (int fileIndex = 0; fileIndex < FILE_SIZE; fileIndex++) {
            final int squareIndex = rankIndex * RANK_SIZE + fileIndex;

            chessBoardMessageBuilder.append(squareResponses.get(squareIndex));
        }
        return chessBoardMessageBuilder.toString();
    }

    public void printErrorMessage(final String message) {
        System.out.printf("%n[ERROR] %s%n", message);
    }

    public void printScores(final ScoreResponses responses) {
        responses.getResponses()
                .forEach(System.out::println);
        System.out.println();
    }
}
