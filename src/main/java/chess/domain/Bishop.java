package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bishop extends ChessPiece {

    protected Bishop(Team team) {
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
    List<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        List<ChessCoordinate> movableCoords = new ArrayList<>();

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
