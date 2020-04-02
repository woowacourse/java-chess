package chess.domain.piece;

import java.util.List;

import chess.domain.coordinates.Coordinates;

public interface Piece {
	List<Coordinates> findMovableCoordinates(Coordinates from, Coordinates to);

	boolean isKing();

	boolean isPawn();

	boolean isAlly(Piece that);

	boolean isTeamOf(Color color);

	String getName();

	Color getColor();

	double getScore();
}
