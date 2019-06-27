package chess.domain.board;

import chess.domain.Team;
import chess.domain.pieces.*;
import chess.domain.position.Position;
import chess.domain.position.Positions;

import java.util.HashMap;
import java.util.Map;

public class BoardCreator {
    private static final int MIN_BOARD_COORDINATE = 1;
    private static final int MAX_BOARD_COORDINATE = 8;

    public static Map<Position, Piece> initialize() {
        Map<Position, Piece> boardState = new HashMap<>();
        initializeBlank(boardState);
        initializeRook(boardState);
        initializeKnight(boardState);
        initializeBishop(boardState);
        initializeQueen(boardState);
        initializeKing(boardState);
        initializePawn(boardState);

        return boardState;
    }

    public static void initializeBlank(Map<Position, Piece> boardState) {
        for (int i = MIN_BOARD_COORDINATE; i <= MAX_BOARD_COORDINATE; i++) {
            for (int j = MIN_BOARD_COORDINATE; j <= MAX_BOARD_COORDINATE; j++) {
                Position position = Positions.matchWith(i, j);
                boardState.put(position, new Blank(position, Team.BLANK));
            }
        }
    }

    private static void initializeRook(Map<Position, Piece> boardState) {
        Position position;
        position = Positions.matchWith(1, 1);
        boardState.put(position, new Rook(position, Team.WHITE));
        position = Positions.matchWith(8, 1);
        boardState.put(position, new Rook(position, Team.WHITE));
        position = Positions.matchWith(1, 8);
        boardState.put(position, new Rook(position, Team.BLACK));
        position = Positions.matchWith(8, 8);
        boardState.put(position, new Rook(position, Team.BLACK));
    }

    private static void initializeKnight(Map<Position, Piece> boardState) {
        Position position;
        position = Positions.matchWith(2, 1);
        boardState.put(position, new Knight(position, Team.WHITE));
        position = Positions.matchWith(7, 1);
        boardState.put(position, new Knight(position, Team.WHITE));
        position = Positions.matchWith(2, 8);
        boardState.put(position, new Knight(position, Team.BLACK));
        position = Positions.matchWith(7, 8);
        boardState.put(position, new Knight(position, Team.BLACK));
    }

    private static void initializeBishop(Map<Position, Piece> boardState) {
        Position position;
        position = Positions.matchWith(3, 1);
        boardState.put(position, new Bishop(position, Team.WHITE));
        position = Positions.matchWith(6, 1);
        boardState.put(position, new Bishop(position, Team.WHITE));
        position = Positions.matchWith(3, 8);
        boardState.put(position, new Bishop(position, Team.BLACK));
        position = Positions.matchWith(6, 8);
        boardState.put(position, new Bishop(position, Team.BLACK));
    }

    private static void initializeQueen(Map<Position, Piece> boardState) {
        Position position;
        position = Positions.matchWith(4, 1);
        boardState.put(position, new Queen(position, Team.WHITE));
        position = Positions.matchWith(4, 8);
        boardState.put(position, new Queen(position, Team.BLACK));
    }

    private static void initializeKing(Map<Position, Piece> boardState) {
        Position position;
        position = Positions.matchWith(5, 1);
        boardState.put(position, new King(position, Team.WHITE));
        position = Positions.matchWith(5, 8);
        boardState.put(position, new King(position, Team.BLACK));
    }

    private static void initializePawn(Map<Position, Piece> boardState) {
        Position position;
        for (int i = MIN_BOARD_COORDINATE; i <= MAX_BOARD_COORDINATE; i++) {
            position = Positions.matchWith(i, 2);
            boardState.put(position, new Pawn(position, Team.WHITE));
            position = Positions.matchWith(i, 7);
            boardState.put(position, new Pawn(position, Team.BLACK));
        }
    }
}
