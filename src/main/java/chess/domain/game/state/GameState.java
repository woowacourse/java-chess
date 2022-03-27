package chess.domain.game.state;

import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;

public interface GameState {

    GameState start();

    GameState move(Position source, Position target);

    GameState status();

    GameState end();

    Map<Position, Piece> getBoard();

    boolean isFinished();
}
