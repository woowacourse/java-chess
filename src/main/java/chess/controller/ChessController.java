package chess.controller;

import chess.domain.Board;
import chess.domain.initializer.ChessPieceInitializer;
import chess.dto.MoveOrder;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessPieceInitializer chessPieceInitializer;

    public ChessController(final InputView inputView, final OutputView outputView, final ChessPieceInitializer chessPieceInitializer) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessPieceInitializer = chessPieceInitializer;
    }

    public void run() {
        final Board board = initializeBoard();

        while (!board.isGameEnd()) {
            outputView.outputBoard(board.getPieces());
            MoveOrder order = inputView.getOrder();
            switch (order.orderOption()) {
                case MOVE -> board.move(order.piecePosition(), order.newPosition());
                case TAKE -> board.take(order.piecePosition(), order.newPosition());
            }
        }
        outputView.outputWinner(board.getWinnerColor());
    }

    private Board initializeBoard() {
        return new Board(chessPieceInitializer.blackPieces(), chessPieceInitializer.whitePieces());
    }
}
