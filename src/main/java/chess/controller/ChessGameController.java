package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
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
        Command command = InputView.inputInitialCommand();
        validateInitialCommand(command);
        if (command == Command.START) {
            ChessGame chessGame = new ChessGame(new Board(BoardFactory.initialize()));
            printCurrentBoard(chessGame);
            progressChessGame(chessGame);
        }
        OutputView.printEndMessage();
    }

    private void validateInitialCommand(final Command command) {
        if (command.isMove()) {
            throw new IllegalArgumentException("[ERROR] 게임이 아직 시작되지 않았습니다.");
        }
    }

    private void printCurrentBoard(final ChessGame chessGame) {
        if (chessGame.isOn()) {
            OutputView.printBoard(chessGame.getCurrentBoard());
        }
    }

    private void progressChessGame(final ChessGame chessGame) {
        while (chessGame.isOn()) {
            List<String> inputs = InputView.inputProgressCommand();
            Command command = Command.of(inputs.get(COMMAND_INDEX));
            validateInvalidProgressCommand(command);
            move(chessGame, inputs, command);
            showStatus(chessGame, command);
            endGame(chessGame, command);
        }
    }

    private void validateInvalidProgressCommand(final Command command) {
        if (command.isStart()) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 시작되었습니다.");
        }
    }

    private void move(final ChessGame chessGame, final List<String> inputs, final Command command) {
        if (command == Command.MOVE) {
            chessGame.move(inputs.get(SOURCE_INDEX), inputs.get(TARGET_INDEX));
            printCurrentBoard(chessGame);
        }
    }

    private void showStatus(final ChessGame chessGame, final Command command) {
        if (command == Command.STATUS) {
            printStatus(chessGame);
        }
    }

    private void endGame(final ChessGame chessGame, final Command command) {
        if (command == Command.END) {
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
