package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Team;
import chess.exception.StartCommandException;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Objects;

public final class ChessGameController {

    public final void start(final ChessGame chessGame) {
        OutputView.printStartMessage();
        gameStart(chessGame);
        play(chessGame);
    }

    private void gameStart(final ChessGame chessGame) {
        try {
            Command command = Command.valueOf(InputView.getCommand());
            startCommand(command, chessGame);
            printCurrentBoard(command, chessGame);
        } catch (StartCommandException e) {
            OutputView.printError(e.getMessage());
            gameStart(chessGame);
        }
    }

    private void startCommand(final Command command, final ChessGame chessGame) {
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
            OutputView.printWinner(chessGame.winner(), chessGame.getScoreByTeam(Team.BLACK), chessGame.getScoreByTeam(Team.WHITE));
        }
    }

    private void turnExecute(final ChessGame chessGame) {
        try {
            Command command = Command.valueOf(InputView.getCommand());
            command.execute(chessGame);
            interactiveCommand(command, chessGame);
            printCurrentBoard(command, chessGame);
        } catch (RuntimeException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void interactiveCommand(final Command command, final ChessGame chessGame) {
        if (command.equals(Command.MOVE)) {
            move(chessGame);
        }
        if (command.equals(Command.STATUS)) {
            OutputView.printEachTeamScore(chessGame.getScoreByTeam(Team.BLACK), chessGame.getScoreByTeam(Team.WHITE));
        }
    }

    private void move(final ChessGame chessGame) {
        String startPoint = InputView.getPoint();
        String endPoint = InputView.getPoint();
        chessGame.move(startPoint, endPoint);
    }

    private void printCurrentBoard(final Command command, final ChessGame chessGame) {
        if (command.isPrint()) {
            printBoard(chessGame);
        }
    }

    private void printBoard(final ChessGame chessGame) {
        OutputView.printBoard(chessGame.getBoard());
    }
}
