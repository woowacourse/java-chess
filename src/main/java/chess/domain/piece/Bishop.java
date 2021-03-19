package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.TeamColor;

public class Bishop extends PieceOnBoard {

    private static final String NAME = "b";
    private Position position;

    public Bishop(TeamColor teamColor) {
        super(teamColor, NAME);
    }


    public Bishop(TeamColor teamColor, Position position) {
        super(teamColor, NAME);
        this.position = position;
    }


    @Override
    public boolean isMoveAble(Position source, Position target, ChessBoard chessBoard) {
        return false;
    }



}
