package controller;

import domain.game.Board;
import domain.game.ChessBoardGenerator;
import domain.game.ChessGame;
import domain.game.GameState;
import domain.piece.Position;
import domain.piece.Side;
import service.ChessGameService;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ChessController {

    private static final String GAME_EXIT_COMMAND = "end";
    private static final String POSITION_DELIMITER = "";
    private static final int SOURCE_FILE_INDEX = 0;
    private static final int SOURCE_RANK_INDEX = 1;
    private static final int TARGET_FILE_INDEX = 2;
    private static final int TARGET_RANK_INDEX = 3;

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameService chessGameService;

    public ChessController(InputView inputView, OutputView outputView, ChessGameService chessGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGameService = chessGameService;
    }

    public void run() {
        this.outputView.printGameGuideMessage();
        repeatByRunnable(inputView::requestStartCommand);
        Board chessBoard = new Board(new ChessBoardGenerator().generate());
        ChessGame chessGame = new ChessGame(chessBoard, Side.WHITE, GameState.RUN);

        this.outputView.printChessBoard(chessBoard.getChessBoard());
        while (play(chessGame)) {
            outputView.printChessBoard(chessBoard.getChessBoard());
        }

    }

    private boolean play(ChessGame chessGame) {
        String userCommand = repeatBySupplier(() -> inputView.requestUserCommandInGame());
        if (isExitCommand(userCommand) || chessGame.isExitGame()) {
            return false;
        }
        if (userCommand.equals("status")) {
            Map<Side, Double> scores = chessGame.calculateScores();
            outputView.printGameScores(scores);
            outputView.printWinner(chessGame.calculateWinner());
            return true;
        }
        try {
            List<String> inputs = Arrays.asList(userCommand.split(POSITION_DELIMITER));

            Position sourcePosition = Position.of(inputs.get(SOURCE_FILE_INDEX), inputs.get(SOURCE_RANK_INDEX));
            Position targetPosition = Position.of(inputs.get(TARGET_FILE_INDEX), inputs.get(TARGET_RANK_INDEX));

            chessGame.move(sourcePosition, targetPosition);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            play(chessGame);
        }
        return true;
    }

    private boolean isExitCommand(String command) {
        return command.equals(GAME_EXIT_COMMAND);
    }

    private Runnable repeatByRunnable(Runnable runnable) {
        try {
            runnable.run();
            return runnable;
        } catch (IllegalArgumentException e) {
            this.outputView.printErrorMessage(e.getMessage());
            return repeatByRunnable(runnable);
        }
    }

    private String repeatBySupplier(Supplier<String> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            this.outputView.printErrorMessage(e.getMessage());
            return repeatBySupplier(supplier);
        }
    }
}
