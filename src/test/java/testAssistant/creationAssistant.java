package testAssistant;

import domain.pieces.*;
import domain.point.Direction;
import domain.point.Distance;
import domain.point.Point;
import domain.team.Team;

public class creationAssistant {
	public static Team createTeam(String string) {
		return Team.valueOf(string.toUpperCase());
	}

	public static Point createPoint(String point) {
		return Point.of(point);
	}

	public static Direction createDirection(String direction) {
		return Direction.valueOf(direction.toUpperCase());
	}

	public static Distance createDistance(String distance) {
		return Distance.valueOf(distance.toUpperCase());
	}

	public static Bishop createBishop(String team, String point) {
		return new Bishop(createTeam(team), createPoint(point));
	}

	public static King createKing(String team, String point) {
		return new King(createTeam(team), createPoint(point));
	}

	public static Knight createKnight(String team, String point) {
		return new Knight(createTeam(team), createPoint(point));
	}

	public static Pawn createPawn(String team, String point) {
		return new Pawn(createTeam(team), createPoint(point));
	}

	public static Pawn createPawnOnceMoved(String team, String point) {
		return new Pawn(createTeam(team), createPoint(point), false);
	}

	public static Queen createQueen(String team, String point) {
		return new Queen(createTeam(team), createPoint(point));
	}

	public static Rook createRook(String team, String point) {
		return new Rook(createTeam(team), createPoint(point));
	}
}
