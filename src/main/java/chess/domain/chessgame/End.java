package chess.domain.chessgame;

import chess.domain.board.Score;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class End implements State {

    private static final String END_CANT_EXECUTE_COMMAND_MESSAGE = "종료 상태에선 명령을 실행할 수 없습니다.";
    private static final End INSTANCE = new End();

    private End() {
    }

    public static End getInstance() {
        return INSTANCE;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException(END_CANT_EXECUTE_COMMAND_MESSAGE);
    }

    @Override
    public State move(final Position from, final Position to) {
        throw new UnsupportedOperationException(END_CANT_EXECUTE_COMMAND_MESSAGE);
    }

    @Override
    public Map<Color, Score> status() {
        throw new UnsupportedOperationException(END_CANT_EXECUTE_COMMAND_MESSAGE);
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException(END_CANT_EXECUTE_COMMAND_MESSAGE);
    }

    @Override
    public Map<Position, Piece> getBoard() {
        throw new UnsupportedOperationException(END_CANT_EXECUTE_COMMAND_MESSAGE);
    }

    @Override
    public State load(final long id) {
        throw new UnsupportedOperationException(END_CANT_EXECUTE_COMMAND_MESSAGE);
    }

    @Override
    public List<Long> findAllId() {
        throw new UnsupportedOperationException(END_CANT_EXECUTE_COMMAND_MESSAGE);
    }
}
