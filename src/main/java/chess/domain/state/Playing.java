package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public abstract class Playing extends GameStarted {

    private static final String IS_NOT_YOUR_TURN_EXCEPTION_MESSAGE = "본인의 기물이 아닙니다.";

    protected Playing(Board board) {
        super(board);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Winner findWinner() {
        throw new IllegalStateException("게임이 아직 진행 중 입니다.");
    }

    @Override
    public GameState move(Position start, Position target) {
        if (!isCorrectTurn(start)) {
            throw new IllegalArgumentException(IS_NOT_YOUR_TURN_EXCEPTION_MESSAGE);
        }
        Piece targetPiece = board.playTurn(start, target);
        return judgeStatus(targetPiece);
    }

    private boolean isCorrectTurn(Position start) {
        return board.isBlack(start) == isBlackTurn();
    }

    private GameStarted judgeStatus(Piece piece) {
        if (piece.isKing()) {
            return judgeWinner();
        }
        return judgeTurn();
    }

    private End judgeWinner() {
        if (isBlackTurn()) {
            return new BlackWin(board);
        }
        return new WhiteWin(board);
    }

    private Playing judgeTurn() {
        if (isBlackTurn()) {
            return new WhiteTurn(board);
        }
        return new BlackTurn(board);
    }

    @Override
    public GameState terminate() {
        return new Terminate(board);
    }
}
