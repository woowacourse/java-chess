package chess.controller;

import chess.domain.ChessResult;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public final class ChessController {

    public void runChess() {
        OutputView.printStartGameMessage();
        if (!InputView.inputInitialCommand()) {
            return;
        }
        final ChessGame chessGame = new ChessGame();
        while (!chessGame.isGameOver()) {
            proceedMain(chessGame);
            chessGame.nextTurn();
        }
        showResult(chessGame.board());
    }

    private void proceedMain(final ChessGame chessGame) {
        try {
            proceed(chessGame);
        } catch (UnsupportedOperationException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            proceedMain(chessGame);
        }
    }

    private void proceed(final ChessGame chessGame) {
        OutputView.printCurrentBoard(chessGame.board().unwrap());
        final List<String> runtimeCommands = InputView.inputRuntimeCommand();

        if (checkUserWantToEndGame(runtimeCommands.get(0))) {
            chessGame.changeGameOver();
            return;
        }
        move(chessGame, runtimeCommands);
    }

    private boolean checkUserWantToEndGame(final String input) {
        return InputView.END_COMMAND.equals(input);
    }

    private void move(final ChessGame chessGame, final List<String> runtimeCommands) {
        final Position start = getPositionByCommands(runtimeCommands.get(1).split(""));
        final Position end = getPositionByCommands(runtimeCommands.get(2).split(""));
        chessGame.move(start, end);

        if (chessGame.isKingDead()) {
            chessGame.changeGameOver();
        }
    }

    private Position getPositionByCommands(final String[] commands) {
        return new Position(commands[0], commands[1]);
    }

    private void showResult(final Board board) {
        OutputView.printCurrentBoard(board.unwrap());
        OutputView.printGameResultNotice();

        if (InputView.isStatusInput()) {
            OutputView.printResult(new ChessResult(board), board.isKingDead());
        }
    }
}
