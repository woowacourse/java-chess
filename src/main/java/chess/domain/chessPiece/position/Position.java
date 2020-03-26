package chess.domain.chessPiece.position;

import chess.domain.chessPiece.piece.Pawn;
import chess.domain.chessboard.ChessBoard;
import chess.domain.movepattern.Direction;
import chess.domain.movepattern.KnightPattern;
import chess.domain.movepattern.MovePattern;

import java.util.Objects;

public class Position {
	private static final int FILE_INDEX = 0;
	private static final int RANK_INDEX = 1;
	private static final String ERROR_MESSAGE_EXIST_PIECE_ON_PATH = "경로에 다른 말이 존재합니다.";
	private static final String FILE_RANK_DELIMITER = "";

	private File file;
	private Rank rank;

	private Position(File file, Rank rank) {
		this.file = file;
		this.rank = rank;
	}

	public static Position of(String coordinate) {
		String[] fileAndRank = coordinate.split(FILE_RANK_DELIMITER);
		File file = File.of(fileAndRank[FILE_INDEX]);
		Rank rank = Rank.of(fileAndRank[RANK_INDEX]);
		return Position.of(file, rank);
	}

	public static Position of(File file, Rank rank) {
		return new Position(file, rank);
	}

	public boolean isPawnStartLine(Pawn pawn) {
		if (pawn.isBlackTeam()) {
			return this.rank == Rank.TWO;
		}
		return this.rank == Rank.SEVEN;
	}

	public boolean isNewLine() {
		return file == File.A;
	}

	public boolean isSameRank(Position target) {
		return this.rank == target.rank;
	}

	public boolean isSameFile(Position target) {
		return isSameFile(target.file);
	}

	public boolean isSameFile(File file) {
		return this.file == file;
	}

	public int calculateRankDistance(Position target) {
		return rank.calculateDistance(target.rank);
	}

	public int calculateFileDistance(Position target) {
		return file.calculateDistance(target.file);
	}

	public void move(MovePattern movePattern, ChessBoard chessBoard) {
		Direction direction = movePattern.getDirection();
		int count = movePattern.getCount();

		if (movePattern instanceof KnightPattern) {
			moveKnightPattern(movePattern);
			return;
		}
		if (direction == Direction.UP) {
			moveUp(chessBoard, count);
			return;
		}
		if (direction == Direction.DOWN) {
			moveDown(chessBoard, count);
			return;
		}
		if (direction == Direction.RIGHT) {
			moveRight(chessBoard, count);
			return;
		}
		if (direction == Direction.LEFT) {
			moveLeft(chessBoard, count);
			return;
		}
		if (direction == Direction.UP_RIGHT) {
			moveUpRight(chessBoard, count);
			return;
		}
		if (direction == Direction.UP_LEFT) {
			moveUpLeft(chessBoard, count);
			return;
		}
		if (direction == Direction.DOWN_RIGHT) {
			moveDownRight(chessBoard, count);
			return;
		}
		if (direction == Direction.DOWN_LEFT) {
			moveDownLeft(chessBoard, count);
		}
	}

	private void moveKnightPattern(MovePattern movePattern) {
		KnightPattern knightPattern = (KnightPattern) movePattern;
		Position target = knightPattern.getTargetPosition();
		this.file = target.file;
		this.rank = target.rank;
	}

	private void moveDownLeft(ChessBoard chessBoard, int count) {
		int xDegree = this.file.getNumber();
		int yDegree = this.rank.getNumber();
		for (int i = 0; i < count; i++) {
			xDegree--;
			yDegree--;
			isExistPieceOnPath(chessBoard, xDegree, yDegree);
		}
		this.file = File.of(xDegree);
		this.rank = Rank.of(yDegree);
	}

	private void moveDownRight(ChessBoard chessBoard, int count) {
		int xDegree = this.file.getNumber();
		int yDegree = this.rank.getNumber();
		for (int i = 0; i < count; i++) {
			xDegree++;
			yDegree--;
			isExistPieceOnPath(chessBoard, xDegree, yDegree);
		}
		this.file = File.of(xDegree);
		this.rank = Rank.of(yDegree);
	}

	private void moveUpLeft(ChessBoard chessBoard, int count) {
		int xDegree = this.file.getNumber();
		int yDegree = this.rank.getNumber();
		for (int i = 0; i < count; i++) {
			xDegree--;
			yDegree++;
			isExistPieceOnPath(chessBoard, xDegree, yDegree);
		}
		this.file = File.of(xDegree);
		this.rank = Rank.of(yDegree);
	}

	private void moveUpRight(ChessBoard chessBoard, int count) {
		int xDegree = this.file.getNumber();
		int yDegree = this.rank.getNumber();
		for (int i = 0; i < count; i++) {
			xDegree++;
			yDegree++;
			isExistPieceOnPath(chessBoard, xDegree, yDegree);
		}
		this.file = File.of(xDegree);
		this.rank = Rank.of(yDegree);
	}

	private void moveUp(ChessBoard chessBoard, int count) {
		int xDegree = this.file.getNumber();
		int yDegree = this.rank.getNumber();
		for (int i = 0; i < count; i++) {
			yDegree++;
			isExistPieceOnPath(chessBoard, xDegree, yDegree);
		}
		this.rank = Rank.of(yDegree);
	}

	private void moveDown(ChessBoard chessBoard, int count) {
		int xDegree = this.file.getNumber();
		int yDegree = this.rank.getNumber();
		for (int i = 0; i < count; i++) {
			yDegree--;
			isExistPieceOnPath(chessBoard, xDegree, yDegree);
		}
		this.rank = Rank.of(yDegree);
	}

	private void moveRight(ChessBoard chessBoard, int count) {
		int xDegree = this.file.getNumber();
		int yDegree = this.rank.getNumber();
		for (int i = 0; i < count; i++) {
			xDegree++;
			isExistPieceOnPath(chessBoard, xDegree, yDegree);
		}
		this.file = File.of(xDegree);
	}

	private void moveLeft(ChessBoard chessBoard, int count) {
		int xDegree = this.file.getNumber();
		int yDegree = this.rank.getNumber();
		for (int i = 0; i < count; i++) {
			xDegree--;
			isExistPieceOnPath(chessBoard, xDegree, yDegree);
		}
		this.file = File.of(xDegree);
	}

	private void isExistPieceOnPath(ChessBoard chessBoard, int xDegree, int yDegree) {
		Position positionOfPath = Position.of(File.of(xDegree), Rank.of(yDegree));
		if (chessBoard.isExistPieceOnPosition(positionOfPath)) {
			throw new IllegalArgumentException(ERROR_MESSAGE_EXIST_PIECE_ON_PATH);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Position position = (Position) o;
		return file == position.file &&
				rank == position.rank;
	}

	@Override
	public int hashCode() {
		return Objects.hash(file, rank);
	}
}
