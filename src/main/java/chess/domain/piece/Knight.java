package chess.domain.piece;

import chess.domain.*;
import chess.domain.coordinate.ChessCoordinate;

import java.util.*;
import java.util.stream.Collectors;

public class Knight extends ChessPiece {
    private static final Knight knightBlack = new Knight(Team.BLACK);
    private static final Knight knightWhite = new Knight(Team.WHITE);


    public static Knight getInstance(Team team) {
        if (team == Team.BLACK) {
            return knightBlack;
        }
        if (team == Team.WHITE) {
            return knightWhite;
        }
        throw new IllegalArgumentException("만들 수 없는 팀입니다.");
    }

    private Knight(Team team) {
        super(getPieceTypeByTeam(team));
    }

    private static PieceType getPieceTypeByTeam(Team team) {
        if (team == Team.BLACK) {
            return PieceType.KNIGHT_BLACK;
        }
        if (team == Team.WHITE) {
            return PieceType.KNIGHT_WHITE;
        }
        throw new IllegalArgumentException("만들 수 없는 팀입니다.");
    }

    @Override
    public Set<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        List<ChessCoordinate> candidates = new ArrayList<>();

        from.getX().move(INCREASE_TWO).ifPresent(proveYSide(from, candidates));
        from.getX().move(DECREASE_TWO).ifPresent(proveYSide(from, candidates));

        from.getY().move(INCREASE_TWO).ifPresent(proveXSide(from, candidates));
        from.getY().move(DECREASE_TWO).ifPresent(proveXSide(from, candidates));

        return candidates.stream()
                .filter((coord) -> pieceTeamProvider.getTeamAt(coord) != getType().getTeam())
                .collect(Collectors.toSet());
    }

}
