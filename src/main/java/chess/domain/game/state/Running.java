package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.Position;

public class Running implements GameState{

    private static final String NOT_SUPPORTED_FUNCTION = "[ERROR] 게임 도중에 보드를 초기화할 수 없습니다";

    private final Board board;

    public Running(Board board) {
        this.board = board;
    }

    @Override
    public GameState initBoard() {
        throw new IllegalStateException(NOT_SUPPORTED_FUNCTION);
    }

    @Override
    public GameState movePiece(Position fromPosition, Position toPosition) {
        board.movePiece(fromPosition,toPosition);
        if (!board.isAllKingExist()) {
            return new End();
        }
        return new Running(board);
    }

    @Override
    public GameState end() {
        return new End();
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
