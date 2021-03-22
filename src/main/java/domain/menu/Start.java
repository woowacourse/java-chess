package domain.menu;

import domain.ChessGame;
import domain.dto.BoardDto;
import domain.dto.MenuDto;
import domain.exception.CannotStartException;
import domain.piece.objects.PieceFactory;

public class Start implements Command {
    @Override
    public MenuDto execute(String command, ChessGame game) {
        if (!game.isWait()) {
            throw new CannotStartException();
        }
        game.start(PieceFactory.createPieces());
        return new BoardDto(game.getBoard());
    }
}
