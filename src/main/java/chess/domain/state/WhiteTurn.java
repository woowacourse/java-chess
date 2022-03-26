package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class WhiteTurn extends State {

    WhiteTurn(final Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new IllegalStateException("게임이 이미 시작되었습니다.");
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public State move(final Position from, final Position to) {
        checkValidPosition(from, to);
        board.move(from, to);
        return new BlackTurn(board);
    }

    private void checkValidPosition(Position from, Position to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("출발 지점과 도착 지점 위치가 동일합니다.");
        }
        if (board.isMatchingColor(from, Color.BLACK)) {
            throw new IllegalArgumentException("흰색 말을 선택하세요.");
        }
        if (board.isMatchingColor(to, Color.WHITE)) {
            throw new IllegalArgumentException("도착 지점에 나의 말이 존재합니다.");
        }
    }
}
