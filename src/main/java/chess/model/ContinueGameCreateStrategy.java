package chess.model;

import chess.model.piece.*;

import java.util.*;

import static chess.model.ChessGame.*;

public class ContinueGameCreateStrategy implements CreateStrategy {
    private BoardDTO dto;

    public ContinueGameCreateStrategy(BoardDTO dto) {
        this.dto = dto;
    }

    public Map<String, Tile> create() {
        Map<String, Tile> tiles = new HashMap<>();

        List<String> pieces = dto.getPieces();
        Collections.reverse(pieces);

        convertPiecesInformationToMap(pieces, tiles);

        return tiles;
    }

    private void convertPiecesInformationToMap(List<String> pieces, Map<String, Tile> tiles) {
        for (int i = 7; i >= 0; i--) {
            String row = pieces.get(i);
            drawTilesFromPieces(row, i, tiles);
        }
    }

    private void drawTilesFromPieces(String row, int i, Map<String,Tile> tiles) {
        for (int j = 1; j <= 8; j++) {
            String piece = row.substring(j - 1, j);
            String coordinate = String.valueOf(j) + (i+1);

            drawBlackPieces(i, tiles, piece, coordinate);
            drawWhitePieces(i, tiles, piece, coordinate);
            drawEmptyTiles(tiles, piece, coordinate);
        }
    }

    private void drawEmptyTiles(Map<String, Tile> tiles, String piece, String coordinate) {
        if (EMPTY_SYMBOL.equals(piece)) {
            tiles.put(coordinate, new Tile(coordinate, new Empty()));
        }
    }

    private void drawWhitePieces(int i, Map<String, Tile> tiles, String piece, String coordinate) {
        if (WHITE_KING_SYMBOL.equals(piece)) {
            tiles.put(coordinate, new Tile(coordinate, new King("white")));
        }
        if (WHITE_QUEEN_SYMBOL.equals(piece)) {
            tiles.put(coordinate, new Tile(coordinate, new Queen("white")));
        }
        if (WHITE_BISHOP_SYMBOL.equals(piece)) {
            tiles.put(coordinate, new Tile(coordinate, new Bishop("white")));
        }
        if (WHITE_ROOK_SYMBOL.equals(piece)) {
            tiles.put(coordinate, new Tile(coordinate, new Rook("white")));
        }
        if (WHITE_KNIGHT_SYMBOL.equals(piece)) {
            tiles.put(coordinate, new Tile(coordinate, new Knight("white")));
        }
        if (WHITE_PAWN_SYMBOL.equals(piece) && (i + 1) == 7) {
            tiles.put(coordinate, new Tile(coordinate, new Pawn(false, "white")));
        }
        if (WHITE_PAWN_SYMBOL.equals(piece) && (i + 1) != 7) {
            tiles.put(coordinate, new Tile(coordinate, new Pawn(true, "white")));
        }
    }

    private void drawBlackPieces(int i, Map<String, Tile> tiles, String piece, String coordinate) {
        if (BLACK_KING_SYMBOL.equals(piece)) {
            tiles.put(coordinate, new Tile(coordinate, new King("black")));
        }
        if (BLACK_QUEEN_SYMBOL.equals(piece)) {
            tiles.put(coordinate, new Tile(coordinate, new Queen("black")));
        }
        if (BLACK_BISHOP_SYMBOL.equals(piece)) {
            tiles.put(coordinate, new Tile(coordinate, new Bishop("black")));
        }
        if (BLACK_ROOK_SYMBOL.equals(piece)) {
            tiles.put(coordinate, new Tile(coordinate, new Rook("black")));
        }
        if (BLACK_KNIGHT_SYMBOL.equals(piece)) {
            tiles.put(coordinate, new Tile(coordinate, new Knight("black")));
        }
        if (BLACK_PAWN_SYMBOL.equals(piece) && (i + 1) == 2) {
            tiles.put(coordinate, new Tile(coordinate, new Pawn(false, "black")));
        }
        if (BLACK_PAWN_SYMBOL.equals(piece) && (i + 1) != 2) {
            tiles.put(coordinate, new Tile(coordinate, new Pawn(true, "black")));
        }
    }
}
