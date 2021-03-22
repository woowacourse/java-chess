package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.result.Score;

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

    @Override
    public Score getScore() {
        return null;
    }


    @Override
    public Character getColumn() {
        throw new IllegalArgumentException("공백의 값을 구할 수 없습니다.");
    }
}
