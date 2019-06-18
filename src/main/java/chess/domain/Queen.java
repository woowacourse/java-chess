package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece{
    protected Queen(Team team) {
        super(getPieceTypeByTeam(team));
    }

    private static PieceType getPieceTypeByTeam(Team team) {
        if (team == Team.BLACK) {
            return PieceType.QUEEN_BLACK;
        }
        if (team == Team.WHITE) {
            return PieceType.QUEEN_WHITE;
        }
        throw new IllegalArgumentException();
    }

    @Override
    List<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        List<ChessCoordinate> movableCoords = new ArrayList<>();

        ChessXCoordinate fromX = from.getX();
        ChessYCoordinate fromY = from.getY();

        movableCoords.addAll(probeVertical(pieceTeamProvider, ChessXCoordinate.getAscendingCoordinates(fromX), fromY));
        movableCoords.addAll(probeVertical(pieceTeamProvider, ChessXCoordinate.getDescendingCoordinates(fromX), fromY));

        movableCoords.addAll(probeHorizon(pieceTeamProvider, fromX, ChessYCoordinate.getAscendingCoordinates(fromY)));
        movableCoords.addAll(probeHorizon(pieceTeamProvider, fromX, ChessYCoordinate.getDescendingCoordinates(fromY)));

        List<ChessXCoordinate> xCoords = ChessXCoordinate.getDescendingCoordinates(fromX);
        List<ChessYCoordinate> yCoords = ChessYCoordinate.getDescendingCoordinates(fromY);

        movableCoords.addAll(probeDiagonal(pieceTeamProvider, xCoords, yCoords));

        yCoords = ChessYCoordinate.getAscendingCoordinates(fromY);
        movableCoords.addAll(probeDiagonal(pieceTeamProvider, xCoords, yCoords));

        xCoords = ChessXCoordinate.getAscendingCoordinates(fromX);
        movableCoords.addAll(probeDiagonal(pieceTeamProvider, xCoords, yCoords));

        yCoords = ChessYCoordinate.getDescendingCoordinates(fromY);
        movableCoords.addAll(probeDiagonal(pieceTeamProvider, xCoords, yCoords));
        
        return movableCoords;
    }
}
