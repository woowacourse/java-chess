package domain;

import domain.piece.CommonMovablePiece;
import domain.position.Position;
import java.util.Map;

public class ChessBoard {

    Map<Position, CommonMovablePiece> board;

    public ChessBoard(final BoardGenerator boardGenerator) {
        this.board = boardGenerator.generate();
    }

    public String symbol(final Position position) {
        final CommonMovablePiece piece = board.get(position);
        if (piece == null) {
            return ".";
        }
        return piece.symbol();
    }

    @Override
    public String toString() {
        return "ChessBoard{" +
                "board=" + board +
                '}';
    }
}
