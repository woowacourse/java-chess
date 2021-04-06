package domain.menu;

import domain.ChessGame;
import dto.MenuDto;
import dto.PiecesDto;

public interface Command {
    MenuDto execute(String command, ChessGame game);

    PiecesDto executeWebCommand(String command, ChessGame game);
}
