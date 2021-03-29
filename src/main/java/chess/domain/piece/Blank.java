package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.strategy.MoveStrategy;
import java.util.List;

public class Blank extends Piece {

    public Blank(Color color) {
        super(color, new MoveStrategy() {
            @Override
            public boolean movable(ChessBoard chessBoard, Position sourcePosition,
                Position targetPosition, Piece sourcePiece) {
                throw new UnsupportedOperationException();
            }
        });
        this.type = Type.BLANK;
    }

    @Override
    public List<Direction> direction() {
        throw new UnsupportedOperationException();
    }
}
