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

    private final static int BOARD_SIZE = 8;
    private final static int DEFAULT_POSITION = 1;
    private final static char EMPTY = '.';

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
        return generateBoard(INITIAL_BOARD);
    }

    public static Board generateBoard(char[][] board) {
        List<Piece> pieces = new ArrayList<>();
        for (int y = 0; y < BOARD_SIZE; y++) {
            generateColumn(board[y], pieces, y);
        }
        return Board.of(pieces);
    }

    private static void generateColumn(char[] chars, List<Piece> pieces, int y) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            char pieceLetter = chars[x];
            addIfNotEmpty(pieces, y, x, pieceLetter);
        }
    }

    private static void addIfNotEmpty(List<Piece> pieces, int y, int x, char pieceLetter) {
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
        switch (Character.toLowerCase(pieceLetter)) {
            case 'r':
                return Rook.of(convertedLocation, team);
            case 'n':
                return Knight.of(convertedLocation, team);
            case 'b':
                return Bishop.of(convertedLocation, team);
            case 'q':
                return Queen.of(convertedLocation, team);
            case 'k':
                return King.of(convertedLocation, team);
            default:
                return Pawn.of(convertedLocation, team);
        }
    }

    private static Location convertLocation(int x, int y) {
        int transX = x + DEFAULT_POSITION;
        int transY = BOARD_SIZE - y;
        return Location.of(transX, transY);
    }

    private static Team findOutTeam(char pieceLetter) {
        if (Character.isLowerCase(pieceLetter)) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }
}
