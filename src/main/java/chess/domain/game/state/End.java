package chess.domain.game.state;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.game.GameResult;
import chess.domain.piece.Color;
import chess.domain.position.Position;

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
    public Map<Color, Double> calculateScore() {
        return GameResult.calculateTotalScore(board.getBoard());
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
