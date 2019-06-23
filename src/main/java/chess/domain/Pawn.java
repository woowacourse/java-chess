package chess.domain;

import java.util.*;

public class Pawn extends ChessPiece {
    private static Map<Team, Pawn> pawns = new HashMap<>();

    static Pawn getInstance(Team team) {
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
    Set<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        if (from.getY() == ChessYCoordinate.RANK_1 || from.getY() == ChessYCoordinate.RANK_8) {
            return Collections.emptySet();
        }

        if (getType().getTeam() == Team.WHITE) {
            return provePawn(pieceTeamProvider, from, ChessYCoordinate.RANK_2, 1);
        }

        if (getType().getTeam() == Team.BLACK) {
            return provePawn(pieceTeamProvider, from, ChessYCoordinate.RANK_7, -1);
        }

        throw new IllegalArgumentException("사용할 수 없는 말입니다.");
    }

    private Set<ChessCoordinate> provePawn(PieceTeamProvider pieceTeamProvider, ChessCoordinate from, ChessYCoordinate yCoord, int sign) {
        Set<ChessCoordinate> candidates = new HashSet<>();

        getIfEmpty(pieceTeamProvider, ChessCoordinate.valueOf(from.getX(), from.getY().move(sign).get())).ifPresent(candidates::add);

        from.getX().move(-1)
                .ifPresent((x) -> getIfEnemy(pieceTeamProvider, ChessCoordinate.valueOf(x, from.getY().move(sign).get()))
                        .ifPresent(candidates::add));
        from.getX().move(1)
                .ifPresent((x) -> getIfEnemy(pieceTeamProvider, ChessCoordinate.valueOf(x, from.getY().move(sign).get()))
                        .ifPresent(candidates::add));

        if (from.getY() == yCoord) {
            getIfEmpty(pieceTeamProvider, ChessCoordinate.valueOf(from.getX(), from.getY().move(sign * 2).get())).ifPresent(candidates::add);
        }
        return candidates;
    }

}
