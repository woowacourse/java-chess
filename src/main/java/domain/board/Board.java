package domain.board;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceType;
import domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> squares;

    public Board(Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public List<Piece> extractPieces() {
        return squares.values().stream().toList();
    }

    public Piece findPieceByPosition(Position position) {
        return squares.get(position);
    }

    public void placePieceByPosition(Piece piece, Position position) {
        squares.replace(position, piece);
    }

    public void displacePieceByPosition(Position position) {
        squares.replace(position, new Piece(PieceType.NONE, Color.NONE));
    }

    public boolean isNotBlocked(Position source, Position target) {
        List<Position> betweenPositions = new ArrayList<>();
        if (source.isStraight(target)) {
            betweenPositions.addAll(source.findBetweenStraightPositions(target));
        }
        if (source.isDiagonal(target)) {
            betweenPositions.addAll(source.findBetweenDiagonalPositions(target));
        }
        return betweenPositions.stream()
                .map(this::findPieceByPosition)
                .allMatch(betweenPiece -> betweenPiece.isSameType(PieceType.NONE));
    }
}
