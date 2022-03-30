package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Color;

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
    public double calculateScore(Color color) {
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
