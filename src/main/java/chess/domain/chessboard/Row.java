package chess.domain.chessboard;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import chess.domain.Team;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Pawn;
import chess.domain.position.Position;

public class Row {
	private static final String NOT_MATCH_POSITION_MESSAGE = "해당 행에 존재하지 않는 좌표 입니다.";
	private static final String NOT_MATCH_CHESS_PIECE_MESSAGE = "목표 위치와 일치하는 ChessPiece가 없습니다.";

	private List<ChessPiece> chessPieces;

	public Row(List<ChessPiece> chessPieces) {
		this.chessPieces = chessPieces;
	}

	public boolean contains(Position position) {
		return chessPieces.stream()
			.anyMatch(chessPiece -> chessPiece.equalsPosition(position));
	}

	public ChessPiece findByPosition(Position position) {
		return chessPieces.stream()
			.filter(chessPiece -> chessPiece.equalsPosition(position))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(NOT_MATCH_POSITION_MESSAGE));
	}

	public void replace(Position targetPosition, ChessPiece startPiece) {
		int targetIndex = IntStream.range(0, chessPieces.size())
			.filter(index -> chessPieces.get(index).equalsPosition(targetPosition))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(NOT_MATCH_CHESS_PIECE_MESSAGE));

		chessPieces.set(targetIndex, startPiece);
		startPiece.changePosition(targetPosition);
	}

	public List<ChessPiece> findByTeam(Team team) {
		List<ChessPiece> pieces = chessPieces.stream()
			.filter(chessPiece -> chessPiece.isMatchTeam(team))
			.collect(Collectors.toList());
		return Collections.unmodifiableList(pieces);
	}

	public boolean isPawn(int index, Team team) {
		ChessPiece chessPiece = chessPieces.get(index);
		return chessPiece.isMatchTeam(team) && chessPiece.getClass() == Pawn.class;
	}

	public ChessPiece get(int x) {
		return chessPieces.get(x);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Row row = (Row)o;
		return chessPieces.equals(row.chessPieces);
	}

	@Override
	public int hashCode() {
		return Objects.hash(chessPieces);
	}
}
