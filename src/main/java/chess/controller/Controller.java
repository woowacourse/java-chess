package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.game.Command;
import chess.domain.game.Result;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

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
            chessGame.run(Command.parseCommand(input));
            OutputView.printChessBoard(chessGame.getChessBoard());
            playGame(chessGame);
        }
        if (Command.END.equals(Command.findCommand(input))
            || chessGame.isOver()) {
            endGame(chessGame);
        }
    }

    public void endGame(ChessGame chessGame) {
        OutputView.gameEnd();
        Result result = chessGame.gameResult();
        if (Command.STATUS.equals(Command.findCommand(InputView.input()))) {
            OutputView.printResult(result);
        }
    }
}
