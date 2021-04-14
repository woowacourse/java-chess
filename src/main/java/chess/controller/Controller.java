package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.game.Command;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;

public class Controller {

    public void run() {
        ChessGame chessGame = new ChessGame(new ChessBoard(), Color.WHITE);
        OutputView.gameStart();
        play(chessGame);
    }

    public void play(ChessGame chessGame) {
        while (!chessGame.isOver()) {
            String input = InputView.input();
            validCommand(input).execute(chessGame, input);
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
}
