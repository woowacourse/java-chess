package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import java.util.List;

public class Blank extends Piece {

    public Blank(Color color, Position position) {
        super(color, position);
        this.type = Type.BLANK;
    }

    @Override
    public List<Position> getMovablePositions(ChessBoard chessBoard) {
        throw new UnsupportedOperationException();
    }
}
