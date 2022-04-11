package chess.domain.game;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.Route;
import chess.dto.Arguments;

public class Running extends GameState {

    private static final String STATE = "RUNNING";

    public Running(Board board, Color turnColor) {
        super(board, turnColor);
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("[ERROR] 이미 게임이 진행중입니다.");
    }

    @Override
    public GameState finish() {
        return new Finished(board, turnColor);
    }

    @Override
    public GameState move(Arguments arguments) {
        board = board.move(Route.of(arguments), turnColor);
        if (board.isKingDead()) {
            return new Finished(board, turnColor);
        }
        return new Running(board, turnColor.toggle());
    }

    @Override
    public String getState() {
        return STATE;
    }

    @Override
    public boolean isRunnable() {
        return true;
    }
}
