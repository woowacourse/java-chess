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
import chess.domain.movepattern.Direction;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.MovePatternFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ChessBoard {
	private static final int INIT_KING_COUNT = 2;
	private static final int ONE_PAWN_COUNT = 1;
	private static final double ONE_PAWN_BONUS = 0.5;
	private static final int ZERO = 0;
	private static final String ERROR_MESSAGE_EXIST_PIECE_ON_PATH = "경로에 다른 말이 존재합니다.";
	private static final String ERROR_MESSAGE_POSITION_EXIST_SAME_TEAM = "해당 칸에 같은 팀의 말이 존재 합니다.";

	private final List<Position> chessBoard;
	private final List<Piece> pieces;

	public ChessBoard() {
		this.chessBoard = ChessBoardFactory.create();
		this.pieces = initPieces();
	}

	private List<Piece> initPieces() {
		List<Piece> pieces = PieceBundleFactory.createPieceSet(new BlackTeam());
		pieces.addAll(PieceBundleFactory.createPieceSet(new WhiteTeam()));
		return pieces;
	}

	public void movePiece(Position sourcePosition, Position targetPosition) {
		validateTargetTeam(sourcePosition, targetPosition);
		validateMovable(sourcePosition, targetPosition);

		Piece pieceToMove = findPieceByPosition(sourcePosition);
		removeAttackedPiece(findPieceByPosition(targetPosition));
		pieceToMove.move(targetPosition);
	}

	private void validateTargetTeam(Position sourcePosition, Position targetPosition) {
		Piece sourcePiece = findPieceByPosition(sourcePosition);
		Piece targetPiece = findPieceByPosition(targetPosition);

		if (targetPiece == null) {
			return;
		}
		if (sourcePiece.isSameTeam(targetPiece)) {
			throw new IllegalArgumentException(ERROR_MESSAGE_POSITION_EXIST_SAME_TEAM);
		}
	}

	private void validateMovable(Position sourcePosition, Position targetPosition) {
		checkMovePattern(sourcePosition, targetPosition);
		checkObstacleOnPath(sourcePosition, targetPosition);
	}

	private void checkMovePattern(Position sourcePosition, Position targetPosition) {
		MovePattern movePattern = MovePatternFactory.findMovePattern(sourcePosition, targetPosition);
		Piece pieceToMove = findPieceByPosition(sourcePosition);
		Piece targetPiece = findPieceByPosition(targetPosition);

		if (pieceToMove instanceof Pawn) {
			Pawn pawn = (Pawn) pieceToMove;
			pawn.validateMovePattern(movePattern, targetPiece);
			return;
		}
		pieceToMove.validateMovePattern(movePattern);
	}

	private void checkObstacleOnPath(Position sourcePosition, Position targetPosition) {
		MovePattern movePattern = MovePatternFactory.findMovePattern(sourcePosition, targetPosition);
		Piece pieceToMove = findPieceByPosition(sourcePosition);

		if (pieceToMove.isKnight()) {
			return;
		}
		validatePath(sourcePosition, movePattern);
	}

	private void validatePath(Position sourcePosition, MovePattern movePattern) {
		Position testPosition = Position.of(sourcePosition);
		Direction direction = movePattern.getDirection();
		int count = movePattern.getCount();

		for (int i = 0; i < count; i++) {
			testPosition.move(direction);
			checkIsExistPieceOnPath(testPosition);
		}
	}

	private void checkIsExistPieceOnPath(Position path) {
		if (findPieceByPosition(path) != null) {
			throw new IllegalArgumentException(ERROR_MESSAGE_EXIST_PIECE_ON_PATH);
		}
	}

	public void removeAttackedPiece(Piece targetPiece) {
		if (targetPiece != null) {
			pieces.remove(targetPiece);
		}
	}

	public Piece findPieceByPosition(Position position) {
		return pieces.stream()
				.filter(x -> x.isEqualPosition(position))
				.findAny()
				.orElse(null);
	}

	public boolean isSurviveKings() {
		return pieces.stream().filter(x -> x instanceof King).count() == INIT_KING_COUNT;
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

	public List<Piece> findBlackTeam() {
		return pieces.stream()
				.filter(Piece::isBlackTeam)
				.collect(Collectors.toList());
	}

	public List<Piece> findWhiteTeam() {
		return pieces.stream()
				.filter(Piece::isWhiteTeam)
				.collect(Collectors.toList());
	}

	public List<Position> getChessBoard() {
		return Collections.unmodifiableList(chessBoard);
	}
}
