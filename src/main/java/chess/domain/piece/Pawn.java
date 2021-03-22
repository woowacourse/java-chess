package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.pieceinformations.PieceInformation;
import chess.domain.position.Position;
import chess.domain.pieceinformations.TeamColor;

public class Pawn extends PieceOnBoard {

    public Pawn(TeamColor teamColor) {
        super(teamColor, PieceInformation.PAWN);
    }

    public Pawn(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.PAWN, position);
    }

    @Override
    public boolean isMoveAble(Position target, ChessBoard chessBoard) {
        return movablePawn(target, chessBoard);
    }



}
