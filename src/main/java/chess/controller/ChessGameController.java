package chess.controller;

import chess.ChessGame;
import chess.domain.Team;
import chess.domain.position.Col;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Objects;

public class ChessGameController {
    private final ChessGame chessGame;

    public ChessGameController(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void start() {
        OutputView.printStartMessage();
        run();
    }

    private void run() {
        while (chessGame.isPlaying()) {
            turnExecute();
        }
        if (Objects.nonNull(chessGame.winner())) {
            OutputView.printWinner(chessGame.winner(), chessGame.getScoreByTeam(Team.BLACK), chessGame.getScoreByTeam(Team.WHITE));
        }
    }

    private void turnExecute() {
        try {
            Command command = Command.valueOf(InputView.getCommand());
            command.execute(chessGame);
            interactiveCommand(command);
            printCurrentBoard(command);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void interactiveCommand(final Command command) {
        if (command.equals(Command.MOVE)) {
            move();
        }
        if (command.equals(Command.STATUS)) {
            OutputView.printEachTeamScore(chessGame.getScoreByTeam(Team.BLACK), chessGame.getScoreByTeam(Team.WHITE));
        }
    }

    private void printCurrentBoard(final Command command) {
        if (command.isPrint()) {
            printBoard();
        }
    }

    private void move() {
        String startPoint = InputView.getPoint();
        String endPoint = InputView.getPoint();

        Position startPosition = position(startPoint);
        Position endPosition = position(endPoint);
        chessGame.move(startPosition, endPosition);
    }

    private Position position(final String point) {
        return new Position(
                Row.getLocation(String.valueOf(point.charAt(1))),
                Col.getLocation(String.valueOf(point.charAt(0)))
        );
    }

    private void printBoard() {
        OutputView.printBoard(chessGame.getBoard());
    }
}
