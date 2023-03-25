package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Side;
import chess.domain.position.Path;
import chess.domain.position.Position;

public class ChessGame {

    private final long id;
    private State state;
    private Turn turn;
    private final Board board;

    public ChessGame(final long id, final Board board, Turn turn) {
        this.id = id;
        this.state = State.RUN;
        this.board = board;
        this.turn = turn;
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

    private void clear() {
        this.state = State.CLEAR;
    }

    public void moveOrNot(final Position source, final Position target) {
        checkPlayable();
        checkTurn(source);
        boolean kingDeath = board.isKing(target);
        final Path path = board.findMovablePositions(source);
        path.checkMovable(target);
        board.move(source, target);
        endGameIfKingDeath(kingDeath);
        changeTurn();
    }

    private void endGameIfKingDeath(final boolean kingDeath) {
        if (kingDeath) {
            clear();
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

    private void changeTurn() {
        turn = turn.changeTurn();
    }

    public boolean isRunnable() {
        return state.isRunnable();
    }

    public boolean isStart() {
        return state.isStart();
    }

    public boolean isClear() {
        return state.isClear();
    }

    public Turn getTurn() {
        return turn;
    }

    public long getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public Double calculateScore(Side side) {
        checkCalculable();
        return board.calculateScore(side);
    }

    public Side calculateWinner() {
        final Side winner = board.calculateWinner();

        if (winner.isNeutrality()) {
            return Side.calculateWinner(board.calculateScore(Side.WHITE), board.calculateScore(Side.BLACK));
        }
        return winner;
    }

    private void checkCalculable() {
        if (state.isCalculable()) {
            return;
        }
        throw new IllegalArgumentException("게임을 시작해주세요.");
    }
}