package domain.menu;

import domain.ChessGame;
import dto.MenuDto;
import dto.StatusDto;

public class End implements Command {
    @Override
    public MenuDto execute(String command, ChessGame game) {
        game.finish();
        return new StatusDto(game.blackScore(), game.whiteScore());
    }
}
