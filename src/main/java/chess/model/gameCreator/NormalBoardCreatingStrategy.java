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
        pieces.put("K", (coordinate) -> new Tile(coordinate, new King(BLACK_TEAM)));
        pieces.put("Q", (coordinate) -> new Tile(coordinate, new Queen(BLACK_TEAM)));
        pieces.put("B", (coordinate) -> new Tile(coordinate, new Bishop(BLACK_TEAM)));
        pieces.put("R", (coordinate) -> new Tile(coordinate, new Rook(BLACK_TEAM)));
        pieces.put("N", (coordinate) -> new Tile(coordinate, new Knight(BLACK_TEAM)));
        pieces.put("P", (coordinate) -> new Tile(coordinate, new Pawn(true, BLACK_TEAM)));
        pieces.put("movedP", (coordinate) -> new Tile(coordinate, new Pawn(false, BLACK_TEAM)));

        pieces.put("k", (coordinate) -> new Tile(coordinate, new King(WHITE_TEAM)));
        pieces.put("q", (coordinate) -> new Tile(coordinate, new Queen(WHITE_TEAM)));
        pieces.put("b", (coordinate) -> new Tile(coordinate, new Bishop(WHITE_TEAM)));
        pieces.put("r", (coordinate) -> new Tile(coordinate, new Rook(WHITE_TEAM)));
        pieces.put("n", (coordinate) -> new Tile(coordinate, new Knight(WHITE_TEAM)));
        pieces.put("p", (coordinate) -> new Tile(coordinate, new Pawn(true, WHITE_TEAM)));
        pieces.put("movedp", (coordinate) -> new Tile(coordinate, new Pawn(false, WHITE_TEAM)));
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
