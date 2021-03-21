package chess.controller;

import chess.ChessGame;
import chess.domain.Team;
import chess.domain.position.Col;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {
    private final ChessGame chessGame;
    private Team currentTurn;

    public ChessGameController(final ChessGame chessGame) {
        this.chessGame = chessGame;
        this.currentTurn = Team.WHITE;
    }

    public void start() {
        OutputView.printStartMessage();
        run();
    }

    private void run() {
        while (chessGame.isPlaying()) {
            Command command = Command.valueOf(InputView.getCommand());
            command.execute(chessGame);
            moveCommand(command);
            printCurrentBoard(command);
        }
    }

    private void moveCommand(Command command) {
        if (command.equals(Command.MOVE)) {
            move();
            currentTurn = Team.getAnotherTeam(currentTurn);

        }
    }

    private void printCurrentBoard(final Command command) {
        if (command.isPrint()) {
            printBoard();
        }
    }

    private void move() {
        try {
            String startPoint = InputView.getPoint();
            String endPoint = InputView.getPoint();

            Position startPosition = position(startPoint);
            Position endPosition = position(endPoint);
            chessGame.move(startPosition, endPosition, currentTurn);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            run();
        }
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
