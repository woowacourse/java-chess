package chess.domain;

import static chess.domain.color.Color.*;
import static chess.domain.piece.PieceType.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.domain.color.Color;
import chess.domain.move.Direction;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.initial.BoardFactory;

public class Board {

	private static final String ROOK_NAME = "R";
	private static final String KNIGHT_NAME = "N";
	private static final String BISHOP_NAME = "B";
	private static final String QUEEN_NAME = "Q";
	private static final String PAWN_NAME = "P";
	private static Color thisTurn;
	private final Map<Position, Piece> board;

	private Board(final Map<Position, Piece> board) {
		this.board = board;
		thisTurn = WHITE;
	}

	public static Board create() {
		return new Board(BoardFactory.create());
	}

	public void move(final Position source, final Position target) {
		validateSourceNotEmpty(source);
		isTurn(source);
		validateDifferentPosition(source, target);
		validateTargetNotSameColor(source, target);

		Piece piece = board.get(source);
		Direction unit = Direction.calculateDirection(piece, target);
		validateMovable(piece, unit);
		validatePath(source, target, unit, piece);

		movePiece(source, target, piece);
	}

	private void validateSourceNotEmpty(final Position source) {
		if (isEmptyPosition(source)) {
			throw new IllegalArgumentException("출발점에 체스말이 존재하지 않습니다");
		}
	}

	private boolean isEmptyPosition(final Position source) {
		return board.get(source).isEmpty();
	}

	private void isTurn(final Position source) {
		if (board.get(source).color() != thisTurn) {
			throw new IllegalArgumentException("상대팀의 순서입니다");
		}
	}

	private void validateDifferentPosition(final Position source, final Position target) {
		if (source.equals(target)) {
			throw new IllegalArgumentException("출발지와 도착지는 같을 수 없습니다");
		}
	}

	private void validateTargetNotSameColor(final Position source, final Position target) {
		final Piece sourcePiece = board.get(source);
		final Piece targetPiece = board.get(target);

		if (sourcePiece.isSameTeam(targetPiece.color())) {
			throw new IllegalArgumentException("같은 팀은 공격할 수 없습니다");
		}
	}

	private void validateMovable(final Piece piece, final Direction unit) {
		if (!piece.movable(unit)) {
			throw new IllegalArgumentException("체스말이 이동할 수 없는 위치입니다");
		}
	}

	private void validatePath(final Position source, final Position target, final Direction unit, final Piece piece) {
		List<Position> path = calculatePath(source, target, unit);
		validatePathIsEmpty(path);
		validateMovableByCount(piece, path.size());
	}

	private List<Position> calculatePath(Position source, Position target, Direction unit) {
		List<Position> path = new ArrayList<>();
		Position pathPosition = source;
		while (!pathPosition.isSame(target)) {
			pathPosition = pathPosition.nextPosition(unit);
			path.add(pathPosition);
		}

		return path.subList(0, path.size() - 1);
	}

	private void validatePathIsEmpty(final List<Position> path) {
		for (final Position position : path) {
			validatePositionIsEmpty(position);
		}
	}

	private void validatePositionIsEmpty(final Position position) {
		if (!isEmptyPosition(position)) {
			throw new IllegalArgumentException("이동할 경로에 체스말이 존재합니다.");
		}
	}

	private void validateMovableByCount(final Piece piece, final int pathSize) {
		if (!piece.movableByCount(pathSize)) {
			throw new IllegalArgumentException("한 칸만 움직일 수 있는 체스말입니다");
		}
	}

	private void movePiece(final Position source, final Position target, final Piece piece) {
		piece.move(target);
		board.put(target, piece);
		board.put(source, new Empty(NONE, source));
		thisTurn = thisTurn.switchTurn();
	}

	public Map<Position, Piece> board() {
		return board;
	}

	public double blackStatus() {
		double score = 0.0;
		for (File file : files()) {
			score += blackScores(file);
		}
		return score;
	}

	public double whiteStatus() {
		double score = 0.0;
		for (File file : files()) {
			score += whiteScores(file);
		}
		return score;
	}

	private double blackScores(File file) {
		double newScore = 0;
		int count = 0;
		for (Rank rank : ranks()) {
			Piece piece = board.get(Position.of(file, rank));
			newScore += blackPieceScore(piece.name());
			count += pawnCount(piece.name(), BLACK);
		}
		return newScore + addPawnScore(count);
	}

	private double blackPieceScore(String pieceName) {
		if (pieceName.equals(ROOK_NAME)) {
			return ROOK.score();
		}
		if (pieceName.equals(KNIGHT_NAME)) {
			return KNIGHT.score();
		}
		if (pieceName.equals(BISHOP_NAME)) {
			return BISHOP.score();
		}
		if (pieceName.equals(QUEEN_NAME)) {
			return QUEEN.score();
		}
		return 0;
	}

	private int pawnCount(String name, Color color) {
		if (color == BLACK && name.equals(PAWN_NAME)) {
			return 1;
		}
		if (color == WHITE && name.equals(PAWN_NAME.toLowerCase())) {
			return 1;
		}
		return 0;
	}

	private double addPawnScore(int count) {
		if (count == 0) {
			return 0;
		}
		if (count == 1) {
			return PAWN.score();
		}
		return PAWNS.score() * count;
	}

	private double whiteScores(File file) {
		double newScore = 0;
		int count = 0;
		for (Rank rank : ranks()) {
			Piece piece = board.get(Position.of(file, rank));
			newScore += whitePieceScore(piece.name());
			count += pawnCount(piece.name(), WHITE);
		}
		return newScore + addPawnScore(count);
	}

	private double whitePieceScore(String pieceName) {
		if (pieceName.equals(ROOK_NAME.toLowerCase())) {
			return ROOK.score();
		}
		if (pieceName.equals(KNIGHT_NAME.toLowerCase())) {
			return KNIGHT.score();
		}
		if (pieceName.equals(BISHOP_NAME.toLowerCase())) {
			return BISHOP.score();
		}
		if (pieceName.equals(QUEEN_NAME.toLowerCase())) {
			return QUEEN.score();
		}
		return 0;
	}
}
