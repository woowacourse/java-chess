package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.TeamColor;

public class Knight extends PieceOnBoard {

    private static final String NAME = "n";
    private Position position;

    public Knight(TeamColor teamColor) {
        super(teamColor, NAME);
    }


    public Knight(TeamColor teamColor, Position position) {
        super(teamColor, NAME);
        this.position = position;
    }

    @Override
    public boolean isMoveAble(Position source, Position target, ChessBoard chessBoard) {
        return false;
    }

}
