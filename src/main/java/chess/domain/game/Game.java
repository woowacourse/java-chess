package chess.domain.game;

import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.piece.Color;

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

    public Result getResult() {
        return new Result(board.getScoreOf(Color.WHITE), board.getScoreOf(Color.BLACK));
    }

    public Board getBoard() {
        return board;
    }
}
