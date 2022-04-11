package chess.domain.game;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.dto.Arguments;

public class Finished extends GameState {

    private static final String END_MESSAGE = "[ERROR] 이미 게임이 끝났습니다.";

    private static final String STATE = "FINISHED";

    public Finished(Board board, Color turnColor) {
        super(board, turnColor);
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException(END_MESSAGE);
    }

    @Override
    public GameState finish() {
        throw new UnsupportedOperationException(END_MESSAGE);
    }

    @Override
    public GameState move(Arguments arguments) {
        throw new UnsupportedOperationException(END_MESSAGE);
    }

    @Override
    public String getState() {
        return STATE;
    }

    @Override
    public boolean isRunnable() {
        return false;
    }

}
