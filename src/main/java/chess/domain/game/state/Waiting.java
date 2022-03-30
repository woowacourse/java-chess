package chess.domain.game.state;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class Waiting implements GameState {

    @Override
    public GameState movePiece(Position fromPosition, Position toPosition) {
        throw new IllegalArgumentException("대기중이라 말을 옮길 수 없습니다");
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
        throw new IllegalArgumentException("대기중이라 점수를 계산할 수 없습니다");
    }

    @Override
    public Color getWinTeamColor() {
        throw new IllegalArgumentException("대기중이라 우승팀을 구할 수 없습니다");
    }

    @Override
    public Board board() {
        throw new IllegalArgumentException("대기중이라 보드를 가져올 수 없습니다");
    }
}
