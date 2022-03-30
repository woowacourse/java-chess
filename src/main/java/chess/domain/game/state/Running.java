package chess.domain.game.state;

import java.util.Arrays;
import java.util.Map;

import chess.domain.board.Board;
import chess.domain.game.GameResult;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class Running implements GameState {

    private final Board board;
    private Color color;

    public Running(Board board, Color color) {
        this.board = board;
        this.color = color;
    }

    @Override
    public GameState movePiece(Position fromPosition, Position toPosition) {
        validateTurn(fromPosition);
        board.movePiece(fromPosition, toPosition);
        switchColor();
        if (!board.isAllKingExist()) {
            return new End(board);
        }
        return new Running(board, color);
    }

    private void validateTurn(Position fromPosition) {
        if (board.getPieceColor(fromPosition) != color) {
            throw new IllegalArgumentException("해당 색상의 말을 옮길 차례가 아닙니다.");
        }
    }

    private void switchColor() {
        color = Arrays.stream(Color.values())
            .filter(value -> value != Color.NONE && value != color)
            .findAny()
            .get();
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
