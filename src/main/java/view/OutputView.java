package view;

import domain.board.Board;

public class OutputView {

    private final MessageResolver messageResolver;

    public OutputView(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public void printBoard(Board board) {
        System.out.println(messageResolver.resolveBoardMessage(board));
    }
}
