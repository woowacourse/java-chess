package chess.controller;

import chess.domain.ChessResult;
import chess.domain.Turn;
import chess.domain.board.Board;
import chess.domain.chessgame.ChessGame;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.exception.GameOverException;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    public void runChess() {
        OutputView.printStartGameMessage();
        ChessGame game = new ChessGame();
        if (!InputView.inputInitialCommand()) {
            game.endGame();
        }

        while (!game.isGameOver()) {
            proceed(game);
        }
//        showResult(game.board());
    }

    private void proceed(final ChessGame game) {
        try {
            play(game);
        } catch (IllegalStateException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            proceed(game);
        }
    }

    private void play(final ChessGame game) {
        OutputView.printCurrentBoard(game.board());
        final List<String> runtimeCommands = InputView.inputRuntimeCommand();

        if (InputView.END_COMMAND.equals(runtimeCommands.get(0))) {
            game.endGame();
            return;
        }
        move(game, runtimeCommands);
    }

    private void move(final ChessGame game, final List<String> runtimeCommands) {
        final Position start = getPositionByCommands(runtimeCommands.get(1).split(""));
        final Position end = getPositionByCommands(runtimeCommands.get(2).split(""));
        game.move(start, end);
    }

    private Position getPositionByCommands(final String[] commands) {
        return new Position(commands[0], commands[1]);
    }

    private void showResult(final Board board) {
        OutputView.printGameResultNotice();
        if (InputView.isStatusInput()) {
            OutputView.printResult(new ChessResult(board));
        }
    }
}
