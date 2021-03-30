package chess.controller;

import chess.domain.grid.ChessGame;
import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.GridStrategy;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ChessController {
    private static final String COMMAND_START = "start";
    private static final String COMMAND_END = "end";
    private static final String COMMAND_MOVE = "move";
    private static final String COMMAND_STATUS = "status";
    private static final String WHITE_SPACE_DELIMITER = " ";

    public final void run() {
        OutputView.printChessInstruction();
        GridStrategy gridStrategy = new NormalGridStrategy();
        Grid grid = new Grid(gridStrategy);
        ChessGame game = new ChessGame(grid);
        do {
            command(game);
        }
        while (!game.isFinished());
    }

    private void command(final ChessGame game) {
        try {
            runCommand(game);
        } catch (IllegalArgumentException error) {
            OutputView.printError(error);
        }
    }

    private void runCommand(final ChessGame game) {
        String command = InputView.command();
        if (command.equals(COMMAND_START)) {
            startCommand(game);
            return;
        }
        if (command.equals(COMMAND_END)) {
            endCommand(game);
            return;
        }
        if (command.equals(COMMAND_STATUS)) {
            statusCommand(game);
            return;
        }
        if (command.startsWith(COMMAND_MOVE)) {
            moveCommand(game, command);
            return;
        }
        throw new IllegalArgumentException("잘못 입력 하셨습니다.");
    }

    private void startCommand(final ChessGame game) {
        OutputView.printGridStatus(game.grid().lines());
        game.start();
    }

    private void endCommand(ChessGame game) {
        game.end();
    }

    private void statusCommand(final ChessGame game) {
        game.status();
        double blackScore = game.grid().score(Color.BLACK);
        double whiteScore = game.grid().score(Color.WHITE);
        OutputView.printScores(Color.BLACK, blackScore);
        OutputView.printScores(Color.WHITE, whiteScore);
        if (blackScore > whiteScore) {
            OutputView.printWinner(Color.BLACK);
        }
        if (blackScore < whiteScore) {
            OutputView.printWinner(Color.WHITE);
        }
    }

    private void moveCommand(final ChessGame game, final String command) {
        List<String> moveInput = Arrays.asList(command.split(WHITE_SPACE_DELIMITER));
        Position sourcePosition = new Position(moveInput.get(1));
        Position targetPosition = new Position(moveInput.get(2));
        Piece sourcePiece = game.grid().piece(sourcePosition);
        Piece targetPiece = game.grid().piece(targetPosition);
        game.move(sourcePiece, targetPiece);
        if (game.isGameOver()) {
            OutputView.printWinner(game.getWinner());
            return;
        }
        OutputView.printGridStatus(game.grid().lines());
    }
}
