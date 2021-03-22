package domain;

import domain.piece.Empty;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.Color.*;

public class Board {
    private static final int SIZE = 8;
    private Map<Position, Piece> board;

    public Board(Map<Position, Piece> pieces) {
        board = new HashMap<>(pieces);
    }

    public void move(Position start, Position end) {
        Piece piece = board.get(start);
        if (piece.canMove2(getBoard(), start, end)) {
            board.remove(start);
            board.put(end, piece);
        }
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public Piece getPiece(Position position) {
        return board.getOrDefault(position, Empty.create());
    }

    public boolean canMovable(Position position, boolean color) {
        Piece piece = board.get(position);
        return !(piece instanceof Empty) &&  piece.isSameColor(color);
    }

    public Map<Position, Piece> getTeam(boolean color) {
        return board.entrySet().stream()
                .filter(entry -> entry.getValue().isSameColor(color))
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
    }

    public Map<Position, Piece> getBlackTeam() {
        return getTeam(BLACK.getValue());   // TODO : 상수 처리
    }

    public Map<Position, Piece> getWhiteTeam() {
        return getTeam(WHITE.getValue());   // TODO : 상수 처리
    }

    public boolean isExistSamePawn(Map.Entry<Position, Piece> pawn) {
        Position position = pawn.getKey();
        return Direction.verticalDirection().stream()
                .filter(direction -> !position.move(direction).equals(position))
                .map(direction -> getPiece(position.move(direction)))
                .filter(piece -> piece instanceof Pawn && piece.isSameColor(pawn.getValue()))
                .findAny()
                .isPresent();
    }
}
