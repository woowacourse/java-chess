package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Rook extends ChessPiece {

    public Rook(Team team) {
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
    public List<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        List<ChessCoordinate> movableCoords = new ArrayList<>();

        ChessXCoordinate fromX = from.getX();
        ChessYCoordinate fromY = from.getY();

        movableCoords.addAll(probeVertical(pieceTeamProvider, ChessXCoordinate.getAscendingCoordinates(fromX), fromY));
        movableCoords.addAll(probeVertical(pieceTeamProvider, ChessXCoordinate.getDescendingCoordinates(fromX), fromY));

        movableCoords.addAll(probeHorizon(pieceTeamProvider, fromX, ChessYCoordinate.getAscendingCoordinates(fromY)));
        movableCoords.addAll(probeHorizon(pieceTeamProvider, fromX, ChessYCoordinate.getDescendingCoordinates(fromY)));

        return movableCoords;
    }

}
