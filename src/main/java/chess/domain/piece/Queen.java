package chess.domain.piece;

import chess.domain.*;
import chess.domain.coordinate.ChessCoordinate;

import java.util.*;

public class Queen extends ChessPiece {
    private static final Queen queenBlack = new Queen(Team.BLACK);
    private static final Queen queenWhite = new Queen(Team.WHITE);

    public static Queen getInstance(Team team) {
        if (team == Team.BLACK) {
            return queenBlack;
        }
        if (team == Team.WHITE) {
            return queenWhite;
        }
        throw new IllegalArgumentException("만들 수 없는 팀입니다.");
    }

    private Queen(Team team) {
        super(getPieceTypeByTeam(team));
    }

    private static PieceType getPieceTypeByTeam(Team team) {
        if (team == Team.BLACK) {
            return PieceType.QUEEN_BLACK;
        }
        if (team == Team.WHITE) {
            return PieceType.QUEEN_WHITE;
        }
        throw new IllegalArgumentException("만들 수 없는 팀입니다.");
    }

    @Override
    public Set<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        Set<ChessCoordinate> movableCoords = probCross(pieceTeamProvider, from);

        movableCoords.addAll(probeAllDiagonal(pieceTeamProvider, from));

        return movableCoords;
    }
}
