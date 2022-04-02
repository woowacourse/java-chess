package chess.domain.game;

import java.util.List;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.dto.EmptyGameResponse;
import chess.dto.GameResponse;

public class Finished extends GameState {

    Finished(Board board, Color turnColor) {
        super(board, turnColor);
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("[ERROR] 이미 게임이 시작되었습니다.");
    }

    @Override
    public GameState finish() {
        throw new UnsupportedOperationException("[ERROR] 이미 게임이 끝났습니다.");
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
        return false;
    }

    @Override
    public GameResponse getResponse() {
        return new EmptyGameResponse();
    }
}
