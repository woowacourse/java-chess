package chess.domain.game.state;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class Waiting implements GameState {

    private static final String GAME_IS_WAITING_AND_NOT_MOVABLE_PIECE = "[ERROR] 대기중이라 말을 옮길 수 없습니다";
    private static final String UNSUPPORTED_CALCULATING = "[ERROR] 대기중이라 점수를 계산할 수 없습니다";
    private static final String UNSUPPORTED_GET_WINNER = "[ERROR] 대기중이라 우승팀을 구할 수 없습니다";
    private static final String UNSUPPORTED_GET_BOARD = "[ERROR] 대기중이라 보드를 가져올 수 없습니다";

    @Override
    public GameState movePiece(Position fromPosition, Position toPosition) {
        throw new UnsupportedOperationException(GAME_IS_WAITING_AND_NOT_MOVABLE_PIECE);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public boolean isWaiting() {
        return true;
    }

    @Override
    public Map<Color, Double> calculateScore() {
        throw new UnsupportedOperationException(UNSUPPORTED_CALCULATING);
    }

    @Override
    public Color getWinTeamColor() {
        throw new UnsupportedOperationException(UNSUPPORTED_GET_WINNER);
    }

    @Override
    public Board board() {
        throw new UnsupportedOperationException(UNSUPPORTED_GET_BOARD);
    }
}
