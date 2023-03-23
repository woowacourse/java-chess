package chess.domain.game;

import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.board.Score;
import chess.domain.piece.Color;

import java.util.HashMap;
import java.util.Map;

public abstract class Game {
    protected final Board board;

    protected Game(Board board) {
        this.board = board;

    }

    public static Game of(Board board) {
        if (board.getScoreOf(Color.WHITE).getScore() == 0 || board.getScoreOf(Color.BLACK).getScore() == 0) {
            return new EndGame(board);
        }
        return new RunningGame(board);
    }

    public abstract Game move(Position from, Position to);

    public abstract boolean isEnd();

    public Map<Color, Score> getStatus() {
        HashMap<Color, Score> result = new HashMap<>();
        result.put(Color.WHITE, board.getScoreOf(Color.WHITE));
        result.put(Color.BLACK, board.getScoreOf(Color.BLACK));
        return result;
    }

    public Board getBoard() {
        return board;
    }
}
