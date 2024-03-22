package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.view.InputView;
import chess.view.OutputView;

public class RestartGame implements GameStatus {
    private final InputView inputView;

    public RestartGame(InputView inputView) {
        this.inputView = inputView;
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public GameStatus play() {
        Board board = new Board(new BoardInitializer());
        OutputView.printBoard(board);
        return new MainGame(inputView, board);
    }
}
