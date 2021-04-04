package chess.domain.piece;

import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;
import chess.domain.state.Score;

import java.util.Map;

public class Blank implements Piece {
    public static final Blank INSTANCE = new Blank();

    private Blank() {
    }

    @Override
    public String getPieceName() {
        return ".";
    }

    @Override
    public String getPieceType() {
        return "BLANK";
    }

    @Override
    public TeamColor getColor() {
        return TeamColor.NONE;
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
    public String toString() {
        return " .\n";
    }

    @Override
    public Score getScore() {
        throw new UnsupportedOperationException("빈칸은 점수가 없어요~");
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isMovable(Position source, Position target, Map<Position, Piece> chessBoard) {
        return false;
    }

}
