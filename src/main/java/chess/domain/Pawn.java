package chess.domain;

import java.util.*;

public class Pawn extends ChessPiece {
    private enum PawnInfo {
        BLACK(PieceType.PAWN_BLACK, ChessYCoordinate.RANK_7, -1),
        WHITE(PieceType.PAWN_WHITE, ChessYCoordinate.RANK_2, 1);

        private PieceType pieceType;
        private ChessYCoordinate initValue;
        private int direction;

        PawnInfo(PieceType pieceType, ChessYCoordinate initValue, int direction) {
            this.pieceType = pieceType;
            this.initValue = initValue;
            this.direction = direction;
        }

        public static PawnInfo valueOf(PieceType pieceType) {
            return Arrays.stream(values())
                    .filter(pawnInfo -> pawnInfo.pieceType.equals(pieceType))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 죄표입니다."));
        }
    }

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

        return provePawn(pieceTeamProvider, from, PawnInfo.valueOf(getType()));
    }

    private Set<ChessCoordinate> provePawn(PieceTeamProvider pieceTeamProvider, ChessCoordinate from, PawnInfo pawnInfo) {
        Set<ChessCoordinate> candidates = new HashSet<>();

        getIfEmpty(pieceTeamProvider, ChessCoordinate.valueOf(from.getX(), from.getY().move(pawnInfo.direction).get())).ifPresent(candidates::add);

        from.getX().move(DECREASE_ONE)
                .ifPresent((x) -> getIfEnemy(pieceTeamProvider, ChessCoordinate.valueOf(x, from.getY().move(pawnInfo.direction).get()))
                        .ifPresent(candidates::add));

        from.getX().move(INCREASE_ONE)
                .ifPresent((x) -> getIfEnemy(pieceTeamProvider, ChessCoordinate.valueOf(x, from.getY().move(pawnInfo.direction).get()))
                        .ifPresent(candidates::add));

        if (from.getY() == pawnInfo.initValue) {
            getIfEmpty(pieceTeamProvider, ChessCoordinate.valueOf(from.getX(), from.getY().move(pawnInfo.direction * INCREASE_TWO).get())).ifPresent(candidates::add);
        }
        return candidates;
    }
}
