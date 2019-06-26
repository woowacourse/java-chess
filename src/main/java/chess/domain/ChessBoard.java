package chess.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import chess.exception.NotFoundPieceException;
import chess.exception.SamePositionException;
import chess.domain.piece.Piece;

public class ChessBoard {
	private final List<Piece> pieces;

	public ChessBoard() {
		pieces = new ArrayList<>();
	}

	public void addPiece(Piece newPiece) {
		if (hasSamePosition(newPiece)) {
			throw new SamePositionException();
		}
		pieces.add(newPiece);
	}

	private boolean hasSamePosition(Piece newPiece) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(newPiece));
	}

	public Optional<Piece> findPiece(Position position) {
		for (Piece piece : pieces) {
			if (piece.isSamePosition(position)) {
				return Optional.ofNullable(piece);
			}
		}
		throw new NotFoundPieceException();
	}

	public boolean isMovable(Path path) {
		return path.getPath().stream()
				.noneMatch(this::isExist);
	}

	private boolean isExist(Position position) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(position));
	}

	public void remove(Piece piece) {
		pieces.remove(piece);
	}

	public Score getXScore(Player player) {
		Score totalScore = new Score(0);
		List<Score> scores = pieces.stream()
				.filter(piece -> piece.isMine(player) && !piece.isPawn())
				.map(Piece::getScore)
				.collect(Collectors.toList());

		for (Score score : scores) {
			totalScore = totalScore.add(score);
		}

		totalScore = totalScore.add(getPawnScore(player));
		return totalScore;
	}

	private Score getPawnScore(Player player) {
		Score score = new Score(0);
		List<Piece> pawns = getPawn(player);

		for (int i = 1; i <= 8; i++) {
			score = score.add(getXScore(pawns, i));
		}
		return score;
	}

	private List<Piece> getPawn(Player player) {
		return pieces.stream()
				.filter(piece -> piece.isMine(player) && piece.isPawn())
				.collect(Collectors.toList());
	}

	private Score getXScore(List<Piece> pieces, int x) {
		long count = pieces.stream()
				.filter(piece -> piece.isSameCoordinateX(x))
				.count();

		if(count == 1) {
			return new Score(1);
		}
		return new Score(count * 0.5);
	}

	public List<Piece> getPieces() {
		return Collections.unmodifiableList(pieces);
	}
}
