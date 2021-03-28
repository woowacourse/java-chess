package domain.menu;

import domain.ChessGame;
import domain.dto.MenuDto;
import domain.dto.StatusDto;

public class Status implements Command {

    @Override
    public MenuDto execute(String command, ChessGame game) {
        return new StatusDto(game.piecesScore());
    }
}
