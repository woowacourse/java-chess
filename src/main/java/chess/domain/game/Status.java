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
        throw new UnsupportedOperationException();
    }

    @Override
    public GameState status() {
        return new Status(board, turnColor);
    }

    @Override
    public GameState finish() {
        return new Finished(board, turnColor);
    }

    @Override
    public boolean isRunnable() {
        return true;
    }

    @Override
    public GameState move(List<String> arguments) {
        board.move(arguments, turnColor);
        return new Running(board, turnColor.toggle());
    }

    @Override
    public Response getResponse() {
        return ScoreResponse.of(board.calculateScore());
    }
}
