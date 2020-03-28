package chess.domain.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import chess.domain.command.Command;
import chess.domain.exception.InvalidMovementException;
import chess.domain.piece.GamePiece;
import chess.domain.player.Player;
import chess.domain.score.Score;

public class Board {

    private static final int NEXT = 1;

    private final Map<Position, GamePiece> board;
    private final Status status;

    private Board(Map<Position, GamePiece> board, Status status) {
        this.board = Collections.unmodifiableMap(board);
        this.status = status;
    }

    public static Board createEmpty() {
        return new Board(createEmptyMap(), Status.READY_STATUS);
    }

    private static Map<Position, GamePiece> createEmptyMap() {
        return Position.list()
                .stream()
                .collect(Collectors.toMap(Function.identity(), position -> GamePiece.EMPTY));
    }

    public Board initialize() {
        Map<Position, GamePiece> initialBoard = createEmptyMap();
        for (GamePiece piece : GamePiece.list()) {
            placePiecesOnInitialPositions(initialBoard, piece);
        }

        return new Board(initialBoard, Status.INITIAL_STATUS);
    }

    private void placePiecesOnInitialPositions(Map<Position, GamePiece> board, GamePiece piece) {
        for (Position position : piece.getInitialPositions()) {
            board.put(position, piece);
        }
    }

    protected static Board from(Map<Position, GamePiece> board, Status status) {
        return new Board(board, status);
    }

    public Board move(Position source, Position target) {
        if (status.isNotProcessing()) {
            throw new UnsupportedOperationException();
        }
        // TODO: 2020/03/25 리팩토링 확인
        Map<Position, GamePiece> board = new HashMap<>(this.board);
        GamePiece sourcePiece = board.get(source);
        GamePiece targetPiece = board.get(target);

        validateSourcePiece(sourcePiece);

        boolean isKill = !targetPiece.equals(GamePiece.EMPTY) && targetPiece.isEnemy(sourcePiece);

        List<Position> path;
        if (isKill) {
            if (status.isWhiteTurn()) {
                path = sourcePiece.searchKillPath(source, target);
            } else {
                path = backWard(sourcePiece.searchKillPath(source.opposite(), target.opposite()));
            }
        } else {
            if (status.isWhiteTurn()) {
                path = sourcePiece.searchMovePath(source, target);
            } else {
                path = backWard(sourcePiece.searchMovePath(source.opposite(), target.opposite()));
            }
        }

        for (Position position : path) {
            validateMovable(board.get(position));
        }

        board.put(target, sourcePiece);
        board.put(source, GamePiece.EMPTY);

        if (targetPiece.isKing()) {
            Status nextStatus = status.nextTurn();
            return new Board(board, nextStatus.finish());
        }

        return new Board(board, status.nextTurn());
    }

    private List<Position> backWard(List<Position> path) {
        return path.stream()
                .map(Position::opposite)
                .collect(Collectors.toList());
    }

    private void validateSourcePiece(GamePiece sourcePiece) {
        if (sourcePiece.equals(GamePiece.EMPTY)) {
            throw new InvalidMovementException("기물이 존재하지 않습니다.");
        }
        if (status.isWhiteTurn() && sourcePiece.is(Player.BLACK)) {
            throw new InvalidMovementException("해당 플레이어의 턴이 아닙니다.");
        }
        if (status.isBlackTurn() && sourcePiece.is(Player.WHITE)) {
            throw new InvalidMovementException("해당 플레이어의 턴이 아닙니다.");
        }
    }

    private void validateMovable(GamePiece obstacle) {
        if (obstacle != GamePiece.EMPTY) {
            throw new InvalidMovementException("경로에 기물이 존재합니다.");
        }
    }

    public Map<Player, Score> calculateScore() {
        Map<Player, Score> scores = new HashMap<>();
        List<GamePiece> gamePieces = new ArrayList<>(board.values());

        Map<GamePiece, Integer> gameWhitePiecesCount = getGamePieceCount(gamePieces, Player.WHITE);
        Map<GamePiece, Integer> gameBlackPiecesCount = getGamePieceCount(gamePieces, Player.BLACK);

        int sameFileWhitePawnCount = getSameColumnPawnCount(Player.WHITE);
        int sameFileBlackPawnCount = getSameColumnPawnCount(Player.BLACK);

        scores.put(Player.WHITE, Score.of(gameWhitePiecesCount, sameFileWhitePawnCount));
        scores.put(Player.BLACK, Score.of(gameBlackPiecesCount, sameFileBlackPawnCount));

        return scores;
    }

    private Map<GamePiece, Integer> getGamePieceCount(List<GamePiece> gamePieces, Player player) {
        return gamePieces.stream()
                .distinct()
                .filter(gamePiece -> gamePiece != GamePiece.EMPTY)
                .filter(gamePiece -> gamePiece.is(player))
                .collect(Collectors.toMap(gamePiece -> gamePiece, gamePiece -> Collections.frequency(gamePieces, gamePiece)));
    }

    private int getSameColumnPawnCount(Player player) {
        Map<Integer, Integer> sameColumnPawnCount = new HashMap<>();
        for (int i = 0; i < Column.values().length; i++) {
            sameColumnPawnCount.put(i, 0);
        }
        List<GamePiece> gamePieces = new ArrayList<>(getBoard().values());

        int rowLength = Row.values().length;
        for (int i = 0; i < gamePieces.size(); i++) {
            GamePiece gamePiece = gamePieces.get(i);
            if (gamePiece.isPawn() && gamePiece.is(player)) {
                sameColumnPawnCount.computeIfPresent(i % rowLength, (key, value) -> value + 1);
            }
        }

        return sameColumnPawnCount.values()
                .stream()
                .filter(count -> count >= 2)
                .reduce(0, Integer::sum);
    }

    public boolean isNotFinished() {
        return status.isNotFinished();
    }

    public List<Line> getRows() {
        List<Line> rows = new ArrayList<>();
        List<GamePiece> gamePieces = new ArrayList<>(getBoard().values());

        int columnLength = Column.values().length;
        for (int rowNumber = 0; rowNumber < Row.values().length; rowNumber++) {
            Line row = new Line(gamePieces.subList(rowNumber * columnLength, (rowNumber + NEXT) * columnLength));
            rows.add(row);
        }

        return rows;
    }

    public Map<Position, GamePiece> getBoard() {
        return Collections.unmodifiableMap(new TreeMap<>(board));
    }
}
