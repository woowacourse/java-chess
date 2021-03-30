package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.game.Result;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;

public class Controller {

    public void run() {
        ChessGame chessGame = new ChessGame(new ChessBoard(), Color.WHITE);
        OutputView.gameStart();
        if (Command.START.equals(Command.findCommand(InputView.input()))) {
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

        if (Command.MOVE.equals(Command.findCommand(input))) {
            move(chessGame, input);
        }
        if (Command.END.equals(Command.findCommand(input))
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
        if (Command.STATUS.equals(Command.findCommand(InputView.input()))) {
            OutputView.printResult(result);
        }
    }
}
