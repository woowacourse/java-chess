package domain.menu;

import domain.ChessGame;
import dto.BoardDto;
import dto.MenuDto;
import domain.piece.objects.PieceFactory;

public class Start implements Command {
    @Override
    public MenuDto execute(String command, ChessGame game) {
        game.start(PieceFactory.createPieces());
        return new BoardDto(game.getBoard());
    }
}
