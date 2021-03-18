package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.board.position.Position;

import java.util.*;

public class Board {

    private final List<Map<Position, Piece>> squares;

    public Board() {
        squares = InitBoardGenerator.initLines();
    }

    public List<Map<Position, Piece>> getSquares() {
        return squares;
    }
}
