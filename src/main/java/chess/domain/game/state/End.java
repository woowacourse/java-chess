package chess.domain.game.state;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.game.Score;
import chess.domain.game.Winner;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class End implements GameState {

    private static final String GAME_IS_END_AND_NOT_MOVABLE_PIECE = "[ERROR] 게임이 끝나서 말을 옮길 수 없습니다.";

    private final Board board;

    public End(Board board) {
        this.board = board;
    }

    @Override
    public GameState movePiece(Position fromPosition, Position toPosition) {
        throw new UnsupportedOperationException(GAME_IS_END_AND_NOT_MOVABLE_PIECE);
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
        return Score.from(board.getValue());
    }

    @Override
    public Color getWinTeamColor() {
        Winner winner = new Winner(board);
        return winner.getColor();
    }

    @Override
    public Board board() {
        return board;
    }
}
