package chess.domain.chessBoard;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import chess.domain.chessGame.ChessStatus;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

public class ChessBoard {

	private final Map<Position, ChessPiece> chessBoard;

	public ChessBoard(final Map<Position, ChessPiece> chessBoard) {
		Objects.requireNonNull(chessBoard, "체스 보드가 null입니다.");
		this.chessBoard = chessBoard;
	}

	public void validate(final Position position) {
		Objects.requireNonNull(position, "체스 위치가 null입니다.");
	}

	private void validate(final Position sourcePosition, final Position targetPosition) {
		Objects.requireNonNull(sourcePosition, "소스 위치가 null입니다.");
		Objects.requireNonNull(targetPosition, "타겟 위치가 null입니다.");
	}

	public boolean isKingOn(final Position position) {
		validate(position);
		final ChessPiece chessPiece = chessBoard.get(position);

		if (Objects.isNull(chessPiece)) {
			return false;
		}
		return chessPiece.isKing();
	}

	public boolean isChessPieceOn(final Position position) {
		validate(position);
		return chessBoard.containsKey(position);
	}

	public boolean isSamePieceColorOn(final Position position, final PieceColor pieceColor) {
		validate(position);
		Objects.requireNonNull(pieceColor, "피스 색상이 null입니다.");
		return chessBoard.get(position).isSame(pieceColor);
	}

	public boolean isLeapableChessPieceOn(final Position position) {
		validate(position);
		return chessBoard.get(position).canLeap();
	}

	public void checkChessPieceExistInRoute(final Position sourcePosition, final Position targetPosition) {
		validate(sourcePosition, targetPosition);
		final MoveDirection checkingDirection = findDirectionFrom(sourcePosition, targetPosition);
		Position checkingPosition = checkingDirection.move(sourcePosition);

		while (!checkingPosition.equals(targetPosition) && isChessPieceNotExistOn(checkingPosition)) {
			checkingPosition = checkingDirection.move(checkingPosition);
		}
	}

	private MoveDirection findDirectionFrom(final Position sourcePosition, final Position targetPosition) {
		return Arrays.stream(MoveDirection.values())
			.filter(moveDirection -> moveDirection.isSameDirectionFrom(sourcePosition, targetPosition))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("체스 피스가 이동할 수 없는 위치를 입력하였습니다."));
	}

	private boolean isChessPieceNotExistOn(final Position checkingPosition) {
		if (Objects.nonNull(chessBoard.get(checkingPosition))) {
			throw new IllegalArgumentException("체스 피스의 이동 경로에 다른 체스 피스가 존재합니다.");
		}
		return true;
	}

	public void checkCanMove(final Position sourcePosition, final Position targetPosition) {
		validate(sourcePosition, targetPosition);
		final ChessPiece sourceChessPiece = chessBoard.get(sourcePosition);

		if (!sourceChessPiece.canMove(sourcePosition, targetPosition)) {
			throw new IllegalArgumentException("체스 피스가 이동할 수 없습니다.");
		}
	}

	public void checkCanCatch(final Position sourcePosition, final Position targetPosition) {
		validate(sourcePosition, targetPosition);
		final ChessPiece sourceChessPiece = chessBoard.get(sourcePosition);
		final ChessPiece targetChessPiece = chessBoard.get(targetPosition);

		if (sourceChessPiece.isSamePieceColor(targetChessPiece)
			|| !sourceChessPiece.canCatch(sourcePosition, targetPosition)) {
			throw new IllegalArgumentException("체스 피스를 잡을 수 없습니다.");
		}
	}

	public void moveChessPiece(final Position sourcePosition, final Position targetPosition) {
		validate(sourcePosition, targetPosition);
		final ChessPiece sourceChessPiece = chessBoard.get(sourcePosition);
		chessBoard.put(targetPosition, sourceChessPiece);
		chessBoard.remove(sourcePosition);
	}

	public ChessStatus calculateStatus() {
		return ChessStatus.of(chessBoard);
	}

	public String getChessPieceNameOn(final Position position) {
		validate(position);
		return chessBoard.get(position).getName();
	}

	public Map<Position, ChessPiece> getChessBoard() {
		return chessBoard;
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		final ChessBoard that = (ChessBoard)object;
		return Objects.equals(chessBoard, that.chessBoard);
	}

	@Override
	public int hashCode() {
		return Objects.hash(chessBoard);
	}

}
