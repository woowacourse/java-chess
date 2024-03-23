package domain.board;

import domain.piece.Color;
import domain.piece.None;
import domain.piece.Piece;
import domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> squares;

    public Board(Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public static Board create() {
        SquaresGenerator squaresGenerator = new SquaresGenerator();
        Map<Position, Piece> squares = squaresGenerator.generate();
        return new Board(squares);
    }

    public List<Piece> extractPieces() {
        return squares.values().stream().toList();
    }

    public Piece findPieceByPosition(Position position) {
        return squares.get(position);
    }

    public void movePiece(Position source, Position target) {
        Piece sourcePiece = findPieceByPosition(source);
        squares.replace(target, sourcePiece);
        squares.replace(source, new None(Color.NONE));
    }

    public boolean isBlocked(Position source, Position target) {
        List<Position> betweenPositions = new ArrayList<>();
        if (source.isStraight(target)) {
            betweenPositions.addAll(source.findBetweenStraightPositions(target));
        }
        if (source.isDiagonal(target)) {
            betweenPositions.addAll(source.findBetweenDiagonalPositions(target));
        }
        return betweenPositions.stream()
                .map(this::findPieceByPosition)
                .anyMatch(Piece::isNotBlank);
    }
}
