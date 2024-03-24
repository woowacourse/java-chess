package chess.model.piece;

import chess.model.position.ChessPosition;

import java.util.List;

public class Blank extends Piece {
    public static final Blank INSTANCE = new Blank();

    private Blank() {
        super(Side.NONE);
    }

    @Override
    public List<ChessPosition> findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
        throw new UnsupportedOperationException("빈칸으로부터 갈 수 있는 경로는 없습니다.");
    }
}
