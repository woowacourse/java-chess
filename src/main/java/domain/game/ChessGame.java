package domain.game;

import domain.piece.Position;
import domain.piece.Side;

import java.util.Arrays;
import java.util.List;

public class ChessGame {

    private static final String POSITION_DELIMITER = "";
    private static final int SOURCE_FILE_INDEX = 0;
    private static final int SOURCE_RANK_INDEX = 1;
    private static final int TARGET_FILE_INDEX = 2;
    private static final int TARGET_RANK_INDEX = 3;

    private final Board board;
    private Side currentTurn = Side.WHITE;
    private GameState state;

    public ChessGame(Board board) {
        this.board = board;
        this.state = GameState.RUN;
    }

    public void move(String userInput) {
        List<String> inputs = Arrays.asList(userInput.split(POSITION_DELIMITER));

        String sourceFile = inputs.get(SOURCE_FILE_INDEX);
        String sourceRank = inputs.get(SOURCE_RANK_INDEX);
        String targetFile = inputs.get(TARGET_FILE_INDEX);
        String targetRank = inputs.get(TARGET_RANK_INDEX);

        Position sourcePosition = Position.of(sourceFile, sourceRank);
        Position targetPosition = Position.of(targetFile, targetRank);
        if (board.isMovable(sourcePosition, targetPosition, currentTurn)) {
            this.state = calculateNextGameState(targetPosition);
            board.move(sourcePosition, targetPosition);
        }
        currentTurn = currentTurn.nextSide();
    }

    public boolean isExitGame() {
        return state == GameState.END;
    }

    private GameState calculateNextGameState(Position targetPosition) {
        if (board.isKing(targetPosition)) {
            return GameState.END;
        }
        return GameState.RUN;
    }
}
