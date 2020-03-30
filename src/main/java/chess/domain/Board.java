package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Team;
import chess.exception.IllegalMoveException;
import chess.exception.NullPieceException;

public class Board {
	private static final String SOURCE_SAME_WITH_DESTINATION = "말이 원래 있던 자리입니다.";
	private static final String NO_PIECE_IN_SOURCE = "해당 위치에 말이 없습니다.";
	private static final String SAME_TEAM_PIECE_IN_DESTINATION = "해당 자리에 같은 팀 말이 있기 때문에 말을 움직일 수 없습니다!";

	private final Pieces pieces;

	public Board() {
		this.pieces = new Pieces(PieceFactory.getInstance().getPieces());
	}

	public void movePiece(Position source, Position destination) {
		validateSameDestination(source, destination);
		Piece sourcePiece = pieces.findByPosition(source);
		validateSource(sourcePiece);
		Piece destinationPiece = pieces.findByPosition(destination);
		List<Piece> piecesInBetween = source.getPositionsInBetween(destination).stream()
				.map(pieces::findByPosition)
				.collect(Collectors.toList());
		sourcePiece.validateDestination(destination, destinationPiece, piecesInBetween);
		if (destinationPiece != null) {
			killPiece(sourcePiece, destinationPiece);
		}
		pieces.move(source, destination);
	}

	private void validateSameDestination(Position source, Position destination) {
		if (source.equals(destination)) {
			throw new IllegalMoveException(SOURCE_SAME_WITH_DESTINATION);
		}
	}

	private void validateSource(Piece piece) {
		if (piece == null) {
			throw new NullPieceException(NO_PIECE_IN_SOURCE);
		}
	}

	private void killPiece(Piece piece, Piece destinationPiece) {
		if (piece.isSameTeam(destinationPiece)) {
			throw new IllegalMoveException(SAME_TEAM_PIECE_IN_DESTINATION);
		}
		destinationPiece.kill();
	}

	public double calculateScoreByTeam(Team team) {
		return new TotalScore(pieces.getAlivePiecesByTeam(team)).getTotalScore();
	}

	public boolean isBothKingAlive() {
		return pieces.isBothKingAlive();
	}

	public Pieces getPieces() {
		return pieces;
	}

	public Team getWinner() {
		return pieces.teamWithAliveKing();
	}
}
