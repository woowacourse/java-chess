package chess.domain.piece;

import chess.domain.pieceinformations.PieceInformation;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;

import java.util.Map;

public class Pawn extends PieceOnBoard {

    public Pawn(TeamColor teamColor) {
        super(teamColor, PieceInformation.PAWN);
    }

    public Pawn(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.PAWN, position);
    }

    @Override
    public boolean isMoveAble(Position target, Map<Position, Piece> chessBoard) {
        return movablePawn(target, chessBoard);
    }

}
