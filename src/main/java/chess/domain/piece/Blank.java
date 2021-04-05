package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.feature.Color;
import chess.domain.feature.Type;

import java.util.List;

public class Blank extends Piece {
    public static final String BLANK_MOVE_ERROR = "공백은 움직일 수 없습니다.";
    public static final String BLANK_COLOR_ERROR = "공백은 " + Color.NO_COLOR.name() + "여야 합니다.";

    public Blank(Color color, Position position) {
        super(Color.NO_COLOR, position);
        if (!color.equals(Color.NO_COLOR)) {
            throw new IllegalArgumentException(BLANK_COLOR_ERROR);
        }
        this.type = Type.BLANK;
    }

    public Blank(Position position) {
        super(position);
        this.type = Type.BLANK;
    }

    @Override
    public void move(ChessBoard chessBoard, Direction direction, Position targetPosition) {
        throw new UnsupportedOperationException(BLANK_MOVE_ERROR);
    }

    @Override
    public boolean isMovable(final ChessBoard chessBoard, final Direction direction, final Position targetPosition) {
        throw new UnsupportedOperationException(BLANK_MOVE_ERROR);
    }

    @Override
    public List<Direction> directions() {
        throw new UnsupportedOperationException(BLANK_MOVE_ERROR);
    }
}
