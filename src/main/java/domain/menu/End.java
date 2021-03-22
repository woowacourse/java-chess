package domain.menu;

import domain.ChessGame;
import domain.dto.BoardDto;
import domain.dto.MenuDto;
import domain.exception.GameNotStartException;

public class End implements Command {
    @Override
    public MenuDto execute(String command, ChessGame game) {
        if (!game.isRunning()) {
            throw new GameNotStartException();
        }
        game.end();
        return new BoardDto(game.getBoard());
    }
}
