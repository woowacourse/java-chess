package chess.domain.piece;

import java.util.List;

import chess.domain.*;
import chess.exception.NotFoundPathException;

public abstract class Piece {
	private final Player player;
	private final Type type;

	private Score score;
	protected Position position;

	public Piece(Player player, Type type, Position position, Score score) {
		this.player = player;
		this.type = type;
		this.position = position;
		this.score = score;
	}

	public Player getPlayer() {
		return player;
	}

	public Position getPosition() {
		return position;
	}

	public ChessPiece getChessPiece() {
		return ChessPiece.getChessPiece(player, type);
	}

	public String getPieceImage() {
		return ChessPiece.getPieceImage(this.player, this.type);
	}

	public abstract Path getMovablePath(Position end);

	public abstract Path getAttackablePath(Position end);

	Path getValidPath(Position end, List<MovementInfo> movementInfos) {
		for (MovementInfo movementInfo : movementInfos) {
			Path path = getPath(movementInfo, end);
			if (path.contains(end)) {
				path.removeEndPosition();
				return path;
			}
		}
		throw new NotFoundPathException();
	}

	private Path getPath(MovementInfo movementInfo, Position end) {
		Path path = new Path();
		Direction direction = movementInfo.getDirection();
		Position currentPosition = position;
		int validDistance = getValidDistance(movementInfo, direction, end);

		for (int i = 0; i < validDistance; i++) {
			currentPosition = currentPosition.move(direction);
			path.add(currentPosition);
		}
		return path;
	}

	private int getValidDistance(MovementInfo movementInfo, Direction direction, Position end) {
		int distance = movementInfo.getMaxDistance();
		int positionMaxDistance = position.getMaxDistance(direction, end);
		return (distance > positionMaxDistance)? positionMaxDistance : distance;
	}

	public void changePosition(Position position) {
		this.position = position;
	}

	public boolean isSamePosition(Position position) {
		return this.position.equals(position);
	}

	public boolean isSamePosition(Piece piece) {
		return this.position.equals(piece.position);
	}

	public boolean isMine(Player player) {
		return this.player.equals(player);
	}

	public Score getScore() {
		return score;
	}

	public abstract boolean isPawn();

	public boolean isSameCoordinateX(int x) {
		return position.isSameCoordinateX(x);
	}

	public int getCoordinateX() {
		return position.getCoordinateX();
	}

	public int getCoordinateY() {
		return position.getCoordinateY();
	}

	public boolean isKing() {
		return type.equals(Type.KING);
	}
}
