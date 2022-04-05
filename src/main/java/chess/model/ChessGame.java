package chess.model;

import java.util.Map;

import chess.model.boardinitializer.PieceArrangement;
import chess.model.piece.Piece;

public class ChessGame {

    static final String ERROR_NOT_MOVABLE_CHESS_FINISHED = "[ERROR] 체스 게임이 종료되어 이동할 수 없습니다.";
    static final String ERROR_NOT_CORRECT_TURN = "[ERROR] 현재 올바르지 않은 팀 선택입니다.";

    private final Board board;
    private final Turn turn;

    public ChessGame(Turn turn, PieceArrangement initializer) {
        this.board = new Board(initializer);
        this.turn = turn;
    }

    public void move(Position source, Position target) {
        validateMovable(source);
        finishIfKingCaptured(target);

        board.move(source, target);
        turn.nextState();
    }

    private void validateMovable(Position source) {
        validateCorrectTurn(source);
        validateNotFinished();
    }

    private void validateCorrectTurn(Position source) {
        if (!turn.isTurnOf(board.pieceAt(source))) {
            throw new IllegalArgumentException(ERROR_NOT_CORRECT_TURN);
        }
    }

    private void validateNotFinished() {
        if (isFinished()) {
            throw new IllegalStateException(ERROR_NOT_MOVABLE_CHESS_FINISHED);
        }
    }

    public boolean isFinished() {
        return turn.isFinished();
    }

    private void finishIfKingCaptured(Position target) {
        if (board.isTargetKing(target)) {
            turn.finish();
        }
    }

    public double getScore() {
        return new Score(board.getValues(), turn).calculate();
    }

    public Map<Position, Piece> getBoardValue() {
        return board.getValues();
    }
}
