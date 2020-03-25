package chess.view;

import chess.board.BoardGenerator;
import chess.board.Coordinate;
import chess.board.Tile;

import java.util.Collections;
import java.util.Map;

public class ChessBoard {
    private final Map<Coordinate, Tile> chessBoard;

    public ChessBoard(BoardGenerator boardGenerator) {
        this.chessBoard = boardGenerator.generate();

    }

    public Map<Coordinate, Tile> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }
}
