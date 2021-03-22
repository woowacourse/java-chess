package domain.menu;

import domain.ChessGame;
import domain.dto.MenuDto;

public interface Command {
    MenuDto execute(String command, ChessGame game);
}
