package chess.controller;

import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.GridStrategy;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ChessController {
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";
    private static final String STATUS_COMMAND = "status";
    private static final String WHITE_SPACE_DELIMITER = " ";
    private static final int SOURCE_ARG_INDEX = 1;
    private static final int TARGET_ARG_INDEX = 2;

    public void run() {
        OutputView.printChessInstruction();
        GridStrategy gridStrategy = new NormalGridStrategy();
        Grid grid = new Grid(gridStrategy);
        do {
            inputCommandAndRun(grid);
        }
        while (!grid.isFinished());
    }

    private void inputCommandAndRun(Grid grid) {
        try {
            tryToInputCommandAndRun(grid);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void tryToInputCommandAndRun(Grid grid) {
        String command = InputView.command();
        if (command.equals(START_COMMAND)) {
            OutputView.printGridStatus(grid.lines());
            grid.start();
        }
        if (command.equals(END_COMMAND)) {
            grid.end();
        }
        if (command.startsWith(MOVE_COMMAND)) {
            runMoveCommand(grid, command);
        }
        if (command.equals(STATUS_COMMAND)) {
            runStatusCommand(grid);
        }
        throw new IllegalArgumentException("잘못된 입력값입니다.");
    }

    private void runMoveCommand(Grid grid, String command) {
        List<String> moveInput = Arrays.asList(command.split(WHITE_SPACE_DELIMITER));
        Position sourcePosition = new Position(moveInput.get(SOURCE_ARG_INDEX));
        Position targetPosition = new Position(moveInput.get(TARGET_ARG_INDEX));
        grid.move(grid.piece(sourcePosition), grid.piece(targetPosition));
        if (grid.isKingCaught()) {
            OutputView.printWinner(grid.winnerColor());
            return;
        }
        OutputView.printGridStatus(grid.lines());
    }

    private void runStatusCommand(Grid grid) {
        grid.status();
        double blackScore = grid.score(Color.BLACK);
        double whiteScore = grid.score(Color.WHITE);
        OutputView.printScores(Color.BLACK, blackScore);
        OutputView.printScores(Color.WHITE, whiteScore);
        OutputView.printWinner(grid.winnerColor());
    }
}
