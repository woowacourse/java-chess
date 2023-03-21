package chess.domain;

import chess.domain.exception.StartCommandException;
import chess.dto.GameStatusDto;
import chess.dto.SquareMoveDto;
import chess.view.Command;

public class ChessGame {

    private final Board board;

    public ChessGame() {
        this.board = Board.create();
    }

    public void start(final Command command) {
        if (command.equals(Command.START)) {
            return;
        }
        throw new StartCommandException();
    }

    public void move(final SquareMoveDto squareMoveDto) {
        board.move(squareMoveDto.getCurrent(), squareMoveDto.getDestination());
    }

    public void restart() {
        board.reset();
    }

    public GameStatusDto getGameStatus() {
        return GameStatusDto.from(board);
    }
}
