package chess.domain.board;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import chess.domain.piece.GamePiece;

public class Board {

    private final Map<Position, GamePiece> board;

    private Board(Map<Position, GamePiece> board) {
        this.board = board;
    }

    public static Board createInitial() {
        Board board = new Board(placeWithEmptyPiece());
        board.initialize();
        return board;
    }

    private static Map<Position, GamePiece> placeWithEmptyPiece() {
        return Position.list()
                .stream()
                .collect(Collectors.toMap(Function.identity(), position -> GamePiece.EMPTY));
    }

    private void initialize() {
        for (GamePiece piece : GamePiece.list()) {
            placeChessPieces(board, piece);
        }
    }

    private static void placeChessPieces(Map<Position, GamePiece> board, GamePiece piece) {
        for (Position position : piece.getInitialPositions()) {
            board.put(position, piece);
        }
    }

    public Map<Position, GamePiece> getBoard() {
        return new TreeMap<>(board);
    }
}
