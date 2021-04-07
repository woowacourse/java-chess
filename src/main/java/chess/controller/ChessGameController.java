package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.Team;
import chess.exception.StartCommandException;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Objects;

public final class ChessGameController {

    public void start(final ChessGame chessGame) {
        OutputView.printStartMessage();
        checkInitialCommand(chessGame);
        play(chessGame);
    }

    private void checkInitialCommand(final ChessGame chessGame) {
        try {
            Command command = Command.valueOf(InputView.command());
            checkStartEndCommand(command, chessGame);
            printCurrentBoard(command, chessGame);
        } catch (StartCommandException e) {
            OutputView.printError(e.getMessage());
            checkInitialCommand(chessGame);
        }
    }

    private void checkStartEndCommand(final Command command, final ChessGame chessGame) {
        if (!Command.START.equals(command) && !Command.END.equals(command)) {
            throw new StartCommandException();
        }
        command.execute(chessGame);
    }

    private void play(final ChessGame chessGame) {
        while (chessGame.isPlaying()) {
            turnExecute(chessGame);
        }
        if (Objects.nonNull(chessGame.winner())) {
            OutputView.printWinner(chessGame.winner(), chessGame.scoreByTeam(Team.BLACK), chessGame.scoreByTeam(Team.WHITE));
        }
    }

    private void turnExecute(final ChessGame chessGame) {
        try {
            Command command = Command.valueOf(InputView.command());
            command.execute(chessGame);
            moveOrStatus(command, chessGame);
            printCurrentBoard(command, chessGame);
        } catch (RuntimeException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void moveOrStatus(final Command command, final ChessGame chessGame) {
        if (Command.MOVE.equals(command)) {
            move(chessGame);
        }
        if (Command.STATUS.equals(command)) {
            OutputView.printEachTeamScore(chessGame.scoreByTeam(Team.BLACK), chessGame.scoreByTeam(Team.WHITE));
        }
    }

    private void move(final ChessGame chessGame) {
        String startPoint = InputView.point();
        String endPoint = InputView.point();
        chessGame.move(startPoint, endPoint);
    }

    private void printCurrentBoard(final Command command, final ChessGame chessGame) {
        if (command.prints()) {
            OutputView.printBoard(chessGame.board());
        }
    }
}
