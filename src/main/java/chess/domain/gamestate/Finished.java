package chess.domain.gamestate;

import chess.domain.Color;
import chess.domain.Winner;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class Finished implements State {
    private static final String INVALID_STATE_MOVE_EXCEPTION = "게임이 진행중이 아닐때는 기물을 이동할 수 없습니다.";
    private static final String NOT_RUNNING_STATE_GET_TURN_EXCEPTION = "게임 진행중이 아니기 때문에 턴을 알 수 없습니다.";

    private final Board board;

    public Finished(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        return new Running(new Board());
    }

    @Override
    public State load(Map<Position, Piece> board, Color turn) {
        return new Running(new Board(board, turn));
    }

    @Override
    public State move(Position beforePosition, Position afterPosition) {
        throw new IllegalStateException(INVALID_STATE_MOVE_EXCEPTION);
    }

    @Override
    public State end() {
        return this;
    }

    @Override
    public double statusOfBlack() {
        return board.scoreOfBlack();
    }

    @Override
    public double statusOfWhite() {
        return board.scoreOfWhite();
    }

    @Override
    public Winner findWinner() {
        if (board.hasBlackKingCaptured()) {
            return Winner.WHITE;
        }
        if (board.hasWhiteKingCaptured()) {
            return Winner.BLACK;
        }
        return findWinnerByScore();
    }

    private Winner findWinnerByScore() {
        final int compared = Double.compare(board.scoreOfBlack(), board.scoreOfWhite());
        if (compared > 0) {
            return Winner.BLACK;
        }
        if (compared < 0) {
            return Winner.WHITE;
        }
        return Winner.DRAW;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Color getTurn() {
        throw new IllegalStateException(NOT_RUNNING_STATE_GET_TURN_EXCEPTION);
    }
}
