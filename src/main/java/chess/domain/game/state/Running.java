package chess.domain.game.state;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.game.Score;
import chess.domain.game.Winner;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class Running implements GameState {

    private static final String NOT_CORRECT_TURN_MOVE_PIECE = "[ERROR] 해당 색상의 말을 옮길 차례가 아닙니다.";

    private final Board board;
    private Color color;

    public Running(final Board board, final Color color) {
        this.board = board;
        this.color = color;
    }

    @Override
    public GameState movePiece(final Position source, final Position target) {
        validateTurn(source);
        board.movePiece(source, target);
        switchColor();
        if (board().isKingDead()) {
            return new End(board);
        }
        return this;
    }

    private void validateTurn(final Position source) {
        if (board.getPieceColor(source) != color) {
            throw new IllegalStateException(NOT_CORRECT_TURN_MOVE_PIECE);
        }
    }

    private void switchColor() {
        if (color == Color.WHITE) {
            color = Color.BLACK;
            return;
        }
        color = Color.WHITE;
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
        final Score score = new Score(board.getValue());
        return score.getAllTeamScore();
    }

    @Override
    public Color getWinTeamColor() {
        final Winner winner = new Winner(board.getValue());
        return winner.getColor();
    }

    @Override
    public Board board() {
        return board;
    }
}
