package chess.domain.piece;

import chess.domain.*;
import chess.domain.coordinate.ChessCoordinate;

import java.util.*;

public class Queen extends ChessPiece {
    private static final Queen queenBlack = new Queen(PieceType.QUEEN_BLACK);
    private static final Queen queenWhite = new Queen(PieceType.QUEEN_WHITE);

    public static Queen getInstance(Team team) {
        if (team == Team.BLACK) {
            return queenBlack;
        }
        if (team == Team.WHITE) {
            return queenWhite;
        }
        throw new IllegalArgumentException("만들 수 없는 팀입니다.");
    }

    private Queen(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public Set<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        Set<ChessCoordinate> movableCoords = probCross(pieceTeamProvider, from);

        movableCoords.addAll(probeAllDiagonal(pieceTeamProvider, from));

        return movableCoords;
    }
}
