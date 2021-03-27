package chess.controller;

import chess.ChessGame;
import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.util.ColumnConverter;
import chess.domain.util.RowConverter;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Objects;

public final class ChessGameController {
    private ChessGame chessGame;

    public void start() {
        OutputView.printStartMessage();
        run();
    }

    private void run() {
        do {
            turnExecute();
        } while (Objects.nonNull(chessGame) && chessGame.isPlaying());

        if (Objects.nonNull(chessGame) && chessGame.isKingDieEnd()) {
            OutputView.printWinner(chessGame.winner(), chessGame.getScoreByTeam(Team.BLACK), chessGame.getScoreByTeam(Team.WHITE));
        }
    }

    private void turnExecute() {
        try {
            Command command = Command.valueOf(InputView.getCommand());
            commandExecute(command);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void commandExecute(final Command command) {
        if (Objects.isNull(chessGame) && command.equals(Command.MOVE)) {
            OutputView.printNoStartMessage();
            turnExecute();
            return;
        }
        interactiveCommand(command);
        unInteractiveCommand(command);
        printCurrentBoard(command);
    }

    private void unInteractiveCommand(final Command command) {
        if (command.equals(Command.START)) {
            chessGame = new ChessGame();
        }
        if (command.equals(Command.END) && Objects.nonNull(chessGame)) {
            chessGame.end();
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
        if (!command.isPrintCommand()) {
            return;
        }
        printBoard();
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
                RowConverter.getLocation(String.valueOf(point.charAt(1))),
                ColumnConverter.getLocation(String.valueOf(point.charAt(0)))
        );
    }

    private void printBoard() {
        OutputView.printBoard(chessGame.getBoard());
    }
}
