package chess.domain.result;

import static chess.domain.piece.Team.*;
import static chess.domain.position.Position.*;
import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class Result {
	private static final int BONUSE_PAWN_COUNT_IN_ONE_COLUMN = 1;
	private static final double PAWN_BONUS_SCORE = 0.5;
	private final Map<Team, Double> status;

	private Result(Map<Team, Double> status) {
		this.status = Collections.unmodifiableMap(new HashMap<>(Objects.requireNonNull(status)));
	}

	public static Result from(Board board) {
		Map<Team, Double> collect = findDafaultScores(board);
		for (int col = MINIMUM_POSITION_NUMBER; col <= MAXIMUM_POSITION_NUMBER; col++) {
			inputNthColumnPawnBonus(board, collect, col);
		}
		return new Result(collect);
	}

	private static void inputNthColumnPawnBonus(Board board, Map<Team, Double> collect, int file) {
		Map<Team, Long> cnt = findNthColumnPawnSize(board, file);
		addPawnBonusScore(collect, cnt);
	}

	private static HashMap<Team, Double> findDafaultScores(Board board) {
		HashMap<Team, Double> collect = board.getPieces().values().stream()
			.filter(Piece::isNotBlank)
			.collect(groupingBy(Piece::getTeam, HashMap::new, summingDouble(Piece::getScore)));

		collect.putIfAbsent(BLACK, 0d);
		collect.putIfAbsent(WHITE, 0d);
		return collect;
	}

	private static void addPawnBonusScore(Map<Team, Double> collect, Map<Team, Long> cnt) {
		cnt.keySet().stream()
			.filter(team -> cnt.get(team) == BONUSE_PAWN_COUNT_IN_ONE_COLUMN)
			.forEach(team -> collect.put(team, collect.get(team) + PAWN_BONUS_SCORE));
	}

	private static Map<Team, Long> findNthColumnPawnSize(Board board, int col) {
		return IntStream.rangeClosed(MINIMUM_POSITION_NUMBER, MAXIMUM_POSITION_NUMBER)
			.mapToObj(row -> board.findPiece(Position.of(col, row)))
			.filter(Piece::isPawn)
			.collect(groupingBy(Piece::getTeam, counting()));
	}

	public Team findWinner() {
		return status.get(BLACK) < status.get(WHITE) ? WHITE :
			!status.get(BLACK).equals(status.get(WHITE)) ? BLACK : NONE;
	}

	public Map<Team, Double> getStatus() {
		return status;
	}
}
