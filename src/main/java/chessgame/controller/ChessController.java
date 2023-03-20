package chessgame.controller;

import chessgame.domain.Board;
import chessgame.domain.Command;
import chessgame.domain.Game;
import chessgame.util.ChessBoardFactory;
import chessgame.view.InputView;
import chessgame.view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Game game = new Game();
        playGame(game);
    }

    private void playGame(Game game) {
        outputView.printStartMessage();
        do {
            eachTurn(game);
            outputView.printChessBoard(game.board());
        } while (game.isStart());
    }

    private void eachTurn(Game game) {
        Command command = readCommand();
        command = setButton(game, command);
        if (command.isMove()) {
            movePiece(game, command);
        }
    }

    private void movePiece(Game game, Command command) {
        try {
            game.movePiece(command.makePoints());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMsg(e.getMessage());
            eachTurn(game);
        }
    }

    private Command setButton(Game game, Command command) {
        try {
            game.setButton(command);
            return command;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMsg(e.getMessage());
            return setButton(game, readCommand());
        }
    }

    private Command readCommand() {
        try {
            return Command.of(inputView.readCommand());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMsg(e.getMessage());
            return readCommand();
        }
    }
}
