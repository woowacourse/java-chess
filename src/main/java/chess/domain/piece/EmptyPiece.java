package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.view.OutputView;

public class EmptyPiece extends Piece {
    private static final String NO_PIECE_EXISTS = "해당 위치에 기물이 존재하지 않습니다";
    public static final EmptyPiece EMPTY_PIECE = new EmptyPiece(Color.EMPTY);

    private EmptyPiece(Color color) {
        super(color);
    }

    @Override
    public boolean movable(Position from, Position to, Board board) {
        OutputView.printError(new IllegalArgumentException(NO_PIECE_EXISTS));
        return false;
    }
}
