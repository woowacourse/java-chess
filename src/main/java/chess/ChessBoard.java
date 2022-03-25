package chess;

import chess.piece.Color;
import chess.piece.Piece;
import chess.position.Position;
import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard {

    private final Map<Position, Piece> board;

    public ChessBoard(List<Piece> squares) {
        board = squares.stream()
            .collect(Collectors.toUnmodifiableMap(Piece::getPosition, piece -> piece));
    }

    public ChessBoard(Map<Position, Piece> board) {
        this.board = Map.copyOf(board);
    }

    public ChessBoard transfer(Position from, Position to) {
        checkIsPossibleMovement(from, to);
        return new ChessBoard(createNewBoard(from, to));
    }

    private Map<Position, Piece> createNewBoard(Position from, Position to) {
        Piece piece = findPieceByPosition(from);
        Map<Position, Piece> newBoard = new HashMap<>(board);
        newBoard.remove(from);
        newBoard.put(to, piece.transfer(to));
        return newBoard;
    }

    private void checkIsPossibleMovement(Position from, Position to) {
        Piece piece = findPieceByPosition(from);

        if (isSameColorPiecesByPosition(from, to) || !piece.isPossibleMovement(to)
        || hasObstacleBetweenPositions(from, to) || piece.isPawn() && hasPieceByPosition(to)) {
            throw new IllegalArgumentException(String.format(
                "%s의 기물을 %s에서 %s로 이동할 수 없습니다.", piece.getClass().getSimpleName(), from, to));
        }
    }

    private Piece findPieceByPosition(Position position) {
        if (!board.containsKey(position)) {
            throw new IllegalArgumentException(String.format("%s에는 기물이 없습니다.", position));
        }
        return board.get(position);
    }

    public boolean isSameColorByPosition(Position from, Color color) {
        return findPieceByPosition(from).isSameColor(color);
    }

    private boolean isSameColorPiecesByPosition(Position from, Position to) {
        if (hasPieceByPosition(to)) {
            Piece fromPiece = findPieceByPosition(from);
            Piece toPiece = findPieceByPosition(to);
            return fromPiece.isSameColor(toPiece.getColor());
        }
        return false;
    }

    private boolean hasObstacleBetweenPositions(Position from, Position to) {
        return from.getPath(to).stream()
            .anyMatch(this::hasPieceByPosition);
    }

    private boolean hasPieceByPosition(Position position) {
        return board.containsKey(position);
    }

    public Collection<Piece> getPieces() {
        return board.values();
    }
}
