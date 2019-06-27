package chess.model.boardcreatestrategy;

import chess.model.board.Tile;
import chess.model.piece.*;

import java.util.HashMap;
import java.util.Map;

import static chess.model.ChessGame.BLACK_TEAM_COLOR;
import static chess.model.ChessGame.WHITE_TEAM_COLOR;

public class NewGameCreateStrategy implements CreateStrategy {
    private static String FIRST_ROW = "1";
    private static String SECOND_ROW = "2";
    private static String THIRD_ROW = "3";
    private static String FOURTH_ROW = "4";
    private static String FIFTH_ROW = "5";
    private static String SIXTH_ROW = "6";
    private static String SEVENTH_ROW = "7";
    private static String EIGTH_ROW = "8";
    
    @Override
    public Map<String, Tile> create() {
        Map<String, Tile> tiles = new HashMap<>();
        drawInitialBoard(tiles);

        return tiles;
    }

    private void drawInitialBoard(Map<String, Tile> tiles) {
        for (int row = 1; row <= 8; row++) {
            drawWhiteOfficersInBoard(tiles, row);
            drawBlackOfficersInBoard(tiles, row);
            drawWhitePawnsInBoard(tiles, row);
            drawBlackPawnsInBoard(tiles, row);
            drawEmptyPiecesInBoard(tiles, row);
        }
    }

    private void drawEmptyPiecesInBoard(Map<String, Tile> tiles, int row) {
        if (row == 3 || row == 4 || row == 5 || row == 6) {
            for (int column = 1; column <= 8; column++) {
                String coordinate = String.valueOf(column).concat(String.valueOf(row));
                tiles.put(coordinate, new Tile(coordinate, new Empty()));
            }
        }
    }

    private void drawBlackPawnsInBoard(Map<String, Tile> tiles, int row) {
        if (row == 7) {
            drawBlackPawnsInEveryColumn(tiles, row);
        }
    }

    private void drawBlackPawnsInEveryColumn(Map<String, Tile> tiles, int row) {
        for (int column = 1; column <= 8; column++) {
            String coordinate = String.valueOf(column).concat(String.valueOf(row));
            tiles.put(coordinate, new Tile(coordinate, new Pawn(true, BLACK_TEAM_COLOR)));
        }
    }

    private void drawWhitePawnsInBoard(Map<String, Tile> tiles, int row) {
        if (row == 2) {
            drawWhitePawnsInEveryColumn(tiles, row);
        }
    }

    private void drawWhitePawnsInEveryColumn(Map<String, Tile> tiles, int row) {
        for (int column = 1; column <= 8; column++) {
            String coordinate = String.valueOf(column).concat(String.valueOf(row));
            tiles.put(coordinate, new Tile(coordinate, new Pawn(true, WHITE_TEAM_COLOR)));
        }
    }

    private void drawBlackOfficersInBoard(Map<String, Tile> tiles, int row) {
        if (row == 8) {
            String coordinateY = String.valueOf(row);

            tiles.put(FIRST_ROW.concat(coordinateY), new Tile(FIRST_ROW.concat(coordinateY), new Rook(BLACK_TEAM_COLOR)));
            tiles.put(SECOND_ROW.concat(coordinateY), new Tile(SECOND_ROW.concat(coordinateY), new Knight(BLACK_TEAM_COLOR)));
            tiles.put(THIRD_ROW.concat(coordinateY), new Tile(THIRD_ROW.concat(coordinateY), new Bishop(BLACK_TEAM_COLOR)));
            tiles.put(FOURTH_ROW.concat(coordinateY), new Tile(FOURTH_ROW.concat(coordinateY), new Queen(BLACK_TEAM_COLOR)));
            tiles.put(FIFTH_ROW.concat(coordinateY), new Tile(FIFTH_ROW.concat(coordinateY), new King(BLACK_TEAM_COLOR)));
            tiles.put(SIXTH_ROW.concat(coordinateY), new Tile(SIXTH_ROW.concat(coordinateY), new Bishop(BLACK_TEAM_COLOR)));
            tiles.put(SEVENTH_ROW.concat(coordinateY), new Tile(SEVENTH_ROW.concat(coordinateY), new Knight(BLACK_TEAM_COLOR)));
            tiles.put(EIGTH_ROW.concat(coordinateY), new Tile(EIGTH_ROW.concat(coordinateY), new Rook(BLACK_TEAM_COLOR)));
        }
    }

    private void drawWhiteOfficersInBoard(Map<String, Tile> tiles, int row) {
        if (row == 1) {
            String coordinateY = String.valueOf(row);

            tiles.put(FIRST_ROW.concat(coordinateY), new Tile(FIRST_ROW.concat(coordinateY), new Rook(WHITE_TEAM_COLOR)));
            tiles.put(SECOND_ROW.concat(coordinateY), new Tile(SECOND_ROW.concat(coordinateY), new Knight(WHITE_TEAM_COLOR)));
            tiles.put(THIRD_ROW.concat(coordinateY), new Tile(THIRD_ROW.concat(coordinateY), new Bishop(WHITE_TEAM_COLOR)));
            tiles.put(FOURTH_ROW.concat(coordinateY), new Tile(FOURTH_ROW.concat(coordinateY), new Queen(WHITE_TEAM_COLOR)));
            tiles.put(FIFTH_ROW.concat(coordinateY), new Tile(FIFTH_ROW.concat(coordinateY), new King(WHITE_TEAM_COLOR)));
            tiles.put(SIXTH_ROW.concat(coordinateY), new Tile(SIXTH_ROW.concat(coordinateY), new Bishop(WHITE_TEAM_COLOR)));
            tiles.put(SEVENTH_ROW.concat(coordinateY), new Tile(SEVENTH_ROW.concat(coordinateY), new Knight(WHITE_TEAM_COLOR)));
            tiles.put(EIGTH_ROW.concat(coordinateY), new Tile(EIGTH_ROW.concat(coordinateY), new Rook(WHITE_TEAM_COLOR)));
        }
    }
}
