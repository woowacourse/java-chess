package chess.domain.chessBoard;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

public class ChessBoard {
	private final Map<Position, ChessPiece> chessBoard;

	public ChessBoard(Map<Position, ChessPiece> chessBoard) {
		this.chessBoard = chessBoard;
	}

	public void move(Position sourcePosition, Position targetPosition) {
		Objects.requireNonNull(sourcePosition, "소스 위치가 null입니다.");
		Objects.requireNonNull(targetPosition, "타겟 위치가 null입니다.");

		ChessPiece sourceChessPiece = findChessPieceFrom(sourcePosition);
		checkMovableOrCatchable(sourceChessPiece, sourcePosition, targetPosition);
		checkRouteAndMove(sourceChessPiece, sourcePosition, targetPosition);
	}

	private ChessPiece findChessPieceFrom(Position position) {
		ChessPiece sourceChessPiece = chessBoard.get(position);

		if (Objects.isNull(chessBoard.get(position))) {
			throw new IllegalArgumentException("해당 위치에 체스 피스가 존재하지 않습니다.");
		}
		return sourceChessPiece;
	}

	private void checkMovableOrCatchable(ChessPiece sourceChessPiece, Position sourcePosition,
		Position targetPosition) {
		ChessPiece targetChessPiece = chessBoard.get(targetPosition);

		if (Objects.isNull(targetChessPiece)) {
			checkCanMoveWith(sourceChessPiece, sourcePosition, targetPosition);
			return;
		}
		checkCanCatchWith(sourceChessPiece, targetChessPiece, sourcePosition, targetPosition);
	}

	private void checkCanMoveWith(ChessPiece sourceChessPiece, Position sourcePosition, Position targetPosition) {
		if (!sourceChessPiece.canMove(sourcePosition, targetPosition)) {
			throw new IllegalArgumentException("체스 피스가 이동할 수 없습니다.");
		}
	}

	private void checkCanCatchWith(ChessPiece sourceChessPiece, ChessPiece targetChessPiece,
		Position sourcePosition, Position targetPosition) {
		if (sourceChessPiece.isSamePieceColorWith(targetChessPiece) ||
			!sourceChessPiece.canCatch(sourcePosition, targetPosition)) {
			throw new IllegalArgumentException("체스 피스가 이동할 수 없습니다.");
		}
	}

	private void checkRouteAndMove(ChessPiece sourceChessPiece, Position sourcePosition, Position targetPosition) {
		if (!sourceChessPiece.canLeap()) {
			checkChessPieceRoute(sourcePosition, targetPosition);
		}
		moveChessPiece(sourcePosition, targetPosition, sourceChessPiece);
	}

	private void checkChessPieceRoute(Position sourcePosition, Position targetPosition) {
		MoveDirection checkingDirection = findDirectionOf(sourcePosition, targetPosition);
		Position checkingPosition = checkingDirection.move(sourcePosition);

		while (!checkingPosition.equals(targetPosition) && isChessPieceExistFrom(checkingPosition)) {
			checkingPosition = checkingDirection.move(checkingPosition);
		}
	}

	private MoveDirection findDirectionOf(Position sourcePosition, Position targetPosition) {
		return Arrays.stream(MoveDirection.values())
			.filter(moveDirection -> moveDirection.isSameDirectionFrom(sourcePosition, targetPosition))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("체스 피스가 이동할 수 없는 위치를 입력하였습니다."));
	}

	private boolean isChessPieceExistFrom(Position checkingPosition) {
		if (Objects.nonNull(chessBoard.get(checkingPosition))) {
			throw new NullPointerException("체스 피스의 이동 경로에 다른 체스 피스가 존재합니다.");
		}
		return true;
	}

	private void moveChessPiece(Position sourcePosition, Position targetPosition, ChessPiece sourceChessPiece) {
		chessBoard.put(targetPosition, sourceChessPiece);
		chessBoard.remove(sourcePosition);
	}
}
