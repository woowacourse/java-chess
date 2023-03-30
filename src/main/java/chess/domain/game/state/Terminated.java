package chess.domain.game.state;

import chess.domain.game.Board;
import chess.domain.game.Position;
import chess.domain.game.ScoreCalculator;
import chess.domain.piece.Team;

public class Terminated implements GameState {

    private static Terminated terminated;

    private Terminated() {
    }

    public static Terminated getState() {
        if (terminated == null) {
            terminated = new Terminated();
        }
        return terminated;
    }

    @Override
    public GameState start() {
        throw new IllegalArgumentException("[ERROR] 종료된 게임이므로 시작할 수 없습니다.");
    }

    @Override
    public void progress(Position source, Position target, Board board) {
        throw new IllegalArgumentException("[ERROR] 종료된 게임이므로 움직일 수 없습니다.");
    }

    @Override
    public boolean isNotTerminated() {
        return false;
    }

    @Override
    public double calculateBlackScore(Board board) {
        ScoreCalculator scoreCalculator = new ScoreCalculator(board.getBoard(), Team.BLACK);
        return scoreCalculator.calculateScore();
    }

    @Override
    public double calculateWhiteScore(Board board) {
        ScoreCalculator scoreCalculator = new ScoreCalculator(board.getBoard(), Team.WHITE);
        return scoreCalculator.calculateScore();
    }
}
