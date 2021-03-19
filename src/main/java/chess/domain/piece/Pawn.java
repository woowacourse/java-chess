package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.State;
import chess.domain.TeamColor;

public class Pawn implements Piece {

    public final TeamColor teamColor;
    private State state = State.ALIVE;

    private String pieceType = "p";

    public Pawn(TeamColor teamColor) {
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
        return teamColor;
    }

    @Override
    public void dead() {
        state = State.DEAD;

    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return teamColor.name() + " Pawn\n";
    }
}
