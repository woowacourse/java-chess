package chess.domain.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import chess.domain.exception.InvalidMovementException;
import chess.domain.piece.GamePiece;
import chess.domain.player.Player;
import chess.domain.score.Score;

public class Board {

    private static final int INIT_TURN = 0;
    public static Board EMPTY = new Board(createEmptyBoard(), INIT_TURN);

    private final Map<Position, GamePiece> board;
    private final int turn;

    private Board(Map<Position, GamePiece> board, int turn) {
        this.board = Collections.unmodifiableMap(board);
        this.turn = turn;
    }

    public static Board from(Map<Position, GamePiece> board, int turn) {
        return new Board(board, turn);
    }

    public static Board createInitial() {
        return new Board(initializePositionsOfPieces(), INIT_TURN);
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
        GamePiece targetPiece = board.get(target);

        validateSourcePiece(sourcePiece);

        boolean isKill = !targetPiece.equals(GamePiece.EMPTY) && targetPiece.isEnemy(sourcePiece);

        List<Position> path;
        if (isWhiteTurn()) {
            path = sourcePiece.searchPath(source, target, isKill);
        } else {
            path = backWard(sourcePiece.searchPath(source.opposite(), target.opposite(), isKill));
        }

        for (Position position : path) {
            validateMovable(board.get(position));
        }

        board.put(target, sourcePiece);
        board.put(source, GamePiece.EMPTY);

        return from(board, turn + 1);
    }

    private List<Position> backWard(List<Position> path) {
        return path.stream()
                .map(Position::opposite)
                .collect(Collectors.toList());
    }

    private boolean isWhiteTurn() {
        return turn % 2 == 0;
    }

    private void validateSourcePiece(GamePiece sourcePiece) {
        if (sourcePiece.equals(GamePiece.EMPTY)) {
            throw new InvalidMovementException();
        }
        if (isWhiteTurn() && !sourcePiece.isWhite()) {
            throw new InvalidMovementException();
        }
        if (!isWhiteTurn() && sourcePiece.isWhite()) {
            throw new InvalidMovementException();
        }
    }

    private void validateMovable(GamePiece obstacle) {
        if (obstacle != GamePiece.EMPTY) {
            throw new InvalidMovementException();
        }
    }

    public Map<Player, Score> calculateScore() {
        Map<Player, Score> scores = new HashMap<>();
        List<GamePiece> gamePieces = new ArrayList<>(board.values());

        Map<GamePiece, Integer> gameWhitePiecesCount = getGamePieceCount(gamePieces, white -> white);
        Map<GamePiece, Integer> gameBlackPiecesCount = getGamePieceCount(gamePieces, white -> !white);

        int sameFileWhitePawnCount = getSameFilePawnCount(white -> white);
        int sameFileBlackPawnCount = getSameFilePawnCount(white -> !white);

        scores.put(Player.WHITE, Score.of(gameWhitePiecesCount, sameFileWhitePawnCount));
        scores.put(Player.BLACK, Score.of(gameBlackPiecesCount, sameFileBlackPawnCount));

        return scores;
    }

    private Map<GamePiece, Integer> getGamePieceCount(List<GamePiece> gamePieces, Predicate<Boolean> player) {
        return gamePieces.stream()
                .distinct()
                .filter(gamePiece -> gamePiece != GamePiece.EMPTY)
                .filter(gamePiece -> player.test(gamePiece.isWhite()))
                .collect(Collectors.toMap(gamePiece -> gamePiece, gamePiece -> Collections.frequency(gamePieces, gamePiece)));
    }

    private int getSameFilePawnCount(Predicate<Boolean> player) {
        int sameFilePawnCount = 0;
        for (File file : File.values()) {
            int count = 0;
            for (Rank rank : Rank.values()) {
                GamePiece gamePiece = board.get(Position.of(file, rank));
                if (gamePiece != GamePiece.EMPTY && gamePiece.isPawn() && player.test(gamePiece.isWhite())) {
                    count++;
                }
            }
            if (count >= 2) {
                sameFilePawnCount += count;
            }
        }
        return sameFilePawnCount;
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
