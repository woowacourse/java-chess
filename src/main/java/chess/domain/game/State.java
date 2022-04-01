package chess.domain.game;

import java.util.Map;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;

public interface State {

	State start();

	State end();

	State move(Coordinate from, Coordinate to);

	Map<Coordinate, Piece> getValue();

	boolean isFinished();
}
