package view;

import domain.board.ChessBoard;
import domain.board.Score;

public class OutputView {
    private final MessageResolver messageResolver;

    public OutputView(final MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public void printGameGuideMessage() {
        System.out.println(messageResolver.resolveGameStartMessage());
    }

    public void printBoard(ChessBoard board) {
        String boardRankMessage = messageResolver.resolveBoard(board);
        System.out.println(boardRankMessage);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printScore(Score score) {
        String scoreMessage = messageResolver.resolveScore(score);
        System.out.println(scoreMessage);
    }
}
