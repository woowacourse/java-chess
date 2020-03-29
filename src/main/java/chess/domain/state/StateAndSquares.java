package chess.domain.state;

import java.util.Arrays;
import java.util.List;
import util.NullChecker;

public class StateAndSquares {

    private final GameState gameState;
    private final MoveSquare moveSquare;

    public StateAndSquares(String input) {
        NullChecker.validateNotNull(input);
        List<String> inputs = Arrays.asList(input.split(" "));
        this.gameState = GameState.of(inputs.get(0));
        validateByGameState(inputs);
        this.moveSquare = getMoveSquareByGameState(inputs);
    }

    private MoveSquare getMoveSquareByGameState(List<String> inputs) {
        if (gameState != GameState.MOVE) {
            return null;
        }
        return new MoveSquare(inputs.get(1), inputs.get(2));
    }

    public boolean isSameState(GameState gameState) {
        return this.gameState == gameState;
    }

    private void validateByGameState(List<String> inputs) {
        if ((gameState != GameState.MOVE && inputs.size() != 1)
            || (gameState == GameState.MOVE && inputs.size() != 3)) {
            throw new IllegalArgumentException("잘못된 인자입니다.");
        }
    }

    public MoveSquare getMoveSquare() {
        return moveSquare;
    }

    public GameState getGameState() {
        return gameState;
    }
}
