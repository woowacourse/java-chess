package chess.domain.state;

import chess.domain.Score;
import chess.domain.board.Chessboard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class Finish implements State {

    private static final String EXCEPTION_MOVE_IMPOSSIBLE = "Finish 상태에서 움직일 수 없습니다.";
    private static final String EXCEPTION_END = "종료된 게임입니다.";
    private static final String WHITE = "white";

    private final Chessboard chessboard;

    public Finish(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    @Override
    public State start() {
        return new Ready().start();
    }

    @Override
    public State move(Position source, Position target) {
        throw new UnsupportedOperationException(EXCEPTION_MOVE_IMPOSSIBLE);
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException(EXCEPTION_END);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isRightTurn(String turn) {
        return false;
    }

    @Override
    public Score computeScore(Color color) {
        if (chessboard.isKingNotAlive()) {
            return computeWinnerScore(color);
        }
        return chessboard.computeScore(color);
    }

    private Score computeWinnerScore(Color color) {
        if (chessboard.isWinWhite()) {
            return scoreWhite(color);
        }
        if (color.isSameColor(WHITE)) {
            return Score.create(new HashMap<>(), Color.WHITE);
        }
        return chessboard.computeScore(color);
    }

    private Score scoreWhite(Color color) {
        if (color.isSameColor(WHITE)) {
            return chessboard.computeScore(color);
        }
        return Score.create(new HashMap<>(), Color.BLACK);
    }

    @Override
    public void loadTurn() {
        throw new UnsupportedOperationException(EXCEPTION_TURN_LOAD);
    }

    @Override
    public State loadBoard(Map<String, Piece> pieces) {
        return new Finish(chessboard);
    }

    @Override
    public String turn() {
        return INIT_TURN;
    }

    @Override
    public Chessboard getChessboard() {
        return chessboard;
    }
}
