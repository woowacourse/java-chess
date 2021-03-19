package chess.controller;

import chess.domain.Board;
import chess.domain.piece.Team;
import chess.domain.position.Position;
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
        Team team = Team.WHITE;
        proceedMain(board, team);
    }

    private void proceedMain(final Board board, Team team) {
        try {
            proceed(board, team);
        } catch (IllegalStateException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            proceedMain(board, team);
        }
    }

    private void proceed(final Board board, Team team) {
        OutputView.printCurrentBoard(board.unwrap());
        final List<String> runtimeCommands = InputView.inputRuntimeCommand();

        if (InputView.END_COMMAND.equals(runtimeCommands.get(0))) {
            showResult(board);
            return;
        }

        final Position start = getPositionByCommands(runtimeCommands.get(1).split(""));
        final Position end = getPositionByCommands(runtimeCommands.get(2).split(""));
        board.move(start, end, team);

        if (board.isKingDead()) {
            showResult(board);
            return;
        }
        team = team.oppositeTeam();
        proceed(board, team);
    }

    private Position getPositionByCommands(final String[] commands) {
        return new Position(commands[0], commands[1]);
    }

    private void showResult(final Board board) {
        OutputView.printGameOverMessage();
        if (InputView.isStatusInput()) {
            OutputView.printResult(board.getWinner(), board);
        }
    }
}
