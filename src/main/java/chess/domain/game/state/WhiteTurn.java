package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;

public class WhiteTurn implements State {

    private final Board board;

    public WhiteTurn(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new IllegalStateException("이미 게임을 시작한 상태입니다.");
    }

    @Override
    public State end() {
        return new End(board);
    }

    @Override
    public State move(Coordinate from, Coordinate to) {
        Piece piece = board.findPiece(from);

        if (piece.isBlack()) {
            throw new IllegalStateException("흰팀 차례에서는 흰색 말만 이동할 수 있습니다.");
        }

        Board newBoard = board.move(from, to);

        if (!newBoard.isBothKingAlive()) {
            return new End(board);
        }
        return new BlackTurn(newBoard);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
