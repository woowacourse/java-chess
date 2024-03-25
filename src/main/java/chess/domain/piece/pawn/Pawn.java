package chess.domain.piece.pawn;

import chess.domain.board.Coordinate;
import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Pawn extends AbstractPiece {
    Pawn(final Team team) {
        super(PieceType.PAWN, team);
    }

    abstract Set<Entry<Integer, Integer>> straightWeights();

    abstract Set<Entry<Integer, Integer>> diagonalWeights();

    @Override
    public List<Coordinate> legalNextCoordinates(final Coordinate now, final Coordinate destination) {
        List<Coordinate> legalNextCoordinates = new ArrayList<>();
        legalNextCoordinates.addAll(straightLegalNextCoordinates(now));
        legalNextCoordinates.addAll(diagonalLegalNextCoordinates(now));
        if (legalNextCoordinates.contains(destination)) {
            return legalNextCoordinates;
        }
        throw new IllegalStateException("해당 기물은 목적지 좌표에 갈 수 없습니다.");
    }

    @Override
    public boolean canMove(final Coordinate now, final Coordinate destination,
                           final Map<Coordinate, Piece> boardInformation) {
        Set<Coordinate> coordinates = straightLegalNextCoordinates(now).stream()
                .filter(coordinate -> boardInformation.get(destination) == null)
                .collect(Collectors.toSet());
        coordinates.addAll(diagonalLegalNextCoordinates(now).stream()
                .filter(coordinate -> boardInformation.get(destination) != null && boardInformation.get(destination)
                        .isNotSameTeam(this))
                .collect(Collectors.toSet()));
        return coordinates.contains(destination);
    }

    private Set<Coordinate> straightLegalNextCoordinates(final Coordinate now) {
        return straightWeights().stream()
                .filter(entry -> now.canMove(entry.getKey(), entry.getValue()))
                .map(entry -> now.move(entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());
    }

    private Set<Coordinate> diagonalLegalNextCoordinates(final Coordinate now) {
        return diagonalWeights().stream()
                .filter(entry -> now.canMove(entry.getKey(), entry.getValue()))
                .map(entry -> now.move(entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());
    }
}
