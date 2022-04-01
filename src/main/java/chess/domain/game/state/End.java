package chess.domain.game.state;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.game.ScoreCalculator;
import chess.domain.game.WinnerCalculator;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class End implements GameState {

    private static final String GAME_IS_END_AND_NOT_MOVABLE_PIECE = "[ERROR] 게임이 끝나서 말을 옮길 수 없습니다.";

    private final Board board;

    public End(final Board board) {
        this.board = board;
    }

    @Override
    public GameState movePiece(Position source, Position target) {
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
        final ScoreCalculator scoreCalculator = new ScoreCalculator(board.getValue());
        return scoreCalculator.calculateAllTeamScore();
    }

    @Override
    public Color getWinTeamColor() {
        final WinnerCalculator winnerCalculator = new WinnerCalculator(board.getValue());
        return winnerCalculator.judgeWinner();
    }

    @Override
    public Board board() {
        return board;
    }
}
