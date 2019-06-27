package chess.model;

import chess.model.piece.*;

import java.util.*;

public class ContinueGameCreateStrategy implements CreateStrategy {
    private static String BLACK_KING_SYMBOL = "K";
    private static String BLACK_QUEEN_SYMBOL = "Q";
    private static String BLACK_BISHOP_SYMBOL = "B";
    private static String BLACK_ROOK_SYMBOL = "R";
    private static String BLACK_KNIGHT_SYMBOL = "N";
    private static String BLACK_PAWN_SYMBOL = "P";
    private static String EMPTY_SYMBOL = "#";
    private static String WHITE_KING_SYMBOL = "k";
    private static String WHITE_QUEEN_SYMBOL = "q";
    private static String WHITE_BISHOP_SYMBOL = "b";
    private static String WHITE_ROOK_SYMBOL = "r";
    private static String WHITE_KNIGHT_SYMBOL = "n";
    private static String WHITE_PAWN_SYMBOL = "p";

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
        if (piece.equals("#")) {
            tiles.put(coordinate, new Tile(coordinate, new Empty()));
        }
    }

    private void drawWhitePieces(int i, Map<String, Tile> tiles, String piece, String coordinate) {
        if (piece.equals("k")) {
            tiles.put(coordinate, new Tile(coordinate, new King("white")));
        }
        if (piece.equals("q")) {
            tiles.put(coordinate, new Tile(coordinate, new Queen("white")));
        }
        if (piece.equals("b")) {
            tiles.put(coordinate, new Tile(coordinate, new Bishop("white")));
        }
        if (piece.equals("r")) {
            tiles.put(coordinate, new Tile(coordinate, new Rook("white")));
        }
        if (piece.equals("n")) {
            tiles.put(coordinate, new Tile(coordinate, new Knight("white")));
        }
        if (piece.equals("p") && (i + 1) == 7) {
            tiles.put(coordinate, new Tile(coordinate, new Pawn(false, "white")));
        }
        if (piece.equals("p") && (i + 1) != 7) {
            tiles.put(coordinate, new Tile(coordinate, new Pawn(true, "white")));
        }
    }

    private void drawBlackPieces(int i, Map<String, Tile> tiles, String piece, String coordinate) {
        if (piece.equals("K")) {
            tiles.put(coordinate, new Tile(coordinate, new King("black")));
        }
        if (piece.equals("Q")) {
            tiles.put(coordinate, new Tile(coordinate, new Queen("black")));
        }
        if (piece.equals("B")) {
            tiles.put(coordinate, new Tile(coordinate, new Bishop("black")));
        }
        if (piece.equals("R")) {
            tiles.put(coordinate, new Tile(coordinate, new Rook("black")));
        }
        if (piece.equals("N")) {
            tiles.put(coordinate, new Tile(coordinate, new Knight("black")));
        }
        if (piece.equals("P") && (i + 1) == 2) {
            tiles.put(coordinate, new Tile(coordinate, new Pawn(false, "black")));
        }
        if (piece.equals("P") && (i + 1) != 2) {
            tiles.put(coordinate, new Tile(coordinate, new Pawn(true, "black")));
        }
    }
}
