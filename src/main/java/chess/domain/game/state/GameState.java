package chess.domain.game.state;

import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;

public interface GameState {

    GameState start();

    GameState move(Position source, Position target);

    Map<Color, Double> status();

    GameState end();

    boolean isFinished();

    Map<Position, Piece> getBoard();

    String getTurn();
}
