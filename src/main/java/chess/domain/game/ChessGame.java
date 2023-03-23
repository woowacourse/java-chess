package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Side;
import chess.domain.position.Position;
import java.util.List;

public class ChessGame {

    private State state;
    private Turn turn;
    private final Board board;

    public ChessGame(final Board board) {
        this.state = State.RUN;
        this.board = board;
        this.turn = Turn.WHITE;
    }

    public void start() {
        if (state.isStart()) {
            throw new IllegalArgumentException("이미 시작했습니다.");
        }
        this.state = State.START;
    }

    public void end() {
        this.state = State.END;
    }

    public void moveOrNot(final Position source, final Position target) {
        checkPlayable();
        checkTurn(source);
        boolean kingDeath = board.isKing(target);
        final List<Position> movablePositions = board.findMovablePositions(source);
        checkMovable(movablePositions, target);
        board.move(source, target);
        endGameIfKingDeath(kingDeath);
        changeTurn();
    }

    private void endGameIfKingDeath(final boolean flag) {
        if (flag) {
            end();
        }
    }

    private void checkTurn(final Position source) {
        if (!board.isRightTurn(source, turn)) {
            throw new IllegalArgumentException("잘못된 차례입니다.");
        }
    }

    private void checkPlayable() {
        if (state.isStart()) {
            return;
        }
        throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
    }

    private void checkMovable(final List<Position> movablePositions, final Position target) {
        if (!movablePositions.contains(target)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    private void changeTurn() {
        turn = turn.changeTurn();
    }

    public boolean isRunnable() {
        return state.isRunnable();
    }

    public boolean isStart() {
        return state.isStart();
    }

    public Board getBoard() {
        return board;
    }

    public List<Double> calculateScore() {
        checkCalculable();
        return List.of(board.calculateScore(Side.WHITE), board.calculateScore(Side.BLACK));
    }

    private void checkCalculable() {
        if (state.isCalculable()) {
            return;
        }
        throw new IllegalArgumentException("게임을 시작해주세요.");
    }
}