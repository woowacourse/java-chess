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
        do {
            oneTurn(game);
        } while (game.isStart());
    }

    private void oneTurn(Game game) {
        Command command = readCommand();
        setButton(game, command);
        if (command.isMove()) {
            movePiece(game, command);
        }
        outputView.printChessBoard(game.board());
    }

    private void movePiece(Game game, Command command) {
        try {
            game.movePiece(command.makePoints());
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            oneTurn(game);
        }
    }

    private void setButton(Game game, Command command) {
        try {
            game.setButton(command);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            setButton(game, readCommand());
        }
    }

    private Command readCommand() {
        try {
            return Command.of(inputView.readCommand());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readCommand();
        }
    }
}
