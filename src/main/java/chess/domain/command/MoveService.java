package chess.domain.command;

import chess.domain.game.ChessGame;
import chess.domain.piece.Position;
import chess.exception.CommandFormatException;

public class MoveService {
    private static final String COMMAND = "move";
    private static final String POSITION_FORMAT = "[a-h][1-8]";

    private final ChessGame chessGame;

    public MoveService(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void move(String source, String target) {
        validateRightInputs(source, target);

        Position sourcePosition = getPositionFromInput(source);
        Position targetPosition = getPositionFromInput(target);

        chessGame.move(sourcePosition, targetPosition);
    }

    private void validateRightInputs(String source, String target) {
        if (isRightPositionFormat(source) && isRightPositionFormat(target)) {
            return;
        }

        throw new CommandFormatException();
    }

    private Position getPositionFromInput(String input) {
        String[] inputs = input.split("");

        int column = inputs[0].charAt(0) - 'a';
        int row = chessGame.getBoardRow() - Integer.parseInt(inputs[1]);

        return new Position(row, column);
    }

    private boolean isRightPositionFormat(String inputs) {
        return inputs.matches(POSITION_FORMAT);
    }

}
