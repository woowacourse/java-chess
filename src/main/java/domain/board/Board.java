package domain.board;

import domain.piece.Color;
import domain.piece.None;
import domain.piece.Piece;
import domain.position.File;
import domain.position.Position;
import domain.position.PositionGenerator;
import domain.position.Rank;
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

    public Piece findPieceByPosition(Position position) {
        return squares.get(position);
    }

    public Piece findPieceByPosition(File file, Rank rank) {
        return squares.get(PositionGenerator.generate(file, rank));
    }

    public void movePiece(Position source, Position target) {
        Piece sourcePiece = findPieceByPosition(source);
        squares.replace(target, sourcePiece);
        squares.replace(source, new None(Color.NONE));
    }

    public boolean isBlocked(Position source, Position target) {
        List<Position> betweenPositions = source.betweenPositions(target);
        return betweenPositions.stream()
                .map(this::findPieceByPosition)
                .anyMatch(Piece::isNotBlank);
    }
}
