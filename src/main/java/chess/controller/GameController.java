package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandType;
import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class GameController {

    public void run() {
        ChessGame chessGame = startGame();
        playGame(chessGame);
    }

    private ChessGame startGame() {
        try {
            OutputView.gameStartMessage();
            ChessGame chessGame = new ChessGame(ChessBoard.GenerateChessBoard());
            Command command = CommandType.makeCommand(InputView.readCommand());
            command.execute(chessGame);
            return chessGame;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return startGame();
    }

    private void playGame(ChessGame chessGame) {
        while (chessGame.isPlaying()) {
            try {
                Command command = CommandType.makeCommand(InputView.readCommand());
                command.execute(chessGame);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
