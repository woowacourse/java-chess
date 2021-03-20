package chess.controller;

import chess.domain.ChessResult;
import chess.domain.Turn;
import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.exception.GameOverException;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    public void runChess() {
        OutputView.printStartGameMessage();
        if (!InputView.inputInitialCommand()) {
            return;
        }

        final Board board = new Board();
        final Turn turn = new Turn();
        proceed(board, turn);
    }

    private void proceed(final Board board, final Turn turn) {
        try {
            play(board, turn);
        } catch (IllegalStateException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            proceed(board, turn);
        } catch (GameOverException e) {
            showResult(board, e.getMessage());
        }
    }

    private void play(final Board board, final Turn turn) {
        OutputView.printCurrentBoard(board.unwrap());
        final List<String> runtimeCommands = InputView.inputRuntimeCommand();

        checkGameOver(InputView.END_COMMAND.equals(runtimeCommands.get(0)), "게임이 강제 종료되었습니다.");
        move(board, turn.now(), runtimeCommands);
        checkGameOver(board.isKingDead(), turn.now().oppositeTeamName() + "의 king이 죽어서 게임이 종료되었습니다.");

        turn.next();
        play(board, turn);
    }

    private void checkGameOver(final boolean isGameOver, final String message) throws GameOverException {
        if (isGameOver) {
            throw new GameOverException(message);
        }
    }

    private void move(final Board board, final Team team, final List<String> runtimeCommands) {
        final Position start = getPositionByCommands(runtimeCommands.get(1).split(""));
        final Position end = getPositionByCommands(runtimeCommands.get(2).split(""));
        board.move(start, end, team);
    }

    private Position getPositionByCommands(final String[] commands) {
        return new Position(commands[0], commands[1]);
    }

    private void showResult(final Board board, final String message) {
        OutputView.printMessage(message);
        OutputView.printGameResultNotice();
        if (InputView.isStatusInput()) {
            OutputView.printResult(new ChessResult(board));
        }
    }
}
