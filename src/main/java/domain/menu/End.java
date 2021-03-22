package domain.menu;

import domain.ChessGame;
import domain.dto.BoardDto;
import domain.dto.MenuDto;

public class End implements Command {
    @Override
    public MenuDto execute(String command, ChessGame game) {
        game.end();
        return new BoardDto(game.getBoard());
    }
}
