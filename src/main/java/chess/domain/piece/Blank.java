package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.player.Score;
import java.util.Map;

public class Blank implements Piece {

    public static final Blank INSTANCE = new Blank();

    private Blank() {
    }

    @Override
    public boolean isMoveAble(Position target, Map<Position, Piece> chessBoard) {
        return false;
    }

    @Override
    public String getPieceName() {
        return ".";
    }

    @Override
    public TeamColor getColor() {
        return null;
    }

    @Override
    public void dead() {

    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void changePosition(Position end) {
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
