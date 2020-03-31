package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.piece.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {
    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";
    private static final String STATUS = "status";
    private static final String COMMAND_DELIMITER = " ";
    private ChessGame chessGame;

    public ChessGameController(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    private void command(String input) {
        String[] tokens = input.split(COMMAND_DELIMITER);
        String command = tokens[0];
        if (START.equals(command)) {
            chessGame.start();
        }
        if (END.equals(command)) {
            chessGame.end();
        }
        if (MOVE.equals(command)) {
            chessGame.move(Position.from(tokens[1]), Position.from(tokens[2]));
        }
        if (STATUS.equals(command)) {
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
