package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pawn extends ChessPiece {
    protected Pawn(Team team) {
        super(getPieceTypeByTeam(team));
    }

    private static PieceType getPieceTypeByTeam(Team team) {
        if (team == Team.BLACK) {
            return PieceType.PAWN_BLACK;
        }
        if (team == Team.WHITE) {
            return PieceType.PAWN_WHITE;
        }
        throw new IllegalArgumentException();
    }

    @Override
    List<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        List<ChessCoordinate> candidates = new ArrayList<>();
        if (from.getY() == ChessYCoordinate.RANK_1 || from.getY() == ChessYCoordinate.RANK_8) {
            return candidates;
        }

        if (getType().getTeam() == Team.WHITE) {
            getIfEmpty(pieceTeamProvider, from.getX(), from.getY().move(1)).ifPresent(candidates::add);

            createXCoordinate(from.getX(), -1)
                    .ifPresent((x) -> getIfEnemy(pieceTeamProvider, x, from.getY().move(1))
                            .ifPresent(candidates::add));
            createXCoordinate(from.getX(), 1)
                    .ifPresent((x) -> getIfEnemy(pieceTeamProvider, x, from.getY().move(1))
                            .ifPresent(candidates::add));


            if (from.getY() == ChessYCoordinate.RANK_2) {
                getIfEmpty(pieceTeamProvider, from.getX(), from.getY().move(2)).ifPresent(candidates::add);
            }
            return candidates;
        }

        if (getType().getTeam() == Team.BLACK) {
            getIfEmpty(pieceTeamProvider, from.getX(), from.getY().move(-1)).ifPresent(candidates::add);

            createXCoordinate(from.getX(), -1)
                    .ifPresent((x) -> getIfEnemy(pieceTeamProvider, x, from.getY().move(-1))
                            .ifPresent(candidates::add));
            createXCoordinate(from.getX(), 1)
                    .ifPresent((x) -> getIfEnemy(pieceTeamProvider, x, from.getY().move(1))
                            .ifPresent(candidates::add));
            if (from.getY() == ChessYCoordinate.RANK_7) {
                getIfEmpty(pieceTeamProvider, from.getX(), from.getY().move(-2)).ifPresent(candidates::add);
            }
            return candidates;
        }

        return candidates;
    }

    private Optional<ChessXCoordinate> createXCoordinate(ChessXCoordinate from, int deltaX) {
        try {
            return Optional.of(from.move(deltaX));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
