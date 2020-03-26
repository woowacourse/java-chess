package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.piece.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {
    private ChessGame chessGame;

    public ChessGameController(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void command(String input) {
        String[] tokens = input.split(" ");
        if (tokens[0].equals("start")) {
            chessGame.start();
        }
        if (tokens[0].equals("end")) {
            chessGame.end();
        }
        if (tokens[0].equals("move")) {
            chessGame.move(Position.from(tokens[1]), Position.from(tokens[2]));
        }
        if (tokens[0].equals("status")){
            OutputView.printStatus(chessGame.status());
        }
    }

    public void run() {
        OutputView.printGameStart();
        while (!chessGame.isFinished()) {
            command(InputView.inputCommand());
            OutputView.printBoard(chessGame.board());
        }
    }
}
