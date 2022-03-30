package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import chess.domain.piece.Team;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    public void run() {
        OutputView.printStartGame();
        if (InputView.inputInitialCommand()) {
            ChessGame chessGame = new ChessGame(new Board(BoardInitializer.initialize()));
            printCurrentBoard(chessGame);
            progressChessGame(chessGame);
        }
        OutputView.printEndMessage();
    }

    private void printCurrentBoard(final ChessGame chessGame) {
        if (chessGame.isOn()) {
            OutputView.printBoard(chessGame.getCurrentBoard());
        }
    }

    private void progressChessGame(final ChessGame chessGame) {
        while (chessGame.isOn()) {
            List<String> inputs = InputView.inputProgressCommand();
            String commandMessage = inputs.get(COMMAND_INDEX);
            move(chessGame, inputs, commandMessage);
            showStatus(chessGame, commandMessage);
            endGame(chessGame, commandMessage);
        }
    }

    private void move(final ChessGame chessGame, final List<String> inputs,final String commandMessage) {
        if (Command.of(commandMessage) == Command.MOVE) {
            chessGame.move(inputs.get(SOURCE_INDEX), inputs.get(TARGET_INDEX));
            printCurrentBoard(chessGame);
        }
    }

    private void showStatus(final ChessGame chessGame, final String commandMessage) {
        if (Command.of(commandMessage) == Command.STATUS) {
            printStatus(chessGame);
        }
    }

    private void endGame(final ChessGame chessGame, final String commandMessage) {
        if (Command.of(commandMessage) == Command.END) {
            chessGame.turnOff();
            printStatus(chessGame);
        }
    }

    private void printStatus(final ChessGame chessGame) {
        Score score = new Score(chessGame.getCurrentBoard());

        double whiteScore = score.calculateScore(Team.WHITE);
        double blackScore = score.calculateScore(Team.BLACK);
        OutputView.printScore(whiteScore, blackScore, score.calculateWinningTeam(whiteScore, blackScore).getValue());
    }
}
