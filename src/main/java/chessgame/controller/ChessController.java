package chessgame.controller;

import chessgame.Command;
import chessgame.Game;
import chessgame.domain.Board;
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
        outputView.printStartMessage();
        Game game = new Game(new Board(ChessBoardFactory.create()));
        playGame(game);
    }

    private void playGame(Game game) {
        do {
            oneTurn(game);
            outputView.printChessBoard(game.board());
        } while (game.isStart());
    }

    private void oneTurn(Game game) {
        Command command = readCommand();
        command = setButton(game, command);
        if (command.isMove()) {
            movePiece(game, command);
        }
    }

    private void movePiece(Game game, Command command) {
        try {
            game.movePiece(command.makePoints());
        } catch(IllegalArgumentException e){
            outputView.printErrorMsg(e.getMessage());
            oneTurn(game);
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
