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
		if (movePattern instanceof KnightPattern) {
			moveKnightPattern(movePattern);
			return;
		}
		moveNotKnightPattern(movePattern, chessBoard);
	}

	private void moveKnightPattern(MovePattern movePattern) {
		KnightPattern knightPattern = (KnightPattern) movePattern;
		Position target = knightPattern.getTargetPosition();
		this.file = target.file;
		this.rank = target.rank;
	}

	private void moveNotKnightPattern(MovePattern movePattern, ChessBoard chessBoard) {
		ValidatePath(movePattern, chessBoard);

		Direction direction = movePattern.getDirection();
		int count = movePattern.getCount();
		for (int i = 0; i < count; i++) {
			this.move(direction);
		}
	}

	private void ValidatePath(MovePattern movePattern, ChessBoard chessBoard) {
		Direction direction = movePattern.getDirection();
		int count = movePattern.getCount();

		Position temp = Position.of(file, rank);
		for (int i = 0; i < count; i++) {
			temp.move(direction);
			checkIsExistPieceOnPath(chessBoard, temp);
		}
	}

	private void move(Direction direction) {
		int x = direction.getXDegree();
		int y = direction.getYDegree();
		this.file = File.of(this.file.getNumber() + x);
		this.rank = Rank.of(this.rank.getNumber() + y);
	}

	private void checkIsExistPieceOnPath(ChessBoard chessBoard, Position tmp) {
		if (chessBoard.isExistPieceOnPosition(tmp)) {
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
