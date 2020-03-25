package chess.domain.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import chess.domain.piece.GamePiece;

public class Board {

    private final Map<Position, GamePiece> board;

    private Board(Map<Position, GamePiece> board) {
        this.board = Collections.unmodifiableMap(board);
    }

    public static Board createInitial() {
        return new Board(initializePositionsOfPieces());
    }

    private static Map<Position, GamePiece> initializePositionsOfPieces() {
        Map<Position, GamePiece> emptyBoard = createEmptyBoard();
        for (GamePiece piece : GamePiece.list()) {
            placeChessPieces(emptyBoard, piece);
        }

        return emptyBoard;
    }

    private static Map<Position, GamePiece> createEmptyBoard() {
        return Position.list()
                .stream()
                .collect(Collectors.toMap(Function.identity(), position -> GamePiece.EMPTY));
    }

    private static void placeChessPieces(Map<Position, GamePiece> board, GamePiece piece) {
        for (Position position : piece.getInitialPositions()) {
            board.put(position, piece);
        }
    }

    public List<List<GamePiece>> gamePieces() {
        List<List<GamePiece>> gamePieces = new ArrayList<>();
        Iterator<GamePiece> iterator = getBoard().values().iterator();

        for (int i = 0; i < Rank.values().length; i++) {
            List<GamePiece> eachRank = new ArrayList<>();
            for (int j = 0; j < File.values().length; j++) {
                eachRank.add(iterator.next());
            }
            gamePieces.add(eachRank);
        }

        return Collections.unmodifiableList(gamePieces);
    }

    public Map<Position, GamePiece> getBoard() {
        return new TreeMap<>(board);
    }
}
