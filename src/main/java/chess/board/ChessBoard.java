package chess.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import chess.gamestate.GameState;
import chess.piece.King;
import chess.piece.Piece;
import chess.result.Score;
import chess.team.Team;

public class ChessBoard {
	public static final int ROW_LENGTH = 8;
	public static final int COLUMN_LENGTH = 8;
	private static final long COLUMN_PAWN_COUNT = 2;

	private final Map<Location, Piece> board;

	public ChessBoard(Map<Location, Piece> pieces) {
		Objects.requireNonNull(pieces, "피스 정보가 없습니다.");
		this.board = pieces;
	}

	// todo : canMvoe, hasObstacle에서 에러나게 변경
	public void move(Location now, Location destination) {
		validateLocation(now, destination);
		Piece piece = board.remove(now);
		board.put(destination, piece);
	}

	private void validateLocation(Location now, Location destination) {
		if (!board.containsKey(now)) {
			throw new IllegalArgumentException("빈칸을 이동했습니다.");
		}
		Piece piece = board.get(now);
		checkSameTeam(piece, destination);
		piece.checkRange(now, destination);
		piece.checkObstacle(board, now, destination);
	}

	private void checkSameTeam(Piece piece, Location destination) {
		boolean sameTeam = false;
		if (board.containsKey(destination)) {
			Piece other = board.get(destination);
			sameTeam = piece.isSameTeam(other);
		}
		if (sameTeam) {
			throw new IllegalArgumentException("같은 팀으로 이동했습니다.");
		}
	}

	public Map<Location, Piece> giveMyPiece(Team team) {
		return board.keySet().stream()
			.filter(location -> board.get(location).isSameTeam(team))
			.collect(Collectors.toMap(location -> location, board::get));
	}

	public Score calculateScore(Team team) {
		Map<Location, Piece> teamPieces = giveMyPiece(team);

		List<Location> sameTeamPawns = teamPieces.keySet().stream()
			.filter(location -> board.get(location).isPawn())
			.collect(Collectors.toList());

		return new Score(new ArrayList<>(teamPieces.values()), getHalfScorePawnCount(sameTeamPawns));
	}

	private int getHalfScorePawnCount(List<Location> sameTeamPawns) {
		int halfScorePawnCount = 0;

		for (Location location : sameTeamPawns) {
			int count = 0;
			for (Location targetLocation : sameTeamPawns) {
				if (targetLocation.isSameCol(location)) {
					count++;
				}
			}
			if (count > 2) {
				halfScorePawnCount++;
			}
		}

		return halfScorePawnCount;
	}

	public boolean hasTwoKings() {
		long kingCount = board.values().stream()
			.filter(piece -> piece instanceof King)
			.count();
		return kingCount == 2;
	}

	public boolean isTurn(Location now, GameState gameState) {
		Piece startingPiece = board.get(now);
		return gameState.isTurn(startingPiece);
	}

	public Map<Location, Piece> getBoard() {
		return board;
	}
}
