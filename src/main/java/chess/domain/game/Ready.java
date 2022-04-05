package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.InitialBoardGenerator;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.dto.Response;

import java.util.List;

public class Ready extends Started {

    public Ready() {
        super(null, null);
    }

    @Override
    public GameState start(Board board, Color turnColor) {
        return new Running(board, turnColor);
    }

    @Override
    public GameState finish() {
        return new Finished(board, turnColor);
    }

    @Override
    public GameState move(List<Point> arguments) {
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
    public Response getResponse() {
        throw new UnsupportedOperationException("[ERROR] 지원하지 않는 명령입니다.");
    }
}
