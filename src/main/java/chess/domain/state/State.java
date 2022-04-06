package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.position.Result;
import chess.domain.board.Color;
import chess.domain.board.Piece;
import java.util.Map;

public interface State {

    State movePiece(Position src, Position dest);

    Map<Position, Piece> getBoard();

    Map<Color, Double> getScores();

    Result getResult();

    boolean isFinished();

    static State start(Board board) {
        return new WhiteTurn(board);
    }

    static State start(Board board, Turn turn) {
        if (turn == Turn.WHITE_TURN) {
            return new WhiteTurn(board);
        }
        if (turn == Turn.BLACK_TURN) {
            return new BlackTurn(board);
        }
        return stop();
    }

    static State stop() {
        return new Stopped(Board.getInitializedInstance());
    }

    Turn currentTurn();
}
