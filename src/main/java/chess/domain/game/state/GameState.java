package chess.domain.game.state;

import java.util.Map;

import chess.domain.game.state.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;

public interface GameState {

    GameState start();

    GameState move(Position source, Position target);

    Map<Color, Double> status();

    GameState end();

    Map<Position, Piece> getBoard();

    boolean isFinished();

    Player getPlayer();
}
