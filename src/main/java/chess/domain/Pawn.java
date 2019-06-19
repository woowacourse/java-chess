package chess.domain;

import java.util.*;

public class Pawn extends ChessPiece {
    private static Map<Team, Pawn> pawns = new HashMap<>();

    public static Pawn getInstance(Team team) {
        if (!pawns.containsKey(team)) {
            pawns.put(team, new Pawn(team));
        }
        return pawns.get(team);
    }

    private Pawn(Team team) {
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
            getIfEmpty(pieceTeamProvider, ChessCoordinate.valueOf(from.getX(), from.getY().move(1).get())).ifPresent(candidates::add);

            from.getX().move(-1)
                    .ifPresent((x) -> getIfEnemy(pieceTeamProvider, ChessCoordinate.valueOf(x, from.getY().move(1).get()))
                            .ifPresent(candidates::add));
            from.getX().move(1)
                    .ifPresent((x) -> getIfEnemy(pieceTeamProvider, ChessCoordinate.valueOf(x, from.getY().move(1).get()))
                            .ifPresent(candidates::add));


            if (from.getY() == ChessYCoordinate.RANK_2) {
                getIfEmpty(pieceTeamProvider, ChessCoordinate.valueOf(from.getX(), from.getY().move(2).get())).ifPresent(candidates::add);
            }
            return candidates;
        }

        if (getType().getTeam() == Team.BLACK) {
            getIfEmpty(pieceTeamProvider, ChessCoordinate.valueOf(from.getX(), from.getY().move(-1).get())).ifPresent(candidates::add);

            from.getX().move(-1)
                    .ifPresent((x) -> getIfEnemy(pieceTeamProvider, ChessCoordinate.valueOf(x, from.getY().move(-1).get()))
                            .ifPresent(candidates::add));
            from.getX().move(1)
                    .ifPresent((x) -> getIfEnemy(pieceTeamProvider, ChessCoordinate.valueOf(x, from.getY().move(1).get()))
                            .ifPresent(candidates::add));
            if (from.getY() == ChessYCoordinate.RANK_7) {
                getIfEmpty(pieceTeamProvider, ChessCoordinate.valueOf(from.getX(), from.getY().move(-2).get())).ifPresent(candidates::add);
            }
            return candidates;
        }

        return candidates;
    }
}
