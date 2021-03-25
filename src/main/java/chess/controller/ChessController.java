package chess.controller;

import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.GridStrategy;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.piece.Color;
import chess.domain.state.End;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static final String COMMAND_START = "start";
    private static final String COMMAND_END = "end";
    private static final String COMMAND_MOVE = "move";
    private static final String COMMAND_STATUS = "status";

    private GameState gameState;

    public ChessController() {
        this.gameState = new Ready();
    }

    public final void run() {
        OutputView.printChessInstruction();
        GridStrategy gridStrategy = new NormalGridStrategy();
        Grid grid = new Grid(gridStrategy);
        do {
            command(grid);
        }
        while (!gameState.isFinished());
    }

    private void command(final Grid grid) {
        try {
            runCommand(grid);
        } catch (IllegalArgumentException error) {
            OutputView.printError(error);
        }
    }

    private void runCommand(final Grid grid) {
        String command = InputView.command();
        if (command.equals(COMMAND_START)) {
            startCommand(grid, command);
            return;
        }
        if (command.equals(COMMAND_END)) {
            endCommand(grid, command);
            return;
        }
        if (command.equals(COMMAND_STATUS)) {
            statusCommand(grid, command);
            return;
        }
        if (command.startsWith(COMMAND_MOVE)) {
            moveCommand(grid, command);
            return;
        }
        throw new IllegalArgumentException("잘못 입력 하셨습니다.");
    }

    private void startCommand(final Grid grid, final String command) {
        OutputView.printGridStatus(grid.lines());
        gameState = gameState.run(grid, command);
    }

    private void endCommand(final Grid grid, final String command) {
        gameState = gameState.run(grid, command);
    }

    private void statusCommand(final Grid grid, final String command) {
        double blackScore = grid.score(Color.BLACK);
        double whiteScore = grid.score(Color.WHITE);
        OutputView.printScores(Color.BLACK, blackScore);
        OutputView.printScores(Color.WHITE, whiteScore);
        if (blackScore > whiteScore) {
            OutputView.printWinner(Color.BLACK);
        }
        if (blackScore < whiteScore) {
            OutputView.printWinner(Color.WHITE);
        }
        gameState = gameState.run(grid, command);
    }

    private void moveCommand(final Grid grid, final String command) {
        gameState = gameState.run(grid, command);
        if (!grid.kingSurvived(Color.BLACK)) {
            OutputView.printWinner(Color.WHITE);
            gameState = new End();
            return;
        }
        if (!grid.kingSurvived(Color.WHITE)) {
            OutputView.printWinner(Color.BLACK);
            gameState = new End();
            return;
        }
        OutputView.printGridStatus(grid.lines());
    }
}
