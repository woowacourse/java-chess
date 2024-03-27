package chess.model.piece;

import chess.model.game.PieceValue;
import chess.model.position.ChessPosition;
import chess.model.position.Path;

public class Blank extends Piece {
    public static final Blank INSTANCE = new Blank();

    private Blank() {
        super(Side.NONE);
    }

    @Override
    public Path findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
        throw new UnsupportedOperationException("빈칸으로부터 갈 수 있는 경로는 없습니다.");
    }

    @Override
    public PieceValue value() {
        throw new UnsupportedOperationException("빈칸은 기물 가치(Piece Value)가 없습니다.");
    }
}
