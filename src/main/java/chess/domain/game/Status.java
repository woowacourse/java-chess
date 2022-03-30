package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.dto.Response;
import chess.dto.ScoreResponse;

import java.util.List;

public class Status extends Started {

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
        board.move(arguments, turnColor);
        if (!board.isKingDead(turnColor.toggle())) {
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
        return ScoreResponse.of(board.calculateScore());
    }
}
