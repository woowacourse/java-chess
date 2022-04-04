package chess;

import chess.domain.ChessGame;
import chess.domain.GameCommand;
import chess.domain.MoveLocation;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {
    public static void main(String[] args) {
        OutputView.printChessCommandInfo();
        ChessGame chessGame = new ChessGame();
        do {
            playEachGame(chessGame);
        } while (chessGame.isRunning());
    }

    private static void playEachGame(ChessGame chessGame) {
        try {
            playGameByCommand(chessGame);
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e);
            playEachGame(chessGame);
        }
    }

    private static void playGameByCommand(ChessGame chessGame) {
        String command = InputView.askCommand();
        GameCommand gameCommand = GameCommand.of(command);
        if (gameCommand.isStart()) {
            chessGame.start();
        }
        if (gameCommand.isMove()) {
            MoveLocation moveLocation = MoveLocation.of(command);
            chessGame.move(moveLocation.getSource(), moveLocation.getTarget());
        }
        if (gameCommand.isStatus()) {
            OutputView.printScore(chessGame.status());
        }
        if (gameCommand.isEnd()) {
            chessGame.end();
        }
    }
}
