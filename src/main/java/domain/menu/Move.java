package domain.menu;

import domain.ChessGame;
import domain.exception.InvalidMoveCommandException;
import domain.piece.position.Position;
import dto.BoardDto;
import dto.MenuDto;
import dto.PiecesDto;
import dto.StatusDto;

public class Move implements Command {
    public static final int COMMAND_NUMBERS = 3;

    @Override
    public MenuDto execute(String command, ChessGame game) {
        startMoveMenu(command, game);
        return new BoardDto(game.getBoard());
    }

    @Override
    public PiecesDto executeWebCommand(String command, ChessGame game) {
        startMoveMenu(command, game);
        return new PiecesDto(game.getBoard().getPieceMap(),
                new StatusDto(game.blackScore(), game.whiteScore()), game.isEnd(), game.getTurn());
    }

    private void startMoveMenu(String command, ChessGame game) {
        invalidCommand(command);
        String startPosition = command.split(" ")[1];
        String endPosition = command.split(" ")[2];
        game.move(Position.of(startPosition), Position.of(endPosition));
    }

    private void invalidCommand(String command) {
        if (command.split(" ").length != COMMAND_NUMBERS) {
            throw new InvalidMoveCommandException(command);
        }
    }
}
