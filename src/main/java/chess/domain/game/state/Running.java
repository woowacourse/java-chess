package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Color;

public class Running implements ChessGame {

    private static final String NOT_SUPPORTED_FUNCTION = "[ERROR] 게임 도중에 보드를 초기화할 수 없습니다";
    private static final String NOT_ENDED_GAME = "[ERROR] 킹이 잡혀야 게임의 승패가 결정됩니다.";

    private final Board board;

    public Running(Board board) {
        this.board = board;
    }

    @Override
    public ChessGame initBoard() {
        throw new IllegalStateException(NOT_SUPPORTED_FUNCTION);
    }

    @Override
    public ChessGame movePiece(Position fromPosition, Position toPosition) {
        board.movePiece(fromPosition,toPosition);
        if (!board.isAllKingExist()) {
            return new Complete(board);
        }
        return new Running(board);
    }

    @Override
    public ChessGame end() {
        return new Terminate(board);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public double calculateScore(Color color) {
        return board.calculateScore(color);
    }

    @Override
    public Color judgeWinner() {
        throw new IllegalStateException(NOT_ENDED_GAME);
    }
}
