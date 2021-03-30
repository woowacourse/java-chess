package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.game.Result;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;
import javafx.util.Pair;

public class Controller {

    public void run() {
        ChessGame chessGame = new ChessGame(new ChessBoard(), Color.WHITE);
        OutputView.gameStart();
        String input = InputView.input();
        if (Command.START.equals(validCommand(input))) {
            startGame(chessGame);
        }
    }

    public void startGame(ChessGame chessGame) {
        chessGame.start();
        OutputView.printChessBoard(chessGame.getChessBoard());
        playGame(chessGame);
    }

    public void playGame(ChessGame chessGame) {
        String input = InputView.input();
        Command command = validCommand(input);
        try {
            validatePlayCommand(command);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            playGame(chessGame);
        }

        if (Command.MOVE.equals(command)) {
            move(chessGame, input);
        }
        if (Command.END.equals(command)
            || chessGame.isOver()) {
            endGame(chessGame);
        }
    }

    private void move(ChessGame chessGame, String input) {
        try {
            chessGame.run(Command.parseCommand(input));
            OutputView.printChessBoard(chessGame.getChessBoard());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        playGame(chessGame);
    }

    public void endGame(ChessGame chessGame) {
        OutputView.gameEnd();
        Result result = chessGame.gameResult();
        String input = InputView.input();
        if (Command.STATUS.equals(validCommand(input))) {
            OutputView.printResult(result);
        }
    }

    private Command validCommand(String input) {
        try {
            return Command.findCommand(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return validCommand(InputView.input());
        }
    }

    private void validatePlayCommand(Command command) {
        if (Command.MOVE.equals(command) || Command.END.equals(command)) {
            return;
        }
        throw new IllegalArgumentException(Command.INVALID_COMMAND);
    }
}
