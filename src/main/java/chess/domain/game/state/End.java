package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Color;

public class End implements GameState {

    private final Board board;

    public End(Board board) {
        this.board = board;
    }

    @Override
    public GameState movePiece(Position fromPosition, Position toPosition) {
        throw new IllegalArgumentException("게임이 끝나서 말을 옮길 수 없습니다.");
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public boolean isWaiting() {
        return false;
    }

    @Override
    public double calculateScore(Color color) {
        return board.calculateScore(color);
    }

    @Override
    public Color getWinTeamColor() {
        return board.getWinnerTeamColor();
    }

    @Override
    public Board board() {
        return board;
    }
}
