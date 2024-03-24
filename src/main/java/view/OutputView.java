package view;

import domain.board.ChessBoard;

public class OutputView {

    private final MessageResolver messageResolver;

    public OutputView(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public void printChessBoard(ChessBoard chessBoard) {
        System.out.println(messageResolver.resolveChessBoardMessage(chessBoard));
    }
}
