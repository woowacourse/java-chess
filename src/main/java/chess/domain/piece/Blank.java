package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.State;
import chess.domain.TeamColor;

public class Blank implements Piece {

    public static final Blank INSTANCE = new Blank();

    private Blank() {
    }


    @Override
    public boolean isMoveAble(Position source, Position target, ChessBoard chessBoard) {
        return false;
    }

    @Override
    public String getPieceName() {
        return ".";
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
    public void setPosition(Position end) {
        throw new IllegalArgumentException("공백은 움직일 수 없습니다.");
    }

    @Override
    public String toString() {
        return " .\n";
    }
}
