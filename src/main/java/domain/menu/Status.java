package domain.menu;

import domain.ChessGame;
import domain.dto.MenuDto;
import domain.dto.StatusDto;
import domain.exception.GameNotStartException;

public class Status implements Command {

    @Override
    public MenuDto execute(String command, ChessGame game) {
        if (game.isWait()) {
            throw new GameNotStartException();
        }
        return new StatusDto(game.piecesScore());
    }
}
