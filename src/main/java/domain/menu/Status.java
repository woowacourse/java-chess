package domain.menu;

import domain.ChessGame;
import dto.MenuDto;
import dto.StatusDto;

public class Status implements Command {

    @Override
    public MenuDto execute(String command, ChessGame game) {
        return new StatusDto(game.piecesScore());
    }
}
