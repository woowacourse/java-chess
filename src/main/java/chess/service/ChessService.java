package chess.service;

import chess.model.MoveHandler;
import chess.model.Square;
import chess.model.board.BasicBoardInitializer;
import chess.model.board.Board;
import chess.model.unit.Side;

public class ChessService {
    private static Board board = new Board();

    static {
        board.initialize(new BasicBoardInitializer());
    }

    public boolean canMove(Square source, Square target) {
        return new MoveHandler(board).move(source, target, Side.WHITE);
    }
}