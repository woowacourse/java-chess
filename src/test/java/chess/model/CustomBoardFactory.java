package chess.model;

import chess.model.board.Board;
import chess.model.board.BoardFactory;
import chess.model.material.Color;
import java.util.List;

public class CustomBoardFactory implements BoardFactory {

    private final List<String> pieces;
    private final Color turn;

    public CustomBoardFactory(List<String> pieces, Color turn) {
        this.pieces = pieces;
        this.turn = turn;
    }

    @Override
    public Board generate() {
        return new Board(generatePieces(pieces), turn);
    }
}
