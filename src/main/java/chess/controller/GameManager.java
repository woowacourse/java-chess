package chess.controller;

import chess.domain.Command;
import chess.domain.board.Board;
import chess.domain.board.BoardCreator;
import chess.domain.position.Position;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class GameManager {

    private final InputView inputView;
    private final OutputView outputView;

    public GameManager(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Command command = inputView.readStartOrEndCommand();
        if (command.isEnd()) {
            return;
        }

        Board board = new Board(new BoardCreator());
        outputView.printBoard(board);

        play(board);
    }

    private void play(Board board) {
        ChessGame chessGame = new ChessGame(board);

        while (inputView.readMoveOrEndCommand().isMove()) {
            Position source = inputView.readSourcePosition();
            Position target = inputView.readTargetPosition();

            chessGame.movePiece(source, target);
            outputView.printBoard(board);
        }
    }
}
