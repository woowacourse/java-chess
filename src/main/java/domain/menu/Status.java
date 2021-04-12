package domain.menu;

import domain.ChessGame;
import dto.MenuDto;
import dto.PiecesDto;
import dto.StatusDto;

public class Status implements Command {

    @Override
    public MenuDto execute(String command, ChessGame game) {
        return new StatusDto(game.blackScore(), game.whiteScore());
    }

    @Override
    public PiecesDto executeWebCommand(String command, ChessGame game) {
        return new PiecesDto(game.getBoard().getPieceMap(),
                new StatusDto(game.blackScore(), game.whiteScore()), game.isEnd(), game.getTurn());
    }
}
