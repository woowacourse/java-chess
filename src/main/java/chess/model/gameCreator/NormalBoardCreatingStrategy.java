package chess.model.gameCreator;

import chess.model.board.BoardDTO;
import chess.model.board.Tile;
import chess.model.piece.*;

import java.util.*;
import java.util.function.Function;

import static chess.model.board.Board.*;

public class NormalBoardCreatingStrategy implements BoardCreatingStrategy {
    private static Map<String, Function<String, Tile>> pieces = new HashMap<>();

    private BoardDTO dto;

    static {
        pieces.put("K", (coordinate) -> new Tile(coordinate, new King("black")));
        pieces.put("Q", (coordinate) -> new Tile(coordinate, new Queen("black")));
        pieces.put("B", (coordinate) -> new Tile(coordinate, new Bishop("black")));
        pieces.put("R", (coordinate) -> new Tile(coordinate, new Rook("black")));
        pieces.put("N", (coordinate) -> new Tile(coordinate, new Knight("black")));
        pieces.put("P", (coordinate) -> new Tile(coordinate, new Pawn(true, "black")));
        pieces.put("movedP", (coordinate) -> new Tile(coordinate, new Pawn(false, "black")));

        pieces.put("k", (coordinate) -> new Tile(coordinate, new King("white")));
        pieces.put("q", (coordinate) -> new Tile(coordinate, new Queen("white")));
        pieces.put("b", (coordinate) -> new Tile(coordinate, new Bishop("white")));
        pieces.put("r", (coordinate) -> new Tile(coordinate, new Rook("white")));
        pieces.put("n", (coordinate) -> new Tile(coordinate, new Knight("white")));
        pieces.put("p", (coordinate) -> new Tile(coordinate, new Pawn(true, "white")));
        pieces.put("movedp", (coordinate) -> new Tile(coordinate, new Pawn(false, "white")));
        pieces.put("#", (coordinate) -> new Tile(coordinate, null));
    }

    public NormalBoardCreatingStrategy(BoardDTO dto) {
        this.dto = dto;
    }

    @Override
    public Map<String, Tile> create() {
        Map<String, Tile> tiles = new HashMap<>();

        List<String> pieces = dto.getPieces();
        Collections.reverse(pieces);

        for (int row = ROW_SIZE - INITIAL_ROW; row >= 0; row--) {
            String piecesByRow = pieces.get(row);
            registerTile(tiles, row, piecesByRow);
        }

        return tiles;
    }

    private void registerTile(Map<String, Tile> tiles, int row, String piecesByRow) {
        for (int column = INITIAL_COLUMN; column <= COLUMN_SIZE; column++) {
            String piece = piecesByRow.substring(column - 1, column);
            String coordinate = String.valueOf(column) + (INITIAL_ROW + row);

            if ("P".equals(piece) && (INITIAL_ROW + row) == BLACK_PAWN_ROW) {
                piece = "moved".concat(piece);
            }

            if ("p".equals(piece) && (INITIAL_ROW + row) == WHITE_PAWN_ROW) {
                piece = "moved".concat(piece);
            }

            tiles.put(coordinate, NormalBoardCreatingStrategy.pieces.get(piece).apply(coordinate));
        }
    }
}
