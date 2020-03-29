package chess.domain.chessboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.domain.Status;
import chess.domain.Team;
import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.King;
import chess.domain.position.Position;

public class ChessBoard {

	private static final String CANNOT_MOVE_PATH = "이동할 수 없는 경로 입니다.";
	private static final String SAME_TEAM_MESSAGE = "같은 팀입니다.";
	private static final String NOT_CHESS_PIECE_MESSAGE = "체스 말이 아닙니다.";

	private List<Row> rows;

	public ChessBoard(List<Row> rows) {
		this.rows = new ArrayList<>(rows);
	}

	public boolean isDieKing(Team team) {
		return findByTeam(team).stream()
			.noneMatch(chessPiece -> chessPiece.getClass() == King.class);
	}

	private List<ChessPiece> findByTeam(Team team) {
		List<ChessPiece> chessPieces = new ArrayList<>();
		rows.forEach(row -> chessPieces.addAll(row.findByTeam(team)));
		return chessPieces;
	}

	public void move(Position startPosition, Position targetPosition) {
		ChessPiece startPiece = findByPosition(startPosition);
		ChessPiece targetPiece = findByPosition(targetPosition);

		checkTeam(startPiece, targetPiece);
		startPiece.canMove(targetPiece, this::findByPosition);

		replace(startPosition, new Blank(startPosition));
		replace(targetPosition, startPiece);
	}

	public ChessPiece findByPosition(Position position) {
		return rows.stream()
			.filter(row -> row.contains(position))
			.map(row -> row.findByPosition(position))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(CANNOT_MOVE_PATH));
	}

	private void checkTeam(ChessPiece startPiece, ChessPiece targetPiece) {
		validateNotBlank(startPiece);
		validateOtherTeam(startPiece, targetPiece);
	}

	private void validateNotBlank(ChessPiece startPiece) {
		if (startPiece.isBlankPiece()) {
			throw new IllegalArgumentException(NOT_CHESS_PIECE_MESSAGE);
		}
	}

	private void validateOtherTeam(ChessPiece startPiece, ChessPiece targetPiece) {
		if (startPiece.isSameTeam(targetPiece)) {
			throw new IllegalArgumentException(SAME_TEAM_MESSAGE);
		}
	}

	private void replace(Position targetPosition, ChessPiece startPiece) {
		Row row = findRow(targetPosition);
		row.replace(targetPosition, startPiece);
	}

	private Row findRow(Position targetPosition) {
		return rows.stream()
			.filter(row -> row.contains(targetPosition))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(CANNOT_MOVE_PATH));
	}

	public Status createStatus() {
		return new Status(rows);
	}

	public List<Row> getRows() {
		return Collections.unmodifiableList(rows);
	}
}
