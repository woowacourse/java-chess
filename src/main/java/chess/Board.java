package chess;

import static chess.piece.Team.*;
import static chess.position.File.*;
import static chess.position.Rank.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;
import chess.position.File;
import chess.position.Position;

public class Board {
	private final Map<Position, Piece> pieces;
	private Team turn;
	private boolean finished;

	public Board(Map<Position, Piece> pieces) {
		this.pieces = pieces;
		this.turn = Team.WHITE;
		this.finished = false;
	}

	public static Board createInitialBoard() {
		return new Board(getInitialPieces());
	}

	private static Map<Position, Piece> getInitialPieces() {
		Map<Position, Piece> pieces = new HashMap<>();
		pieces.put(Position.of(A, EIGHT), new Rook(BLACK));
		pieces.put(Position.of(B, EIGHT), new Knight(BLACK));
		pieces.put(Position.of(C, EIGHT), new Bishop(BLACK));
		pieces.put(Position.of(D, EIGHT), new Queen(BLACK));
		pieces.put(Position.of(E, EIGHT), new King(BLACK));
		pieces.put(Position.of(F, EIGHT), new Bishop(BLACK));
		pieces.put(Position.of(G, EIGHT), new Knight(BLACK));
		pieces.put(Position.of(H, EIGHT), new Rook(BLACK));
		for (File value : File.values()) {
			pieces.put(Position.of(value, SEVEN), new Pawn(BLACK));
		}

		pieces.put(Position.of(A, ONE), new Rook(WHITE));
		pieces.put(Position.of(B, ONE), new Knight(WHITE));
		pieces.put(Position.of(C, ONE), new Bishop(WHITE));
		pieces.put(Position.of(D, ONE), new Queen(WHITE));
		pieces.put(Position.of(E, ONE), new King(WHITE));
		pieces.put(Position.of(F, ONE), new Bishop(WHITE));
		pieces.put(Position.of(G, ONE), new Knight(WHITE));
		pieces.put(Position.of(H, ONE), new Rook(WHITE));
		for (File value : File.values()) {
			pieces.put(Position.of(value, TWO), new Pawn(WHITE));
		}
		return pieces;
	}

	public void move(Position from, Position to) {
		Piece piece = pieces.get(from);
		List<Position> PositionsWherePiecesShouldNeverBeIncluded = piece.findReachablePositions(from, to);
		if (isExistAnyPieceAt(PositionsWherePiecesShouldNeverBeIncluded)) {
			throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
		}
		if (isExistAt(to) && pieces.get(to).isSameTeam(pieces.get(from))) {
			throw new IllegalArgumentException("본인의 말은 잡을 수 없습니다.");
		}

		Piece target = pieces.remove(from);
		target.updateHasMoved();
		Piece piece1 = pieces.get(to);
		if (piece1 != null && piece instanceof King) {
			this.finished = true;
		}
		pieces.put(to, target);
		this.turn = turn.getOppositeTeam();
	}

	public boolean isExistAnyPieceAt(List<Position> traces) {
		return traces.stream()
			.anyMatch(position -> pieces.containsKey(position));
	}

	private boolean isExistAt(Position position) {
		return pieces.containsKey(position);
	}

	public Piece getPiece(Position position) {
		return pieces.get(position);
	}

	public Map<Position, Piece> getPieces() {
		return Collections.unmodifiableMap(this.pieces);
	}

	public boolean checkGameEnd() {
		return false;
	}
}
