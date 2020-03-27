package chess.domain;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Pawn;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Row {
	private static final String NOT_MATCH_POSITION_MESSAGE = "해당 행에 존재하지 않는 좌표 입니다.";
	private List<ChessPiece> chessPieces;

	public Row(List<ChessPiece> chessPieces) {
		this.chessPieces = chessPieces;
	}

	public ChessPiece get(int x) {
		return chessPieces.get(x);
	}

	public List<ChessPiece> getChessPieces() {
		return chessPieces;
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

	public void replace(Position targetPosition, ChessPiece chessPiece) {
		int bound = chessPieces.size();
		for (int index = 0; index < bound; index++) {
			if (chessPieces.get(index).equalsPosition(targetPosition)) {
				chessPieces.set(index, chessPiece);
			}
		}
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
}
