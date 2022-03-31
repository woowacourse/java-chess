package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Color;

public abstract class End implements ChessGame {

    private static final String ALREADY_END_GAME = "[ERROR] 게임이 종료되어 지원하지 않는 기능입니다";

    protected final Board board;

    public End(Board board) {
        this.board = board;
    }

    @Override
    public ChessGame initBoard() {
        throw new IllegalStateException(ALREADY_END_GAME);
    }

    @Override
    public ChessGame movePiece(Position fromPosition, Position toPosition) {
        throw new IllegalStateException(ALREADY_END_GAME);
    }

    @Override
    public ChessGame end() {
        throw new IllegalStateException(ALREADY_END_GAME);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public double calculateScore(Color color) {
        throw new IllegalStateException(ALREADY_END_GAME);
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
