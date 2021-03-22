package domain.menu;

import domain.ChessGame;
import domain.dto.BoardDto;
import domain.dto.MenuDto;
import domain.exception.GameNotStartException;
import domain.piece.Position;

public class Move implements Command {

    @Override
    public MenuDto execute(String command, ChessGame game) {
        if (!game.isRunning()) {
            throw new GameNotStartException();
        }
        startMoveMenu(command, game);
        return new BoardDto(game.getBoard());
    }

    private void startMoveMenu(String command, ChessGame game) {
        String startPosition = command.split(" ")[1];
        String endPosition = command.split(" ")[2];
        game.move(Position.of(startPosition), Position.of(endPosition));
    }
}
