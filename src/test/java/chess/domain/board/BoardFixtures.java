package chess.domain.board;

import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;

public class BoardFixtures {

    public static final Board INITIAL_BOARD = Board.of(new BoardGenerator());
}
