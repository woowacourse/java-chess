package chess.domain.piece.piecefigure;

import chess.domain.board.Position;
import chess.domain.board.PositionChecker;
import chess.domain.piece.pieceinfo.PieceType;
import chess.domain.piece.pieceinfo.TeamType;

import java.util.Set;

public abstract class Piece {
    private final TeamType teamType;
    private final PieceType pieceType;

    Piece(final TeamType teamType, final PieceType pieceType) {
        this.teamType = teamType;
        this.pieceType = pieceType;
    }

    public boolean isSameTeam(Piece piece) {
        return this.teamType == piece.teamType;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    public abstract Set<Position> makePossiblePositions(Position source, PositionChecker positionChecker);
}
