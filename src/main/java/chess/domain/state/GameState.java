package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import java.util.Map;

public interface GameState {

    boolean isEnd();

    GameState terminate();

    GameState move(Position start, Position target);

    String findTurn();

    Winner findWinner();

    Board getBoard();

    String getState();

    String getStateName();

    Map<String, Object> toMap();
}
