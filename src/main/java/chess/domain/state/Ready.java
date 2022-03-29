package chess.domain.state;

import chess.Command;
import chess.domain.board.Board;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import chess.domain.piece.Color;

public class Ready implements ChessState {

    private static final String CANNOT_IMPLEMENT_COMMAND = "현재 실행할 수 없는 명령입니다.";

    @Override
    public ChessState execute(final Command command) {
        if (!(command == Command.START)) {
            throw new IllegalArgumentException(CANNOT_IMPLEMENT_COMMAND);
        }
        return new Running(new Board(new CreateCompleteBoardStrategy()), Color.WHITE);
    }
}
