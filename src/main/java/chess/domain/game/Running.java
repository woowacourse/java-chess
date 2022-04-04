package chess.domain.game;

import chess.dto.Arguments;
import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.Route;

public class Running extends GameState {

    Running(Board board, Color turnColor) {
        super(board, turnColor);
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("[ERROR] 이미 게임이 시작되었습니다.");
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
    public boolean isRunnable() {
        return true;
    }
}
