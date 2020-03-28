package testAssistant;

import domain.pieces.Bishop;
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
}
