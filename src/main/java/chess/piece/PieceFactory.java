package chess.piece;

import java.util.HashMap;
import java.util.Map;

import chess.board.Location;
import chess.team.Team;

public class PieceFactory {
	public Map<Location, Piece> createPieces() {
		Map<Location, Piece> pieces = new HashMap<>();

		putNoble(pieces, 1, Team.WHITE);
		putPawns(pieces, 2, Team.WHITE);

		putPawns(pieces, 7, Team.BLACK);
		putNoble(pieces, 8, Team.BLACK);

		return pieces;
	}

	private void putPawns(Map<Location, Piece> pieces, int row, Team team) {
		for (int i = 0; i < 8; i++) {
			pieces.put(Location.of(row, (char)(i + 'a')), Pawn.of(team));
		}
	}

	private void putNoble(Map<Location, Piece> pieces, int row, Team team) {
		pieces.put(Location.of(row, 'a'), Rook.of(team));
		pieces.put(Location.of(row, 'b'), Knight.of(team));
		pieces.put(Location.of(row, 'c'), Bishop.of(team));
		pieces.put(Location.of(row, 'd'), Queen.of(team));
		pieces.put(Location.of(row, 'e'), King.of(team));
		pieces.put(Location.of(row, 'f'), Bishop.of(team));
		pieces.put(Location.of(row, 'g'), Knight.of(team));
		pieces.put(Location.of(row, 'h'), Rook.of(team));
	}
}
