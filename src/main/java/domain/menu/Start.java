package domain.menu;

import domain.Board;
import domain.ChessGame;
import domain.piece.objects.PieceFactory;
import dto.BoardDto;
import dto.MenuDto;
import dto.PiecesDto;
import dto.StatusDto;

public class Start implements Command {
    @Override
    public MenuDto execute(String command, ChessGame game) {
        game.start();
        return new BoardDto(game.getBoard());
    }

    @Override
    public PiecesDto executeWebCommand(String command, ChessGame game) {
        game.start();
        return new PiecesDto(game.getBoard().getPieceMap(),
                new StatusDto(game.blackScore(), game.whiteScore()), game.isEnd(), game.getTurn());
    }
}
