package chess.domain;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.TeamColor.BLACK;
import static chess.domain.TeamColor.WHITE;

public class Board {

    public static final int BOARD_SIZE = 8;
    private static final int FIRST_EMPTY_COLUMN = 2;
    private static final int LAST_EMPTY_COLUMN = 5;

    private final Map<Position, Piece> board;

    public Board() {
        board = new HashMap<>();
        init();
    }

    public Piece piece(Position position) {
        return board.get(position);
    }

    private void init() {
        initPieces(0, WHITE);
        initPawns(1, WHITE);

        initEmpty();

        initPawns(6, BLACK);
        initPieces(7, BLACK);
    }

    private void initPieces(int y, TeamColor teamColor) {
        board.put(Position.of(0, y), new Rook(teamColor));
        board.put(Position.of(1, y), new Knight(teamColor));
        board.put(Position.of(2, y), new Bishop(teamColor));
        board.put(Position.of(3, y), new Queen(teamColor));
        board.put(Position.of(4, y), new King(teamColor));
        board.put(Position.of(5, y), new Bishop(teamColor));
        board.put(Position.of(6, y), new Knight(teamColor));
        board.put(Position.of(7, y), new Rook(teamColor));
    }

    private void initPawns(int y, TeamColor teamColor) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            board.put(Position.of(x, y), new Pawn(teamColor));
        }
    }

    private void initEmpty() {
        for (int y = FIRST_EMPTY_COLUMN; y <= LAST_EMPTY_COLUMN; y++) {
            initEmptyRow(y);
        }
    }

    private void initEmptyRow(int y) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            board.put(Position.of(x, y), new Piece.Empty());
        }
    }
}
