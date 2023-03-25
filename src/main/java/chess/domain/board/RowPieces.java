package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceMatcher;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Column;
import chess.domain.piece.coordinate.Coordinate;
import chess.view.SymbolMatcher;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RowPieces implements Comparable<RowPieces> {

    private static final int FIRST_PIECE_INDEX = 0;
    private static final int INDEX_NUMBER_APPLIER = 1;

    private final List<Piece> pieces;

    public RowPieces(String rowNum) {
        this(InitialPieces.from(rowNum));
    }

    private RowPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public double sumPiecePoints(Team team) {
        List<Piece> piecesByTeam = makePiecesByTeam(team);
        double sum = 0;

        for (Piece piece : piecesByTeam) {
            sum += piece.point().valueOfPoint();
        }
        return sum;
    }

    private List<Piece> makePiecesByTeam(Team team) {
        return pieces.stream()
            .filter(piece -> piece.isSameTeam(team))
            .collect(Collectors.toList());
    }

    public boolean checkPawnByColumn(Column column, Team team) {
        return pieces.get(Column.indexFromColumn(column) - INDEX_NUMBER_APPLIER).isSameTeamAndPawn(team);
    }

    @Override
    public int compareTo(RowPieces o) {
        Piece firstPiece = this.pieces.get(FIRST_PIECE_INDEX);
        Piece otherPiece = o.pieces.get(FIRST_PIECE_INDEX);
        return firstPiece.compareTo(otherPiece);
    }

    public boolean isMovable(RowPieces targetRowPieces, Coordinate sourceCoordinate, Coordinate destinationCoordinate) {
        Piece sourcePiece = findPieceByCoordinate(this, sourceCoordinate);
        Piece destinationPiece = findPieceByCoordinate(targetRowPieces, destinationCoordinate);

        return sourcePiece.isMovable(destinationPiece);
    }

    private Piece findPieceByCoordinate(RowPieces rowPieces, Coordinate coordinate) {
        return rowPieces.pieces.stream()
            .filter(piece -> piece.hasCoordinate(coordinate))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 컬럼입니다"));
    }

    public boolean isPieceByColumnNotEmpty(Coordinate coordinate) {
        return !findPieceByCoordinate(this, coordinate).isSameTeam(Team.EMPTY);
    }

    public void move(
        RowPieces destinationRowPieces,
        Coordinate sourceCoordinate,
        Coordinate destinationCoordinate
    ) {
        Piece sourcePiece = findPieceByCoordinate(this, sourceCoordinate);
        Piece newPiece = sourcePiece.newSourcePiece(destinationCoordinate);

        destinationRowPieces.switchPiece(newPiece, destinationCoordinate);
        switchPiece(createEmpty(sourceCoordinate), sourceCoordinate);
    }

    private void switchPiece(Piece newPiece, Coordinate coordinate) {
        this.pieces.set(coordinate.columnIndex(), newPiece);
    }

    private Empty createEmpty(Coordinate coordinate) {
        return (Empty) PieceMatcher.of(SymbolMatcher.EMPTY, Team.EMPTY, coordinate);
    }

    public boolean isPieceKnight(Coordinate coordinate) {
        return findPieceByCoordinate(this, coordinate).isKnight();
    }

    public boolean hasCoordinate(Coordinate coordinate) {
        return this.pieces.stream().anyMatch(
            piece -> piece.hasCoordinate(coordinate)
        );
    }

    public boolean isContainsKing(Team team) {
        List<Piece> extractPieceByTeam = pieces.stream().filter(piece -> piece.isSameTeam(team))
            .collect(Collectors.toList());
        return extractPieceByTeam.stream()
            .anyMatch(Piece::isKing);
    }

    public List<Piece> pieces() {
        return pieces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RowPieces rowPieces = (RowPieces) o;
        return Objects.equals(pieces, rowPieces.pieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieces);
    }
}
