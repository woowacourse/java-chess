package chess.domain;

import java.util.List;

import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Team;
import chess.exception.IllegalMoveException;
import chess.exception.NullPieceException;

public class Board {
	private final Pieces pieces;

	public Board() {
		this.pieces = PieceFactory.getPieces();
	}

	public Pieces getPieces() {
		return pieces;
	}

	public void movePiece(Position source, Position destination) {
		validateDestination(source, destination);
		Piece piece = pieces.findByPosition(source);
		if (piece == null) {
			throw new NullPieceException("해당 위치에 말이 없습니다.");
		}
		Piece destinationPiece = pieces.findByPosition(destination);
		if (piece instanceof Pawn) {
			validatePawnDestination(source, destination);
		}
		if (destinationPiece != null) {
			killPiece(piece, destinationPiece);
		}
		if (!(piece instanceof Knight)) {
			validateNoObstacle(source, destination);
		}
		piece.move(destination);
	}

	private void validatePawnDestination(Position source, Position destination) {
		Direction direction = source.calculateDirection(destination);
		if (direction.isGoingForward() && pieces.findByPosition(destination) != null) {
			throw new IllegalMoveException("폰이 이동할 수 없는 위치입니다!");
		}
		if (direction.isGoingDiagonal() && pieces.findByPosition(destination) == null) {
			throw new IllegalMoveException("대각선에 아무것도 없기 때문에 폰이 이동할 수 없는 위치입니다!");
		}
	}

	private void validateDestination(Position source, Position destination) {
		if (source.equals(destination)) {
			throw new IllegalMoveException("말이 원래 있던 자리입니다!");
		}
	}

	private void validateNoObstacle(Position source, Position destination) {
		List<Position> positionsInBetween = source.getPositionsInBetween(destination);
		for (Position position : positionsInBetween) {
			if (pieces.findByPosition(position) != null) {
				throw new IllegalMoveException("경로에 다른 말이 있어 움직일 수 없습니다.");
			}
		}
	}

	private void killPiece(Piece piece, Piece destinationPiece) {
		if (piece.isSameTeam(destinationPiece)) {
			throw new IllegalMoveException("해당 자리에 같은 팀 말이 있기 때문에 말을 움직일 수 없습니다!");
		}
		destinationPiece.kill();
	}

	public double calculateScoreByTeam(Team team) {
		return new TotalScore(pieces.getAlivePiecesByTeam(team)).getTotalScore();
	}

	public Team getWinner() {
		return pieces.teamWithAliveKing();
	}
}
