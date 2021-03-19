package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;

public class Fixture {
    public static final Rook whiteRook = new Rook(Color.WHITE);
    public static final Rook blackRook = new Rook(Color.BLACK);
    public static Board board = BoardFactory.createBoard();
}
