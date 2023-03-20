package controller;

import domain.game.Game;
import domain.piece.Position;
import domain.piece.Side;
import view.GameCommand;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ChessController {

    private static final String GAME_EXIT_COMMAND = "end";
    private static final String INPUT_COMMAND_DELIMITER = " ";
    private static final String POSITION_DELIMITER = "";
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int MOVE_COMMAND_INPUT_CORRECT_SIZE = 3;
    private static final int INPUT_SOURCE_STRING_INDEX = 0;
    private static final int INPUT_TARGET_STRING_INDEX = 1;
    private static final int INPUT_COMMAND_INDEX = 0;
    private static final int INPUT_SOURCE_POSITION_INDEX = 1;
    private static final int INPUT_TARGET_POSITION_INDEX = 2;

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        this.outputView.printGameGuideMessage();
        repeatByRunnable(inputView::requestStartCommand);
        Game game = Game.create();

        Side side = Side.WHITE;
        this.outputView.printChessBoard(game.getChessBoard());

        progressChess(game, side);
    }

    private void progressChess(Game game, Side side) {
        GameCommand gameCommand;
        do {
            Side currentSide = side;
            this.outputView.printSide(side);
            gameCommand = repeatBySupplier(() -> moveByUserInput(game, currentSide));
            this.outputView.printChessBoard(game.getChessBoard());
            side = changeSide(side);
        } while (gameCommand.equals(GameCommand.MOVE));
    }

    private GameCommand moveByUserInput(Game game, Side side) {
        String userInput = this.inputView.requestUserInputDuringProgressChess();
        if (userInput.equals(GAME_EXIT_COMMAND)) {
            return GameCommand.END;
        }
        validateUserInputToGameCommandAfterStart(userInput);

        List<Position> positions = convertUserInputToPositions(userInput);
        Position sourcePosition = positions.get(INPUT_SOURCE_STRING_INDEX);
        Position targetPosition = positions.get(INPUT_TARGET_STRING_INDEX);

        game.move(side, sourcePosition, targetPosition);
        return GameCommand.MOVE;
    }

    private List<Position> convertUserInputToPositions(String userInput) {
        List<String> inputs = Arrays.asList(userInput.split(INPUT_COMMAND_DELIMITER));
        String sourceString = inputs.get(INPUT_SOURCE_POSITION_INDEX);
        String targetString = inputs.get(INPUT_TARGET_POSITION_INDEX);

        List<String> sourceFileAndRank = Arrays.asList(sourceString.split(POSITION_DELIMITER));
        List<String> targetFileAndRank = Arrays.asList(targetString.split(POSITION_DELIMITER));

        List<Position> positions = new ArrayList<>();
        positions.add(Position.of(sourceFileAndRank.get(FILE_INDEX), sourceFileAndRank.get(RANK_INDEX)));
        positions.add(Position.of(targetFileAndRank.get(FILE_INDEX), targetFileAndRank.get(RANK_INDEX)));
        return positions;
    }

    private void validateUserInputToGameCommandAfterStart(String userInput) {
        List<String> inputs = Arrays.asList(userInput.split(INPUT_COMMAND_DELIMITER));
        if (inputs.size() != MOVE_COMMAND_INPUT_CORRECT_SIZE) {
            throw new IllegalArgumentException("올바르지 않은 움직임입니다. (예: move b2 b3)");
        }
        GameCommand command = GameCommand.from(inputs.get(INPUT_COMMAND_INDEX));

        if (command.equals(GameCommand.START)) {
            throw new IllegalArgumentException("게임이 이미 실행중입니다.");
        }
    }

    private Side changeSide(Side side) {
        if (side.equals(Side.WHITE)) {
            return Side.BLACK;

        }
        return Side.WHITE;
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

    private GameCommand repeatBySupplier(Supplier<GameCommand> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            this.outputView.printErrorMessage(e.getMessage());
            return repeatBySupplier(supplier);
        }
    }
}
