package chess.model.domain.state;

import java.util.Arrays;
import java.util.List;
import util.NullChecker;

public class GameStateAndMoveSquare {

    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final int THIRD_INDEX = 2;
    private static final int NO_MOVE_INDEX_COUNT = 1;
    private static final int MOVE_INDEX_COUNT = 3;
    private static final String SPLIT_DELIMITER = " ";
    private static final String WRONG_ARGUMENT_EXCEPTION_MESSAGE = "잘못된 인자입니다.";
    private static final String NO_MOVE_SQUARE_EXCEPTION_MESSAGE = "MoveSquare가 없습니다.";

    private final GameState gameState;
    private final MoveSquare moveSquare;

    public GameStateAndMoveSquare(String input) {
        NullChecker.validateNotNull(input);
        List<String> inputs = Arrays.asList(input.split(SPLIT_DELIMITER));
        this.gameState = GameState.of(inputs.get(FIRST_INDEX));
        validateByGameState(inputs);
        this.moveSquare = getMoveSquareByGameState(inputs);
    }

    private MoveSquare getMoveSquareByGameState(List<String> inputs) {
        if (gameState != GameState.MOVE) {
            return null;
        }
        return new MoveSquare(inputs.get(SECOND_INDEX), inputs.get(THIRD_INDEX));
    }

    public boolean isSameState(GameState gameState) {
        return this.gameState == gameState;
    }

    private void validateByGameState(List<String> inputs) {
        if ((gameState != GameState.MOVE && inputs.size() != NO_MOVE_INDEX_COUNT)
            || (gameState == GameState.MOVE && inputs.size() != MOVE_INDEX_COUNT)) {
            throw new IllegalArgumentException(WRONG_ARGUMENT_EXCEPTION_MESSAGE);
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public MoveSquare getMoveSquare() {
        if (gameState != GameState.MOVE) {
            throw new NullPointerException(NO_MOVE_SQUARE_EXCEPTION_MESSAGE);
        }
        return moveSquare;
    }
}
