package chess.domain.chessboard;

import chess.domain.chessPiece.factory.PieceBundleFactory;
import chess.domain.chessPiece.piece.King;
import chess.domain.chessPiece.piece.Pawn;
import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.piece.PieceAbility;
import chess.domain.chessPiece.position.File;
import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.WhiteTeam;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.MovePatternFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ChessBoard {
	private static final String ERROR_MESSAGE_NOT_MOVABLE = "해당 말이 갈 수 없는 칸입니다.";
	private static final String ERROR_MESSAGE_POSITION_EXIST_SAME_TEAM = "해당 칸에 같은 팀의 말이 존재 합니다.";
	private static final int ZERO = 0;
	private static final int ONE_PAWN_COUNT = 1;
	private static final double ONE_PAWN_BONUS = 0.5;

	private final List<Position> chessBoard;
	private final List<Piece> blackTeam;
	private final List<Piece> whiteTeam;

	public ChessBoard() {
		this.chessBoard = ChessBoardFactory.create();
		this.blackTeam = PieceBundleFactory.createPieceSet(new BlackTeam());
		this.whiteTeam = PieceBundleFactory.createPieceSet(new WhiteTeam());
	}

	public void movePiece(Position sourcePosition, Position targetPosition) {
		validTargetTeam(sourcePosition, targetPosition);
		validMovable(sourcePosition, targetPosition);

		Piece targetPiece = findPieceByPosition(targetPosition);
		Piece pieceToMove = findPieceByPosition(sourcePosition);
		MovePattern movePattern = MovePatternFactory.of(sourcePosition, targetPosition);

		if (targetPiece != null) {
			removePiece(targetPiece);
		}

		pieceToMove.move(movePattern, this);
	}

	private void validTargetTeam(Position sourcePosition, Position targetPosition) {
		Piece sourcePiece = findPieceByPosition(sourcePosition);
		Piece targetPiece = findPieceByPosition(targetPosition);

		if (targetPiece == null) {
			return;
		}
		if (sourcePiece.isSameTeam(targetPiece)) {
			throw new IllegalArgumentException(ERROR_MESSAGE_POSITION_EXIST_SAME_TEAM);
		}
	}

	private void validMovable(Position sourcePosition, Position targetPosition) {
		MovePattern movePattern = MovePatternFactory.of(sourcePosition, targetPosition);
		Piece targetPiece = findPieceByPosition(targetPosition);
		Piece pieceToMove = findPieceByPosition(sourcePosition);

		if (pieceToMove instanceof Pawn) {
			Pawn pawn = (Pawn) pieceToMove;
			if (pawn.isMovable(movePattern, targetPiece)) {
				return;
			}
			throw new IllegalArgumentException(ERROR_MESSAGE_NOT_MOVABLE);
		}

		if (pieceToMove.isMovable(movePattern)) {
			return;
		}
		throw new IllegalArgumentException(ERROR_MESSAGE_NOT_MOVABLE);
	}

	public Piece findPieceByPosition(Position position) {
		Piece piece = blackTeam.stream()
				.filter(x -> x.isEqualPosition(position))
				.findAny()
				.orElse(null);
		if (piece == null) {
			return whiteTeam.stream()
					.filter(x -> x.isEqualPosition(position))
					.findAny()
					.orElse(null);
		}
		return piece;
	}

	public void removePiece(Piece targetPiece) {
		if (blackTeam.contains(targetPiece)) {
			blackTeam.remove(targetPiece);
			return;
		}
		whiteTeam.remove(targetPiece);
	}

	public boolean isExistPieceOnPosition(Position position) {
		return findPieceByPosition(position) != null;
	}

	public boolean isSurviveKings() {
		return blackTeam.stream().anyMatch(x -> x instanceof King)
				&& whiteTeam.stream().anyMatch(x -> x instanceof King);
	}

	public double calculateTeamScore(List<Piece> team) {
		double result = ZERO;
		for (File file : File.values()) {
			List<Piece> piecesInOneFile = team.stream()
					.filter(x -> x.isSameFile(file))
					.collect(Collectors.toList());
			result += piecesInOneFile.stream()
					.map(PieceAbility::getScore)
					.reduce((double) ZERO, Double::sum);
			if (isPawnCountOne(piecesInOneFile)) {
				result += ONE_PAWN_BONUS;
			}
		}
		return result;
	}

	private boolean isPawnCountOne(List<Piece> pieces) {
		return pieces.stream().filter(x -> x instanceof Pawn).count() == ONE_PAWN_COUNT;
	}

	public List<Piece> getBlackTeam() {
		return Collections.unmodifiableList(this.blackTeam);
	}

	public List<Piece> getWhiteTeam() {
		return Collections.unmodifiableList(this.whiteTeam);
	}

	public List<Position> getChessBoard() {
		return Collections.unmodifiableList(chessBoard);
	}
}
