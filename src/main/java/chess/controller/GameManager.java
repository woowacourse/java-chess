package chess.controller;

import chess.domain.Command;
import chess.domain.Game;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class GameManager {

    private final InputView inputView;
    private final OutputView outputView;

    public GameManager(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        if (endGame()) {
            return;
        }
        Game chess = new Game();
        outputView.printBoard(chess.board());

        while (notEndGame()) {
            Position source = inputView.readSourcePosition();
            Position target = inputView.readTargetPosition();
            chess.proceedTurn(source, target);
            outputView.printBoard(chess.board());
        }
    }

    private boolean endGame() {
        Command command = inputView.readStartOrEndCommand();
        return command.isEnd();
    }

    private boolean notEndGame() {
        Command command = inputView.readMoveOrEndCommand();
        return command.isMove();
    }
}
