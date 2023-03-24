package chess.domain;

import chess.domain.exception.StartCommandException;
import chess.dto.GameStatusDto;
import chess.dto.SquareMoveDto;
import chess.view.Command;

public class ChessGame {

    private final Board board;
    private Turn turn;

    public ChessGame() {
        this.board = Board.create();
        this.turn = Turn.getFirstTurn();
    }

    public void start(final Command command) {
        if (command.equals(Command.START)) {
            return;
        }
        throw new StartCommandException();
    }

    public void move(final SquareMoveDto squareMoveDto) {
        final Turn nextTurn = Turn.next(turn);
        board.validateTurn(turn, squareMoveDto.getCurrent());
        board.move(squareMoveDto.getCurrent(), squareMoveDto.getDestination());
        turn = nextTurn;
    }

    public void restart() {
        board.reset();
        turn = Turn.getFirstTurn();
    }

    public boolean isEnd() {
        return board.isKingCaught();
    }

    public GameStatusDto getGameStatus() {
        return GameStatusDto.from(board);
    }
}
