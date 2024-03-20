package chess.domain;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private static final Map<Position, Piece> board = new HashMap<>();

    static {
        createInitialBoard();
    }

    public static void createInitialBoard() {
        createBlankBoard();
        initWhitePieces();
        initBlackPieces();
    }

    private static void createBlankBoard() {
        for (int x = 1; x <= 8; x++) {
            initLine(x);
        }
    }

    private static void initLine(int x) {
        for (int y = 1; y <= 8; y++) {
            Position position = new Position(x, y);
            board.put(position, new Blank(position));
        }
    }

    private static void initBlackPieces() {
        board.replace(new Position(1, 8), new Rook(new Position(1, 8), Color.BLACK));
        board.replace(new Position(2, 8), new Knight(new Position(2, 8), Color.BLACK));
        board.replace(new Position(3, 8), new Bishop(new Position(3, 8), Color.BLACK));
        board.replace(new Position(4, 8), new Queen(new Position(4, 8), Color.BLACK));
        board.replace(new Position(5, 8), new King(new Position(5, 8), Color.BLACK));
        board.replace(new Position(6, 8), new Bishop(new Position(6, 8), Color.BLACK));
        board.replace(new Position(7, 8), new Knight(new Position(7, 8), Color.BLACK));
        board.replace(new Position(8, 8), new Rook(new Position(8, 8), Color.BLACK));
        for (int x = 1; x <= 8; x++) {
            board.replace(new Position(x, 7), new BlackFirstPawn(new Position(x, 7)));
        }
    }

    private static void initWhitePieces() {
        board.replace(new Position(1, 1), new Rook(new Position(1, 1), Color.WHITE));
        board.replace(new Position(2, 1), new Knight(new Position(2, 1), Color.WHITE));
        board.replace(new Position(3, 1), new Bishop(new Position(3, 1), Color.WHITE));
        board.replace(new Position(4, 1), new Queen(new Position(4, 1), Color.WHITE));
        board.replace(new Position(5, 1), new King(new Position(5, 1), Color.WHITE));
        board.replace(new Position(6, 1), new Bishop(new Position(6, 1), Color.WHITE));
        board.replace(new Position(7, 1), new Knight(new Position(7, 1), Color.WHITE));
        board.replace(new Position(8, 1), new Rook(new Position(8, 1), Color.WHITE));
        for (int x = 1; x <= 8; x++) {
            board.replace(new Position(x, 2), new WhiteFirstPawn(new Position(x, 2)));
        }
    }

    public Map<Position, Piece> getInitialBoard() {
        return new HashMap<>(board);
    }
}
