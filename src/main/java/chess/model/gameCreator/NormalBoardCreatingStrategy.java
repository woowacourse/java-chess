package chess.model.gameCreator;

import chess.model.board.BoardDTO;
import chess.model.board.Tile;
import chess.model.piece.*;

import java.util.*;
import java.util.function.Function;

import static chess.model.board.Board.*;

public class NormalBoardCreatingStrategy implements BoardCreatingStrategy {
    private static Map<String, Function<String, Tile>> pieces = new HashMap<>();
    private static final String MOVED_PAWN = "moved";
    private static final String MOVED_BLACK_PAWN = "movedP";
    private static final String MOVED_WHITE_PAWN = "movedp";

    private BoardDTO dto;

    static {
        pieces.put(BLACK_KING, (coordinate) -> new Tile(coordinate, new King(BLACK_TEAM)));
        pieces.put(BLACK_QUEEN, (coordinate) -> new Tile(coordinate, new Queen(BLACK_TEAM)));
        pieces.put(BLACK_BISHOP, (coordinate) -> new Tile(coordinate, new Bishop(BLACK_TEAM)));
        pieces.put(BLACK_ROOK, (coordinate) -> new Tile(coordinate, new Rook(BLACK_TEAM)));
        pieces.put(BLACK_KNIGHT, (coordinate) -> new Tile(coordinate, new Knight(BLACK_TEAM)));
        pieces.put(BLACK_PAWN, (coordinate) -> new Tile(coordinate, new Pawn(true, BLACK_TEAM)));
        pieces.put(MOVED_BLACK_PAWN, (coordinate) -> new Tile(coordinate, new Pawn(false, BLACK_TEAM)));

        pieces.put(WHITE_KING, (coordinate) -> new Tile(coordinate, new King(WHITE_TEAM)));
        pieces.put(WHITE_QUEEN, (coordinate) -> new Tile(coordinate, new Queen(WHITE_TEAM)));
        pieces.put(WHITE_BISHOP, (coordinate) -> new Tile(coordinate, new Bishop(WHITE_TEAM)));
        pieces.put(WHITE_ROOK, (coordinate) -> new Tile(coordinate, new Rook(WHITE_TEAM)));
        pieces.put(WHITE_KNIGHT, (coordinate) -> new Tile(coordinate, new Knight(WHITE_TEAM)));
        pieces.put(WHITE_PAWN, (coordinate) -> new Tile(coordinate, new Pawn(true, WHITE_TEAM)));
        pieces.put(MOVED_WHITE_PAWN, (coordinate) -> new Tile(coordinate, new Pawn(false, WHITE_TEAM)));
        pieces.put(EMPTY, (coordinate) -> new Tile(coordinate, null));
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

            if (BLACK_PAWN.equals(piece) && (INITIAL_ROW + row) == BLACK_PAWN_ROW) {
                piece = MOVED_PAWN.concat(piece);
            }

            if (WHITE_PAWN.equals(piece) && (INITIAL_ROW + row) == WHITE_PAWN_ROW) {
                piece = MOVED_PAWN.concat(piece);
            }

            tiles.put(coordinate, NormalBoardCreatingStrategy.pieces.get(piece).apply(coordinate));
        }
    }
}
