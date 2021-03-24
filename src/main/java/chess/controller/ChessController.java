package chess.controller;

import chess.domain.ChessResult;
import chess.domain.chessgame.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;

public class ChessController {
    public void runChess() {
        OutputView.printStartGameMessage();
        ChessGame game = new ChessGame();
        if (!InputView.inputInitialCommand()) {
            game.endGame();
        }

        while (game.isRunning()) {
            proceed(game);
        }
        showResult(game.board());
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

    private void showResult(final Map<Position, Piece> board) {
        OutputView.printGameResultNotice();
        if (InputView.isStatusInput()) {
            ChessResult result = new ChessResult(board);
            OutputView.printResult(result.scoreResult(), result.winner(), result.isTie());
        }
    }
}
