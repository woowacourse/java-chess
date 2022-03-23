package chess;

import chess.domain.ChessBoard;
import chess.domain.GameCommand;
import chess.domain.NormalPiecesGenerator;
import chess.domain.PiecesGenerator;
import chess.view.InputView;
import chess.view.ResultView;

public class ConsoleApplication {

    public static void main(String[] args) {
        ResultView.printStartMessage();
        playGame();
    }

    private static void playGame() {
        GameCommand gameCommand;
        do {
            gameCommand = requestCommand();
        } while (!gameCommand.isEnd());
    }

    private static GameCommand requestCommand() {
        try {
            GameCommand gameCommand = new GameCommand(InputView.inputCommand());
            playGameByCommand(gameCommand);
            return gameCommand;
        } catch (IllegalArgumentException exception) {
            ResultView.printReplay(exception.getMessage());
            return requestCommand();
        }
    }

    private static void playGameByCommand(GameCommand gameCommand) {
        if (gameCommand.isStart()) {
            PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
            ChessBoard chessBoard = new ChessBoard(piecesGenerator.generate());
            ResultView.printChessBoard(chessBoard.getPieces());
        }
    }
}
