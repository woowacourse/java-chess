package chess.domain.piece;

import chess.domain.*;
import chess.domain.coordinate.ChessCoordinate;

import java.util.*;

public class Bishop extends ChessPiece {
    private static final Bishop bishopBlack = new Bishop(Team.BLACK);
    private static final Bishop bishopWhite = new Bishop(Team.WHITE);

    public static Bishop getInstance(Team team) {
        if (team == Team.BLACK) {
            return bishopBlack;
        }
        if (team == Team.WHITE) {
            return bishopWhite;
        }
        throw new IllegalArgumentException("만들 수 없는 팀입니다.");
    }

    private Bishop(Team team) {
        super(getPieceTypeByTeam(team));
    }

    private static PieceType getPieceTypeByTeam(Team team) {
        if (team == Team.BLACK) {
            return PieceType.BISHOP_BLACK;
        }
        if (team == Team.WHITE) {
            return PieceType.BISHOP_WHITE;
        }
        throw new IllegalArgumentException("만들 수 없는 팀입니다.");
    }

    @Override
    public Set<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {

        return probeAllDiagonal(pieceTeamProvider, from);
    }
}
