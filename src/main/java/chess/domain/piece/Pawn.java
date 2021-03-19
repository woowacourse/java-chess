package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.TeamColor;

public class Pawn extends PieceOnBoard {

    private static final String NAME = "p";
    private Position position;

    public Pawn(TeamColor teamColor) {
        super(teamColor, NAME);
    }


    public Pawn(TeamColor teamColor, Position position) {
        super(teamColor, NAME);
        this.position = position;
    }

    @Override
    public boolean isMoveAble(Position source, Position target, ChessBoard chessBoard) {
        return false;
    }


}
