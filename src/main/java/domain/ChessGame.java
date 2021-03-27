package domain;

import domain.exception.ImmovableSamePositionException;
import domain.exception.InvalidMoveException;
import domain.piece.objects.Piece;
import domain.piece.Position;
import domain.score.Score;
import domain.score.ScoreMachine;
import domain.state.Finished;
import domain.state.Running;
import domain.state.State;
import domain.state.Wait;

import java.util.HashMap;
import java.util.Map;

public class ChessGame {
    private State state = new Wait();
    private boolean turn = false;
    private Board board;

    public void start(Map<Position, Piece> pieces) {
        state = new Running();
        board = new Board(pieces);
    }

    public void move(Position start, Position end) {
        if (!movablePiece(start, end, turn)) {
            throw new InvalidMoveException();
        }
        Piece endPiece = board.getPiece(end);
        board.move(start, end);
        checkKingState(endPiece);
        turn = !turn;
    }

    private boolean movablePiece(Position start, Position end, boolean turn) {
        checkSamePosition(start, end);
        return board.canMovable(start, turn);
    }

    private void checkKingState(Piece endPiece) {
        if (endPiece.isKingDead()) {
            state = new Finished();
        }
    }

    private void checkSamePosition(Position start, Position end) {
        if (start.equals(end)) {
            throw new ImmovableSamePositionException();
        }
    }

    public Map<Boolean, Score> piecesScore() {
        Map<Boolean, Score> result = new HashMap<>();
        result.put(true, ScoreMachine.blackPiecesScore(board));
        result.put(false, ScoreMachine.whitePiecesScore(board));
        return result;
    }

    public Board getBoard() {
        return board;
    }

    public void end() {
        state = new Finished();
    }

    public boolean isRunning() {
        return state instanceof Running;
    }

    public boolean isWait() {
        return state instanceof Wait;
    }

    public boolean isEnd() {
        return state instanceof Finished;
    }
}
