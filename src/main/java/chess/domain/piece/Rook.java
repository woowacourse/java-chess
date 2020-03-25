package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.board.Board;
import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Row;
import chess.domain.piece.movable.RookMovable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rook extends Piece {
	private final List<Direction> directions = Direction.linearDirection();

	public Rook(Position position, String name, Color color) {
		super(position, name, new RookMovable(), color);
	}

	@Override
	public Set<Position> findRemovablePositions(Set<Position> positions, List<Piece> pieces) {
//		Set<Position> validPositions = new HashSet<>(positions);
//
//		for (Direction direction : directions) {
//			Row row = getPosition().getRow();
//			Column column = getPosition().getColumn();
//			Position position = Board.of(row, column);
//			while (Board.checkBound(position, direction)) {
//				row = row.calculate(direction.getXDegree());
//				column = column.calculate(direction.getYDegree());
//				position = Board.of(row, column);
//				if (!positions.contains(position)) {
//					break;
//				}
//				boolean flag = false;
//				for (Piece piece : pieces) {
//					if (piece.getPosition().equals(position)) {
//						flag = true;
//						break;
//					}
//				}
//				if (flag) break;
//			}
//		}
		return null;
	}
}
