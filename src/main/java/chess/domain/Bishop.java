package chess.domain;

import java.util.*;

public class Bishop extends ChessPiece {
    private static Map<Team, Bishop> bishops = new HashMap<>();

    static Bishop getInstance(Team team) {
        if (!bishops.containsKey(team)) {
            bishops.put(team, new Bishop(team));
        }
        return bishops.get(team);
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
        throw new IllegalArgumentException();
    }

    @Override
    Set<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        Set<ChessCoordinate> movableCoords = new HashSet<>();

        List<ChessXCoordinate> xCoords = ChessXCoordinate.getDescendingCoordinates(from.getX());
        List<ChessYCoordinate> yCoords = ChessYCoordinate.getDescendingCoordinates(from.getY());

        movableCoords.addAll(probeDiagonal(pieceTeamProvider, xCoords, yCoords));

        yCoords = ChessYCoordinate.getAscendingCoordinates(from.getY());
        movableCoords.addAll(probeDiagonal(pieceTeamProvider, xCoords, yCoords));

        xCoords = ChessXCoordinate.getAscendingCoordinates(from.getX());
        movableCoords.addAll(probeDiagonal(pieceTeamProvider, xCoords, yCoords));

        yCoords = ChessYCoordinate.getDescendingCoordinates(from.getY());
        movableCoords.addAll(probeDiagonal(pieceTeamProvider, xCoords, yCoords));

        return movableCoords;
    }
}
