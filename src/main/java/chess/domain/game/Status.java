package chess.domain.game;

import java.util.List;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.Route;
import chess.dto.GameResponse;
import chess.dto.ScoreGameResponse;

public class Status extends GameState {

    public Status(Board board, Color turnColor) {
        super(board, turnColor);
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("[ERROR] 게임이 이미 시작되었습니다.");
    }

    @Override
    public GameState finish() {
        return new Finished(board, turnColor);
    }

    @Override
    public GameState move(List<String> arguments) {
        board = board.move(Route.of(arguments), turnColor);
        if (board.isKingDead()) {
            return new Finished(board, turnColor);
        }
        return new Running(board, turnColor.toggle());
    }

    @Override
    public GameState status() {
        return new Status(board, turnColor);
    }

    @Override
    public boolean isRunnable() {
        return true;
    }

    @Override
    public GameResponse getResponse() {
        return ScoreGameResponse.of(board.calculateScore());
    }
}
