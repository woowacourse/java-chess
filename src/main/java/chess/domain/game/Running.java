package chess.domain.game;

import java.util.List;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.dto.BoardResponse;
import chess.dto.Response;

public class Running extends Started {

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
    public GameState move(List<String> arguments) {
        if (!board.move(arguments, turnColor)) {
            return new Running(board, turnColor.toggle());
        }
        return new Finished(board, turnColor);
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
    public Response getResponse() {
        return BoardResponse.of(board.getPointPieces(), turnColor);
    }
}
