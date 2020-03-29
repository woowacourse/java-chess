package domain.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Board {
	private static final int ROW_INDEX = 1;
	private static final int COLUMN_INDEX = 0;
	private static final double PAWN_SCORE_WHEN_HAS_SAME_COLUMN = -0.5;

	private List<Rank> ranks;

	public Board(List<Rank> ranks) {
		this.ranks = ranks;
	}

	public List<Rank> getRanks() {
		return ranks;
	}

	public void move(String sourcePosition, String inputTargetPosition, Team turn) {
		int rankLine = Integer.parseInt(String.valueOf(sourcePosition.charAt(ROW_INDEX)));
		Rank rank = ranks.get(rankLine - 1);
		Piece piece = findPiece(sourcePosition, rank);
		Position targetPosition = Position.of(inputTargetPosition);
		if (piece.canMove(targetPosition, turn, ranks)) {
			piece.move(targetPosition, ranks);
		}
	}

	public Piece findPiece(String sourcePosition, Rank rank) {
		return rank.getPieces().stream()
			.filter(piece -> piece.getPosition().isSamePosition(Position.of(sourcePosition)))
			.findFirst()
			.orElseThrow(() -> new InvalidPositionException(InvalidPositionException.INVALID_SOURCE_POSITION));
	}

	public Map<Team, Double> getScore() {
		Map<Team, Double> scoreBoard = new HashMap<>();
		scoreBoard.put(Team.WHITE, calculateScoreByTeam(Team.WHITE));
		scoreBoard.put(Team.BLACK, calculateScoreByTeam(Team.BLACK));
		return scoreBoard;
	}

	private double calculateScoreByTeam(Team team) {
		List<Piece> pawn = ranks.stream()
			.flatMap(rank -> rank.getPieces().stream())
			.filter(piece -> piece instanceof Pawn)
			.filter(piece -> piece.isTeam(team))
			.collect(Collectors.toList());

		double sum = ranks.stream()
			.flatMap(rank -> rank.getPieces().stream())
			.filter(piece -> piece.isTeam(team))
			.mapToDouble(Piece::getScore)
			.sum();

		if (isSameColumn(pawn)) {
			sum += pawn.size() * PAWN_SCORE_WHEN_HAS_SAME_COLUMN;
		}
		return sum;
	}

	private boolean isSameColumn(List<Piece> pawns) {
		int distinctCount = (int)pawns.stream()
			.mapToInt(pawn -> pawn.getPosition().getColumn().getNumber())
			.distinct()
			.count();
		return pawns.size() == distinctCount;
	}
}
