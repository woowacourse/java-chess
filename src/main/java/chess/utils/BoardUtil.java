package chess.utils;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.team.Team;
import java.util.ArrayList;
import java.util.List;

public class BoardUtil {

    private static final int BOARD_SIZE = 8;
    private static final int DEFAULT_POSITION = 1;
    private static final char EMPTY = '.';

    private static final char[][] INITIAL_BOARD = {
        {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
        {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'}
    };

    private BoardUtil() {
    }

    public static Board generateInitialBoard() {
        return convertToBoard(INITIAL_BOARD);
    }

    public static Board convertToBoard(char[][] board) {
        List<Piece> pieces = new ArrayList<>();
        for (int y = 0; y < BOARD_SIZE; y++) {
            generateColumnPiece(board[y], pieces, y);
        }
        return Board.of(pieces);
    }

    private static void generateColumnPiece(char[] column, List<Piece> pieces, int y) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            char pieceLetter = column[x];
            addIfNotEmpty(pieceLetter, x, y, pieces);
        }
    }

    private static void addIfNotEmpty(char pieceLetter, int x, int y, List<Piece> pieces) {
        if (isNotEmpty(pieceLetter)) {
            pieces.add(generatePiece(pieceLetter, x, y));
        }
    }

    private static boolean isNotEmpty(char pieceLetter) {
        return pieceLetter != EMPTY;
    }

    private static Piece generatePiece(char pieceLetter, int x, int y) {
        Location convertedLocation = convertLocation(x, y);
        Team team = findOutTeam(pieceLetter);
        char lowerPieceLetter = Character.toLowerCase(pieceLetter);
        if (lowerPieceLetter == 'r') {
            return Rook.of(convertedLocation, team);
        }
        if (lowerPieceLetter == 'n') {
            return Knight.of(convertedLocation, team);
        }
        if (lowerPieceLetter == 'b') {
            return Bishop.of(convertedLocation, team);
        }
        if (lowerPieceLetter == 'q') {
            return Queen.of(convertedLocation, team);
        }
        if (lowerPieceLetter == 'k') {
            return King.of(convertedLocation, team);
        }
        return Pawn.of(convertedLocation, team);
    }

    private static Location convertLocation(int x, int y) {
        int transX = x + DEFAULT_POSITION;
        int transY = 7 - y + DEFAULT_POSITION;
        return Location.of(transX, transY);
    }

    private static Team findOutTeam(char pieceLetter) {
        if (Character.isLowerCase(pieceLetter)) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }

    public static char[][] generateViewBoard(Board board) {
        char[][] viewBoard = generateEmptyBoard();
        List<Piece> pieces = board.toList();
        for (Piece piece : pieces) {
            int x = piece.getX();
            int y = piece.getY();
            viewBoard[7 - y + DEFAULT_POSITION][x - DEFAULT_POSITION] = generatePieceLetter(piece);
        }
        return viewBoard;
    }

    private static char[][] generateEmptyBoard() {
        char[][] viewBoard = new char[BOARD_SIZE][BOARD_SIZE];
        for (int y = 0; y < BOARD_SIZE; y++) {
            generateEmptyColumn(viewBoard, y);
        }
        return viewBoard;
    }

    private static void generateEmptyColumn(char[][] viewBoard, int y) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            viewBoard[y][x] = EMPTY;
        }
    }

    private static char generatePieceLetter(Piece piece) {
        char value = piece.pieceScore().value();
        if (piece.isSameTeam(Team.BLACK)) {
            value = Character.toUpperCase(value);
        }
        return value;
    }
}
