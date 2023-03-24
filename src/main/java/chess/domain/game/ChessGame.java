package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Side;
import chess.domain.position.Position;

public class ChessGame {

    private State state;
    private Turn turn;
    private final Board board;

    public ChessGame(final Board board) {
        this.state = State.RUN;
        this.turn = Turn.WHITE;
        this.board = board;
    }

    public void movePiece(final Position source, final Position target) {
        checkPlayable();
        checkTurn(source);
        board.move(source, target);
        changeTurn();
        checkKingCaptured();
    }

    private void checkPlayable() {
        if (!state.isStart()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }

    private void checkTurn(final Position position) {
        final Side side = board.findSideByPosition(position);
        if (!turn.isTurnValid(side)) {
            throw new IllegalArgumentException("다음 턴에 움직일 수 있습니다.");
        }
    }

    private void changeTurn() {
        turn = turn.change();
    }

    private void checkKingCaptured() {
        if (board.isKingCaptured()) {
            end();
        }
    }

    public void start() {
        this.state = State.START;
    }

    public void end() {
        this.state = State.END;
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
}
