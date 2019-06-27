package chess.domain.piece;

import chess.domain.*;
import chess.domain.coordinate.ChessCoordinate;

import java.util.*;

public class Rook extends ChessPiece {
    private static final Rook rookBlack = new Rook(PieceType.ROOK_BLACK);
    private static final Rook rookWhite = new Rook(PieceType.ROOK_WHITE);

    public static Rook getInstance(Team team) {
        if (team == Team.BLACK) {
            return rookBlack;
        }
        if (team == Team.WHITE) {
            return rookWhite;
        }
        throw new IllegalArgumentException();
    }

    private Rook(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public Set<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {

        return probCross(pieceTeamProvider, from);
    }
}
