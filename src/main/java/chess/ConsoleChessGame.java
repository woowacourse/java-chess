package chess;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.MoveResult;
import chess.domain.piece.Piece;
import chess.view.OutputView;
import java.util.HashMap;
import java.util.Map;

public final class ConsoleChessGame {
    private final Board board;
    private Color currentTurn;

    public ConsoleChessGame(Board board, Color turn) {
        this.board = board;
        this.currentTurn = turn;
    }

    public ConsoleChessGame() {
        this(BoardFactory.newInstance(), Color.WHITE);
    }

    public Map<Position, Piece> board() {
        return new HashMap<>(board.getBoard());
    }

    public void move(Position from, Position to) {
        if (!board.isTurnOf(from, currentTurn)) {
            OutputView.printError(new IllegalStateException(currentTurn + "차례입니다."));
            return;
        }

        if (board.move(from, to) == MoveResult.SUCCESS) {
            currentTurn = currentTurn.opposite();
        }
    }

    public Map<Color, Double> score() {
        return board.getScore();
    }

    public boolean isFinished() {
        return board.isFinished();
    }
}
