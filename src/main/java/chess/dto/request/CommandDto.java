package chess.dto.request;

import chess.controller.ColumnToNumber;
import chess.controller.RowToNumber;
import chess.domain.position.Position;
import chess.view.GameCommand;

import java.util.List;

public class CommandDto {

    private final GameCommand gameCommand;
    private final Position startPosition;
    private final Position endPosition;

    private CommandDto(GameCommand gameCommand, Position startPosition, Position endPosition) {
        this.gameCommand = gameCommand;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public static CommandDto of(List<String> userInput) {
        GameCommand gameCommand = GameCommand.of(userInput.get(0));
        Position startPosition = null;
        Position endPosition = null;
        if (userInput.size() == 3) {
            startPosition = convertInputToPosition(userInput.get(1));
            endPosition = convertInputToPosition(userInput.get(2));
        }
        return new CommandDto(gameCommand, startPosition, endPosition);
    }

    private static Position convertInputToPosition(String input) {
        int row = RowToNumber.of(input.charAt(1));
        int column = ColumnToNumber.of(input.charAt(0));

        return Position.of(row, column);
    }

    public GameCommand getGameCommand() {
        return gameCommand;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }
}
