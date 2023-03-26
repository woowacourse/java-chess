package state;

import domain.board.Board;
import domain.piece.Piece;
import domain.position.Position;

import java.util.Map;

public interface State {
    boolean isPlaying();

    void move(Position source, Position destination);

    Map<Position, Piece> getPieces();

    Board getBoard();

    State next();
}
