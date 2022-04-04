package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class Finished implements State {
    private static final String ERROR_CANT_MOVE = "게임이 종료되어 기물을 이동할 수 없습니다.";

    private final Board board;

    public Finished(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        Camp.initializeTurn();
        return new Running(BoardInitializer.get());
    }

    @Override
    public State load(Map<Position, Piece> board, boolean whiteTurn) {
        Camp.initializeTurn();
        if (!whiteTurn) {
            Camp.switchTurn();
        }
        return new Running(new Board(board));
    }

    @Override
    public State move(Position sourcePosition, Position targetPosition) {
        throw new UnsupportedOperationException(ERROR_CANT_MOVE);
    }

    @Override
    public State end() {
        return this;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public Map<Camp, Score> getScores() {
        return Score.of(this.board);
    }

    @Override
    public Camp getWinner() {
        return Score.winnerOf(this.board);
    }
}
