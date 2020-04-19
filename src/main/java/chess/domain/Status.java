package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import chess.domain.chessboard.Row;
import chess.domain.chesspiece.ChessPiece;

public class Status {
	private static final int MIN_DUPLICATED_COUNT = 2;
	private static final long NOTHING_VALUE = 0L;

	private final List<Row> rows;

	public Status(List<Row> rows) {
		this.rows = new ArrayList<>(rows);
	}

	public Result getResult() {
		return new Result(sumScore(Team.BLACK), sumScore(Team.WHITE));
	}

	private double sumScore(Team team) {
		List<ChessPiece> chessPieces = new ArrayList<>();
		for (Row row : rows) {
			chessPieces.addAll(row.findByTeam(team));
		}

		List<Score> scores = chessPieces.stream()
			.map(Score::of)
			.collect(Collectors.toList());

		return Score.sum(scores) - calculateDuplicatedPawnScore(team);
	}

	private double calculateDuplicatedPawnScore(Team team) {
		long totalCount = 0;
		int boardSize = rows.size();
		for (int column = 0; column < boardSize; column++) {
			long columnPerCount = countPawn(team, column);
			totalCount += countSameColumnPawn(columnPerCount);
		}
		return Score.calculateDuplicatePawnScore(totalCount);
	}

	private long countPawn(Team team, int column) {
		return rows.stream()
			.filter(row -> row.isPawn(column, team))
			.count();
	}

	private long countSameColumnPawn(long columnPerCount) {
		if (columnPerCount > MIN_DUPLICATED_COUNT) {
			return columnPerCount;
		}
		return NOTHING_VALUE;
	}
}