package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;

import java.util.Map;

public final class Running implements GameStatus {

    private final Board board;
    private final Color turn;

    public Running(final Board board, final Color turn) {
        this.board = board;
        this.turn = turn;
    }

    @Override
    public GameStatus start() {
        throw new UnsupportedOperationException("게임을 시작한 후에 다시 시작할 수 없습니다.");
    }

    @Override
    public GameStatus load(final GameStatus gameStatus) {
        throw new UnsupportedOperationException("시작한 후에는 게임을 불러올 수 없습니다.");
    }

    @Override
    public GameStatus playTurn(final Position source, final Position target) {
        board.confirmMove(source, target, turn);
        return new Running(board, changeTurn());
    }

    @Override
    public GameStatus end() {
        return new Finished();
    }

    @Override
    public boolean isOnGoing() {
        return true;
    }

    private Color changeTurn() {
        if (turn.isBlack()) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    @Override
    public double computeScore(final Color color) {
        return board.computeScore(color);
    }

    @Override
    public Color getTurn() {
        return turn;
    }

    @Override
    public Color computeWinner() {
        return board.computeWinner();
    }
}
