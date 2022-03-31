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

    Map<Color, Double> getScore();

    Result getResult();

    boolean isFinished();

    static State start(Board board) {
        return new WhiteTurn(board);
    }
}
