package chess.domain.piece;

import chess.domain.*;
import chess.domain.coordinate.ChessCoordinate;

import java.util.*;

public class Bishop extends ChessPiece {
    private static final Bishop bishopBlack = new Bishop(PieceType.BISHOP_BLACK);
    private static final Bishop bishopWhite = new Bishop(PieceType.BISHOP_WHITE);

    public static Bishop getInstance(Team team) {
        if (team == Team.BLACK) {
            return bishopBlack;
        }
        if (team == Team.WHITE) {
            return bishopWhite;
        }
        throw new IllegalArgumentException("만들 수 없는 팀입니다.");
    }

    private Bishop(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public Set<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {

        return probeAllDiagonal(pieceTeamProvider, from);
    }
}
