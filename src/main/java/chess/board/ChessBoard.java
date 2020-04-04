package chess.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import chess.gamestate.GameState;
import chess.piece.Piece;
import chess.result.Score;
import chess.team.Team;

public class ChessBoard {
	public static final int ROW_LENGTH = 8;
	public static final int COLUMN_LENGTH = 8;
	private static final int TEAM_LENGTH = 2;

	private final Map<Location, Piece> board;

	public ChessBoard(Map<Location, Piece> pieces) {
		Objects.requireNonNull(pieces, "피스 정보가 없습니다.");
		this.board = pieces;
	}

	public void move(Location now, Location destination) {
		validateLocation(now, destination);
		Piece piece = board.remove(now);
		board.put(destination, piece);
	}

	private void validateLocation(Location now, Location destination) {
		Piece piece = board.get(now);

		checkLocationSameTeam(piece, destination);
		checkObstacleJumper(piece, now, destination);
		piece.checkStrategy(now, destination, existEnemyInLocation(destination, piece));
	}

	private boolean existEnemyInLocation(Location location, Piece piece) {
		boolean destinationEnemy = false;
		if (board.containsKey(location)) {
			destinationEnemy = board.get(location).isNotSameTeam(piece);
		}
		return destinationEnemy;
	}

	private void checkObstacleJumper(Piece piece, Location now, Location destination) {
		boolean obstacle = false;
		Location nextLocation = now.calculateNextLocation(destination, 1);
		for (int weight = 2; weight <= 8 && nextLocation != destination; weight++) {
			if (board.containsKey(nextLocation)) {
				obstacle = true;
				break;
			}
			nextLocation = now.calculateNextLocation(destination, weight);
		}
		if (obstacle && piece.isNotJumper()) {
			throw new IllegalArgumentException("경로 사이에 피스가 있습니다.");
		}
	}

	private void checkLocationSameTeam(Piece piece, Location destination) {
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

		return new Score(new ArrayList<>(teamPieces.values()), getHalfScoreCount(sameTeamPawns));
	}

	private int getHalfScoreCount(List<Location> sameTeamPawns) {
		int halfScorePawnCount = 0;

		for (Location location : sameTeamPawns) {
			int count = 0;
			for (Location targetLocation : sameTeamPawns) {
				if (targetLocation.isSameColumn(location)) {
					count++;
				}
			}
			if (count >= 2) {
				halfScorePawnCount++;
			}
		}

		return halfScorePawnCount;
	}

	public boolean hasTwoKings() {
		long kingCount = board.values().stream()
			.filter(Piece::isKing)
			.count();

		return kingCount == 2;
	}

	public boolean isNotPiece(Location now) {
		return !board.containsKey(now);
	}

	public boolean isNotSameTeam(GameState gameState, Location now) {
		Piece startingPiece = board.get(now);
		return !startingPiece.isSameTeam(gameState.getTeam());
	}

	public Map<Location, Piece> getBoard() {
		return board;
	}

	public Team findWinner() {
		List<Piece> kings = board.values().stream()
			.filter(Piece::isKing)
			.collect(Collectors.toList());

		if (kings.size() == TEAM_LENGTH) {
			throw new IllegalArgumentException("게임이 종료되지 않았습니다.");
		}

		if (kings.get(0).isSameTeam(Team.BLACK)) {
			return Team.BLACK;
		}
		return Team.WHITE;
	}
}
