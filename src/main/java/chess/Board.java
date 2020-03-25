package chess;

import chess.piece.Piece;
import chess.position.Position;

import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> pieces;

    public Board(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(Position from, Position to) {
        Piece piece = pieces.get(from);
        List<Position> trace = piece.findReachablePositions(from, to);
        if (isExistAt(trace)) {
        	throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
        }
		Piece target = pieces.remove(from);
		pieces.put(to, target);
    }

    public boolean isExistAt(List<Position> traces) {
        return traces.stream()
                .anyMatch(pieces::containsKey);
    }

    public Piece getPiece(Position position) {
        return pieces.get(position);
    }
}
