package domain;

import static domain.InitialPieces.*;
import static domain.PieceMoveResult.*;
import static domain.Team.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

class ChessBoard {
	private final List<Piece> piecesOnBoard;
	private Team currentTeam = WHITE;

	ChessBoard() {
		piecesOnBoard = new ArrayList<>(INITIAL_PIECES);
	}

	ChessBoard(Piece... pieces) {
		piecesOnBoard = new ArrayList<>(List.of(pieces));
	}

	boolean move(Position from, Position to) {
		if (isEmptyPosition(from) || isOtherTeamTurn(from)) {
			return false;
		}
		Piece piece = findPiece(from);
		PieceMoveResult moveResult = piece.move(to, new PiecesOnChessBoard(piecesOnBoard));
		if (moveResult.equals(CATCH)) {
			removeDeadPiece(to);
		}
		currentTeam = currentTeam.otherTeam();
		return moveResult.toBoolean();
	}

	private void removeDeadPiece(Position to) {
		Piece needToRemovePiece = piecesOnBoard.stream()
			.filter(piece -> piece.isOn(to))
			.filter(piece -> {
				Team pieceTeam = piece.getTeam();
				Team otherTeam = currentTeam.otherTeam();
				return pieceTeam.equals(otherTeam);
			})
			.findFirst().orElseThrow();
		piecesOnBoard.remove(needToRemovePiece);
	}

	private boolean isEmptyPosition(Position from) {
		Optional<Piece> optionalPiece = piecesOnBoard.stream()
			.filter(piece1 -> piece1.isOn(from))
			.findFirst();
		return optionalPiece.isEmpty();
	}

	private boolean isOtherTeamTurn(Position from) {
		Piece piece = findPiece(from);
		Team otherTeam = currentTeam.otherTeam();
		Team pieceTeam = piece.getTeam();
		return pieceTeam.equals(otherTeam);
	}

	private Piece findPiece(Position from) {
		return piecesOnBoard.stream()
			.filter(piece -> piece.isOn(from))
			.findFirst().orElseThrow();
	}

	List<Piece> getPiecesOnBoard() {
		return Collections.unmodifiableList(piecesOnBoard);
	}
}
