package chess.domain.board;

import chess.domain.exception.InvalidMovementException;
import chess.domain.piece.GamePiece;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Board {

    public static Board EMPTY = new Board(createEmptyBoard());
    private final Map<Position, GamePiece> board;

    private Board(Map<Position, GamePiece> board) {
        this.board = Collections.unmodifiableMap(board);
    }

    public static Board from(Map<Position, GamePiece> board) {
        return new Board(board);
    }

    public static Board createInitial() {
        return from(initializePositionsOfPieces());
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

    public Board move(Position source, Position target) {
        // TODO: 2020/03/25 리팩토링 확인
        Map<Position, GamePiece> board = new HashMap<>(this.board);
        GamePiece sourcePiece = board.get(source);

        validateSourcePiece(sourcePiece);

        List<Position> path = sourcePiece.searchPath(source, target);
        for (Position position : path) {
            validateMovable(board.get(position));
        }

        board.put(target, sourcePiece);
        board.put(source, GamePiece.EMPTY);

        return from(board);
    }

    private void validateSourcePiece(GamePiece sourcePiece) {
        if (sourcePiece.equals(GamePiece.EMPTY)) {
            throw new InvalidMovementException();
        }
    }

    private void validateMovable(GamePiece obstacle) {
        if (obstacle != GamePiece.EMPTY) {
            throw new InvalidMovementException();
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
        return Collections.unmodifiableMap(new TreeMap<>(board));
    }
}
