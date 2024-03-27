package chess.domain.piece.fixedmove;

import chess.domain.board.Coordinate;
import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import java.util.List;
import java.util.Map;
import java.util.Set;

abstract class FixedMovePiece extends AbstractPiece {

    FixedMovePiece(final PieceType type, Team team) {
        super(type, team);
    }

    abstract Set<Map.Entry<Integer, Integer>> weights();

    @Override
    public List<Coordinate> legalNextCoordinates(final Coordinate now, final Coordinate destination) {
        List<Coordinate> legalNextCoordinates = weights().stream()
                .filter(entry -> now.canMove(entry.getKey(), entry.getValue()))
                .filter(entry -> now.move(entry.getKey(), entry.getValue()).equals(destination))
                .map(entry -> now.move(entry.getKey(), entry.getValue()))
                .toList();
        if (legalNextCoordinates.isEmpty()) {
            throw new IllegalStateException("해당 기물은 목적지 좌표에 갈 수 없습니다.");
        }
        return legalNextCoordinates;
    }

    @Override
    public boolean canMove(final Coordinate now, final Coordinate destination,
                           final Map<Coordinate, Piece> boardInformation) {
        return boardInformation.values().stream()
                .anyMatch(this::isNotSameTeam);
    }

    @Override
    public Piece updateAfterMove() {
        return this;
    }
}
