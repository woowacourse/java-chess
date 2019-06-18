package chess.domain;

import java.util.List;
import java.util.Optional;

public abstract class ChessPiece {
    private PieceType type;

    protected ChessPiece(PieceType type) {
        this.type = type;
    }

    public PieceType getType() {
        return type;
    }

    abstract List<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from);

    protected Optional<ChessCoordinate> getIfEmpty(PieceTeamProvider pieceTeamProvider, ChessXCoordinate x, ChessYCoordinate y) {
        if (pieceTeamProvider.getTeamAt(x, y) == Team.NEUTRAL) {
            return Optional.of(ChessCoordinate.valueOf(x, y));
        }
        return Optional.empty();
    }

    protected Optional<ChessCoordinate> getIfEnemy(PieceTeamProvider pieceTeamProvider, ChessXCoordinate x, ChessYCoordinate y) {
        Team targetTeam = pieceTeamProvider.getTeamAt(x, y);

        if ((getType().getTeam() == Team.BLACK && targetTeam == Team.WHITE) ||
                (getType().getTeam() == Team.WHITE && targetTeam == Team.BLACK)) {
            return Optional.of(ChessCoordinate.valueOf(x, y));
        }
        return Optional.empty();
    }
}
