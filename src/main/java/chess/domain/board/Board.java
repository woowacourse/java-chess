package chess.domain.board;

import chess.domain.piece.piecefigure.*;
import chess.domain.piece.pieceinfo.PieceType;
import chess.domain.piece.pieceinfo.TeamType;
import chess.exception.NotMovablePositionException;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    private static final String NEW_LINE = "\n";

    private final Map<Position, Piece> pieces;

    public Board(final Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(pieces);
    }

    public boolean movePiece(Position source, Position destination) {
        if (!getPossiblePositions(source).contains(destination)) {
            throw new NotMovablePositionException();
        }

        boolean result = (pieces.get(destination).getPieceType() == PieceType.KING);

        pieces.put(destination, pieces.get(source));
        pieces.put(source, Blank.of());
        return result;
    }

    public Set<Position> getPossiblePositions(Position source) {
        return pieces.get(source).makePossiblePositions(source, this::getCurrentPiece);
    }

    public Piece getCurrentPiece(Position current) {
        return pieces.get(current);
    }

    public double calculateFinalScore(TeamType teamType) {
        return sumTotalScore(teamType) - sumPawnScoreInSameColumn(teamType);
    }

    private double sumTotalScore(TeamType teamType) {
        return pieces.keySet().stream()
                .filter(position -> pieces.get(position).isSameTeam(Bishop.of(teamType)))
                .mapToDouble(position -> pieces.get(position).getPieceType().getPoint())
                .sum();
    }

    private double sumPawnScoreInSameColumn(TeamType teamType) {
        Piece pawn = (teamType == TeamType.WHITE) ? WhitePawn.of() : BlackPawn.of();

        return pieces.keySet().stream()
                .filter(position -> pieces.get(position) == pawn)
                .collect(Collectors.groupingBy(Position::getCoordinateY, Collectors.counting()))
                .values().stream()
                .filter(y -> y > 1)
                .mapToDouble(y -> y * 0.5)
                .sum();
    }

    public String parseBoard() {
        StringBuilder sb = new StringBuilder();

        IntStream.rangeClosed(0, 7).forEach(row -> {
            sb.append(getPieceTypesInRow(row));
            sb.append(NEW_LINE);
        });

        return sb.toString();
    }

    private String getPieceTypesInRow(int row) {
        StringBuilder sb = new StringBuilder();

        IntStream.rangeClosed(0, 7).forEach(col -> {
            Position position = Position.of(Position.makeKey(row, col));
            sb.append(judgePieceType(position));
        });

        return sb.toString();
    }

    private String judgePieceType(Position position) {
        Piece piece = pieces.get(position);

        return (piece.isSameTeam(WhitePawn.of()))
                ? piece.getPieceType().getType().toLowerCase()
                : piece.getPieceType().getType().toUpperCase();
    }
}
