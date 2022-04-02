package chess.domain.game;

import java.util.List;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.InitialBoardGenerator;
import chess.dto.GameResponse;

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
    public GameState status() {
        throw new UnsupportedOperationException("[ERROR] 지원하지 않는 명령입니다.");
    }

    @Override
    public boolean isRunnable() {
        return true;
    }

    @Override
    public GameResponse getResponse() {
        throw new UnsupportedOperationException("[ERROR] 준비상태에서는 점수를 얻을 수 없습니다.");
    }
}
