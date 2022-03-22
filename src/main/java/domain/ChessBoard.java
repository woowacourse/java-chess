package domain;

import domain.piece.Piece;
import domain.position.Position;
import java.util.Map;

public class ChessBoard {

    Map<Position, Piece> board;

    public ChessBoard(final BoardGenerator boardGenerator) {
        this.board = boardGenerator.generate();
    }

    public String getSymbol(final Position position) {
        final Piece piece = board.get(position);
        if (piece == null) {
            return ".";
        }
        return piece.getSymbol();
    }

    @Override
    public String toString() {
        return "ChessBoard{" +
                "board=" + board +
                '}';
    }
}
