package chess.domain.game.state;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.game.GameResult;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class Running implements GameState {

    private final Board board;

    public Running(Board board) {
        this.board = board;
    }

    @Override
    public GameState movePiece(Position fromPosition, Position toPosition) {
        board.movePiece(fromPosition, toPosition);
        if (!board.isAllKingExist()) {
            return new End(board);
        }
        return new Running(board);
    }

    @Override
    public boolean isFinish() {
        return false;
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
