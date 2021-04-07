package chess.domain.command;

import chess.domain.game.ChessGame;
import chess.domain.piece.Position;
import chess.exception.CommandFormatException;

public class MoveCommand extends CommandInit {

    private static final int COUNT_OF_ELEMENTS = 3;
    private static final String COMMAND = "move";
    private static final String POSITION_FORMAT = "[a-h][1-8]";

    public MoveCommand(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void execute(String input) {
        String[] inputs = input.split(" ");
        validateRightInputs(inputs);

        Position source = getPositionFromInput(inputs[1]);
        Position target = getPositionFromInput(inputs[2]);

        chessGame.move(source, target);
    }

    private void validateRightInputs(final String[] inputs) {
        if (isContainsThreeElements(inputs) &&
                isRightCommand(inputs) &&
                isRightPositionFormat(inputs)) {
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

    private boolean isContainsThreeElements(String[] inputs) {
        return inputs.length == COUNT_OF_ELEMENTS;
    }

    private boolean isRightCommand(String[] inputs) {
        return inputs[0].equals(COMMAND);
    }

    private boolean isRightPositionFormat(String[] inputs) {
        String source = inputs[1];
        String target = inputs[2];

        return source.matches(POSITION_FORMAT) &&
                target.matches(POSITION_FORMAT);
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }

}
