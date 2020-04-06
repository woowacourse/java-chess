package chess.domain.chessBoard;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import chess.domain.chessGame.ChessStatus;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.King;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.MoveDirection;
import chess.domain.position.Position;
import chess.web.dto.ChessBoardDto;

public class ChessBoard {

	private final Map<Position, ChessPiece> chessBoard;

	public ChessBoard(Map<Position, ChessPiece> chessBoard) {
		validate(chessBoard);
		this.chessBoard = chessBoard;
	}

	private void validate(Map<Position, ChessPiece> chessBoard) {
		if (Objects.isNull(chessBoard) || chessBoard.isEmpty()) {
			throw new IllegalArgumentException("체스 보드가 존재하지 않습니다.");
		}
	}

	public boolean isKingOn(Position targetPosition) {
		Objects.requireNonNull(targetPosition, "체스 위치가 null입니다.");
		return chessBoard.get(targetPosition) instanceof King;
	}

	public boolean isChessPieceOn(Position position) {
		Objects.requireNonNull(position, "체스 위치가 null입니다.");
		return chessBoard.containsKey(position);
	}

	public boolean isSamePieceColorOn(final Position sourcePosition, final PieceColor pieceColor) {
		Objects.requireNonNull(sourcePosition, "소스 위치가 null입니다.");
		Objects.requireNonNull(pieceColor, "피스 색상이 null입니다.");
		return chessBoard.get(sourcePosition).isSame(pieceColor);
	}

	public boolean isLeapableChessPieceOn(Position sourcePosition) {
		Objects.requireNonNull(sourcePosition, "체스 위치가 null입니다.");
		return chessBoard.get(sourcePosition).canLeap();
	}

	public void checkChessPieceExistInRoute(Position sourcePosition, Position targetPosition) {
		validate(sourcePosition, targetPosition);
		MoveDirection checkingDirection = findDirectionFrom(sourcePosition, targetPosition);
		Position checkingPosition = checkingDirection.move(sourcePosition);

		while (!checkingPosition.equals(targetPosition) && isChessPieceNotExistOn(checkingPosition)) {
			checkingPosition = checkingDirection.move(checkingPosition);
		}
	}

	private void validate(Position sourcePosition, Position targetPosition) {
		Objects.requireNonNull(sourcePosition, "소스 위치가 null입니다.");
		Objects.requireNonNull(targetPosition, "타겟 위치가 null입니다.");
	}

	private MoveDirection findDirectionFrom(Position sourcePosition, Position targetPosition) {
		validate(sourcePosition, targetPosition);
		return Arrays.stream(MoveDirection.values())
			.filter(moveDirection -> moveDirection.isSameDirectionFrom(sourcePosition, targetPosition))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("체스 피스가 이동할 수 없는 위치를 입력하였습니다."));
	}

	private boolean isChessPieceNotExistOn(Position checkingPosition) {
		if (Objects.nonNull(chessBoard.get(checkingPosition))) {
			throw new IllegalArgumentException("체스 피스의 이동 경로에 다른 체스 피스가 존재합니다.");
		}
		return true;
	}

	public void checkCanMove(Position sourcePosition, Position targetPosition) {
		validate(sourcePosition, targetPosition);
		ChessPiece sourceChessPiece = chessBoard.get(sourcePosition);

		if (!sourceChessPiece.canMove(sourcePosition, targetPosition)) {
			throw new IllegalArgumentException("체스 피스가 이동할 수 없습니다.");
		}
	}

	public void checkCanCatch(Position sourcePosition, Position targetPosition) {
		validate(sourcePosition, targetPosition);
		ChessPiece sourceChessPiece = chessBoard.get(sourcePosition);
		ChessPiece targetChessPiece = chessBoard.get(targetPosition);

		if (sourceChessPiece.isSamePieceColorWith(targetChessPiece)
			|| !sourceChessPiece.canCatch(sourcePosition, targetPosition)) {
			throw new IllegalArgumentException("체스 피스를 잡을 수 없습니다.");
		}
	}

	public void moveChessPiece(Position sourcePosition, Position targetPosition) {
		validate(sourcePosition, targetPosition);
		ChessPiece sourceChessPiece = chessBoard.get(sourcePosition);
		chessBoard.put(targetPosition, sourceChessPiece);
		chessBoard.remove(sourcePosition);
	}

	public ChessStatus calculateStatus() {
		return ChessStatus.of(chessBoard);
	}

	public String getChessPieceNameOn(Position position) {
		return chessBoard.get(position).getName();
	}

	public ChessBoardDto getChessBoardDto() {
		return ChessBoardDto.of(chessBoard);
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
