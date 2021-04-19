package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.strategy.MoveStrategy;
import java.util.List;

public class Blank extends Piece {

    public static final String BLANK_ERROR = "[ERROR] 빈 칸을 조작할 수 없습니다.";

    public Blank(Color color) {
        super(color, new MoveStrategy() {
            @Override
            public boolean movable(ChessBoard chessBoard, Position sourcePosition,
                Position targetPosition, Piece sourcePiece) {
                throw new UnsupportedOperationException(BLANK_ERROR);
            }
        });
        this.type = Type.BLANK;
    }

    public Blank(Color color, char piece) {
        super(color, piece);
    }

    @Override
    public List<Direction> direction() {
        throw new UnsupportedOperationException(BLANK_ERROR);
    }
}
