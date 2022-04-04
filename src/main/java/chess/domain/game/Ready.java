package chess.domain.game;

import java.util.List;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.InitialBoardGenerator;

public class Ready extends GameState {

    public Ready() {
        super(Board.of(new InitialBoardGenerator()), Color.WHITE);
    }

    @Override
    public GameState start() {
        return new Running(board, turnColor);
    }

    @Override
    public GameState finish() {
        return new Finished(board, turnColor);
    }

    @Override
    public GameState move(List<String> arguments) {
        throw new UnsupportedOperationException("[ERROR] 지원하지 않는 명령입니다.");
    }

    @Override
    public boolean isRunnable() {
        return true;
    }
}
