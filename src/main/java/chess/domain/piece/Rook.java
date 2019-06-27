package chess.domain.piece;

import chess.domain.*;
import chess.domain.coordinate.ChessCoordinate;

import java.util.*;

public class Rook extends ChessPiece {
    private static final Rook rookBlack = new Rook(Team.BLACK);
    private static final Rook rookWhite = new Rook(Team.WHITE);

    public static Rook getInstance(Team team) {
        if (team == Team.BLACK) {
            return rookBlack;
        }
        if (team == Team.WHITE) {
            return rookWhite;
        }
        throw new IllegalArgumentException();
    }

    private Rook(Team team) {
        super(getPieceTypeByTeam(team));
    }

    private static PieceType getPieceTypeByTeam(Team team) {
        if (team == Team.BLACK) {
            return PieceType.ROOK_BLACK;
        }
        if (team == Team.WHITE) {
            return PieceType.ROOK_WHITE;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Set<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {

        return probCross(pieceTeamProvider, from);
    }
}
