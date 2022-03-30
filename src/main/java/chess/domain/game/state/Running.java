package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Color;

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
