package chess.domain.piece;

import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;
import chess.domain.team.Score;
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
        throw new UnsupportedOperationException("빈칸입니다.");
    }

    @Override
    public void dead() {
        throw new UnsupportedOperationException("빈칸입니다.");
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void changePosition(Position end) {
        throw new UnsupportedOperationException("공백은 움직일 수 없습니다.");
    }

    @Override
    public String toString() {
        return " .\n";
    }

    @Override
    public Score getScore() {
        throw new UnsupportedOperationException("빈칸은 점수가 없어요~");
    }


    @Override
    public Character getColumn() {
        throw new UnsupportedOperationException("공백의 값을 구할 수 없습니다.");
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

}
