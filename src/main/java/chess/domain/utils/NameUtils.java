package chess.domain.utils;

import chess.domain.Team;

public class NameUtils {

	public static String parseName(String name, Team team) {
		if (team == Team.WHITE) {
			return name;
		}
		return name.toUpperCase();
	}
}
