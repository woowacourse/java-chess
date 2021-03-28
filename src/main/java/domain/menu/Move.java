package domain.menu;

import domain.ChessGame;
import domain.dto.BoardDto;
import domain.dto.MenuDto;
import domain.exception.InvalidMoveCommandException;
import domain.piece.position.Position;

public class Move implements Command {
    public static final int COMMAND_NUMBERS = 3;

    @Override
    public MenuDto execute(String command, ChessGame game) {
        startMoveMenu(command, game);
        return new BoardDto(game.getBoard());
    }

    private void startMoveMenu(String command, ChessGame game) {
        invalidCommand(command);
        String startPosition = command.split(" ")[1];
        String endPosition = command.split(" ")[2];
        game.move(Position.of(startPosition), Position.of(endPosition));
    }

    private void invalidCommand(String command) {
        if (command.split(" ").length != COMMAND_NUMBERS) {
            throw new InvalidMoveCommandException(command);
        }
    }
}
