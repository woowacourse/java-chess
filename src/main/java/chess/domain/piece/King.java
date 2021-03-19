package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.State;
import chess.domain.TeamColor;

public class King implements Piece {

    private final TeamColor teamColor;

    private String pieceType = "k";

    public King(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    @Override
    public boolean isMoveAble(Position source, Position target, ChessBoard chessBoard) {
        return false;
    }

    @Override
    public String getPieceName() {
        if (teamColor == TeamColor.BLACK) {
            return pieceType.toUpperCase();
        }
        return pieceType.toLowerCase();
    }

    @Override
    public boolean isEnemyTeam(Piece comparePiece) {
        return false;
    }

    @Override
    public TeamColor getColor() {
        return null;
    }

    @Override
    public void dead() {

    }

    @Override
    public State getState() {
        return null;
    }

    @Override
    public String toString() {
        return teamColor.name() + " King\n";
    }

}
