package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandType;
import chess.domain.chessboard.Coordinate;
import chess.domain.game.ChessGame;
import chess.domain.game.GameAction;
import chess.domain.piece.Score;
import chess.domain.piece.Team;
import chess.dto.ChessBoardDto;
import chess.dto.StatusDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;
import java.util.function.Supplier;

public final class ChessController {

    private static final int COORDINATE_FROM_INDEX = 0;
    private static final int COORDINATE_TO_INDEX = 1;

    private final Map<CommandType, GameAction> commandActions = Map.of(
            CommandType.START, (chessGame, ignored) -> this.start(chessGame),
            CommandType.END, (chessGame, ignored) -> this.end(chessGame),
            CommandType.MOVE, this::move,
            CommandType.STATUS, (chessGame, ignored) -> this.status(chessGame)
    );

    public void run() {
        OutputView.printWelcomeMessage();
        ChessGame chessGame = new ChessGame();

        while (!chessGame.isGameEnd()) {
            playChessGame(chessGame);
            printChessBoard(chessGame);
        }
    }

    private void playChessGame(final ChessGame game) {
        Command command = getCommand();
        try {
            commandActions.get(command.getType()).execute(game, command);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
            playChessGame(game);
        }
    }

    private void start(final ChessGame chessGame) {
        chessGame.start();
    }

    private void move(final ChessGame chessGame, final Command command) {
        final Coordinate from = command.getCoordinate().get(COORDINATE_FROM_INDEX);
        final Coordinate to = command.getCoordinate().get(COORDINATE_TO_INDEX);
        chessGame.move(from, to);
    }

    private void status(final ChessGame chessGame) {
        final Map<Team, Score> status = chessGame.getScore();
        printGameStatus(status);
    }

    private void end(final ChessGame chessGame) {
        chessGame.end();
    }

    private Command getCommand() {
        return repeatInput(InputView::readCommand);
    }

    private <T> T repeatInput(final Supplier<T> input) {
        try {
            return input.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return repeatInput(input);
        }
    }

    private void printChessBoard(final ChessGame chessGame) {
        OutputView.printChessBoard(new ChessBoardDto(chessGame.getChessBoard()));
    }

    private void printGameStatus(final Map<Team, Score> status) {
        OutputView.printStatus(new StatusDto(status));
    }
}
