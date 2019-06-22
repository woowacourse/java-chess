package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Set;

public abstract class Piece {
    private final TeamType teamType;
    private final PieceType pieceType;

    Piece(final TeamType teamType, final PieceType pieceType) {
        this.teamType = teamType;
        this.pieceType = pieceType;
    }

    boolean isSameTeam(Piece piece) {
        return this.teamType == piece.teamType;
    }

    public abstract Set<Position> makePossiblePositions(Position source, PositionChecker positionChecker);
}
