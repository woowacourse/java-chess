package chess.domain;

import chess.domain.exception.StartCommandException;
import chess.dto.GameStatusDto;
import chess.dto.SquareMoveDto;
import chess.view.Command;

public class ChessGame {

    // TODO: 불변 만들기
    private Board board;

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

    public void reset() {
        board = Board.create();
    }

    public GameStatusDto getGameStatus() {
        return GameStatusDto.from(board);
    }
}
