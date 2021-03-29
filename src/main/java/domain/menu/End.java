package domain.menu;

import domain.ChessGame;
import domain.dto.MenuDto;
import domain.dto.StatusDto;

public class End implements Command {
    @Override
    public MenuDto execute(String command, ChessGame game) {
        game.finish();
        return new StatusDto(game.piecesScore());
    }
}
