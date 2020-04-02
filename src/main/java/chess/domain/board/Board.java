package chess.domain.board;

import chess.domain.exception.InvalidMovementException;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.GamePiece;
import chess.domain.player.PlayerColor;
import chess.domain.result.ChessResult;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

public class Board {

    private final Map<Position, GamePiece> board;
    private final Status status;

    private Board(Map<Position, GamePiece> board, Status status) {
        this.board = Collections.unmodifiableMap(new TreeMap<>(board));
        this.status = status;
    }

    public static Board from(Map<Position, GamePiece> board, Status status) {
        return new Board(board, status);
    }

    public static Board createEmpty() {
        return new Board(createEmptyMap(), Status.readyStatus());
    }

    private static Map<Position, GamePiece> createEmptyMap() {
        return Position.list()
                .stream()
                .collect(Collectors.toMap(Function.identity(), position -> EmptyPiece.getInstance()));
    }

    public Board placeInitialPieces() {
        Map<Position, GamePiece> initialBoard = createEmptyMap();
        for (GamePiece piece : ChessPiece.list()) {
            placePiecesOnInitialPositions(initialBoard, piece);
        }

        return new Board(initialBoard, Status.initialStatus());
    }

    private void placePiecesOnInitialPositions(Map<Position, GamePiece> board, GamePiece piece) {
        for (Position position : piece.getOriginalPositions()) {
            board.put(position, piece);
        }
    }

    public Board move(Position source, Position target) {
        if (status.isNotProcessing()) {
            throw new UnsupportedOperationException("먼저 게임을 실행해야합니다.");
        }

        Map<Position, GamePiece> board = new HashMap<>(this.board);
        GamePiece sourcePiece = board.get(source);
        GamePiece targetPiece = board.get(target);

        validateSourcePiece(sourcePiece, targetPiece);

        if (!sourcePiece.canMoveTo(this, source, target)) {
            return this;
        }

        board.put(target, sourcePiece);
        board.put(source, EmptyPiece.getInstance());

        Status nextStatus = status.nextTurn();

        if (ChessPiece.isKing(targetPiece)) {
            return new Board(board, nextStatus.finish());
        }
        return new Board(board, nextStatus);
    }

    private void validateSourcePiece(GamePiece sourcePiece, GamePiece targetPiece) {
        if (sourcePiece.equals(EmptyPiece.getInstance())) {
            throw new InvalidMovementException("기물이 존재하지 않습니다.");
        }
        if (status.isWhiteTurn() && sourcePiece.is(PlayerColor.BLACK)) {
            throw new InvalidMovementException("해당 플레이어의 턴이 아닙니다.");
        }
        if (status.isBlackTurn() && sourcePiece.is(PlayerColor.WHITE)) {
            throw new InvalidMovementException("해당 플레이어의 턴이 아닙니다.");
        }
        if (sourcePiece.isSameTeam(targetPiece)) {
            throw new InvalidMovementException("자신의 말은 잡을 수 없습니다.");
        }
    }

    public boolean isNotFinished() {
        return status.isNotFinished();
    }

    public boolean isNotEmpty(Position position) {
        return !board.get(position).equals(EmptyPiece.getInstance());
    }

    public ChessResult calculateResult() {
        return ChessResult.from(this);
    }

    public List<Line> getRows() {
        return getLine(Position::getRow);
    }

    public List<Line> getColumns() {
        return getLine(Position::getColumn);
    }

    private <T> List<Line> getLine(Function<Position, T> filter) {
        Map<T, Line> lines = board.entrySet()
                .stream()
                .collect(groupingBy(entry -> filter.apply(entry.getKey()),       // key
                        () -> new TreeMap<>(Collections.reverseOrder()),    // 리턴타입은 reversed TreeMap
                        mapping(Map.Entry::getValue, collectingAndThen(toList(), Line::new))));// value(Line)

        return new ArrayList<>(lines.values());
    }

    public Map<GamePiece, Integer> countEachGamePiece(PlayerColor playerColor) {
        List<GamePiece> gamePieces = new ArrayList<>(board.values());
        return gamePieces.stream()
                .distinct()
                .filter(gamePiece -> gamePiece != EmptyPiece.getInstance())
                .filter(gamePiece -> gamePiece.is(playerColor))
                .collect(Collectors.toMap(gamePiece -> gamePiece, gamePiece -> Collections.frequency(gamePieces, gamePiece)));
    }

    public Map<Position, GamePiece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
