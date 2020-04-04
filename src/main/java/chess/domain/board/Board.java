package chess.domain.board;

import chess.domain.piece.ChessPiece;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.GamePiece;
import chess.domain.player.PlayerColor;
import chess.domain.result.ChessResult;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

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

        if (!isPieceCanMove(sourcePiece, targetPiece)) {
            return this;
        }

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

    private boolean isPieceCanMove(GamePiece sourcePiece, GamePiece targetPiece) {
        if (sourcePiece.equals(EmptyPiece.getInstance())) {
            return false;
        }
        if (status.isWhiteTurn() && sourcePiece.is(PlayerColor.BLACK)) {
            return false;
        }
        if (status.isBlackTurn() && sourcePiece.is(PlayerColor.WHITE)) {
            return false;
        }
        if (sourcePiece.is(targetPiece.getPlayerColor())) {
            return false;
        }
        return true;
    }

    public boolean isNotFinished() {
        return status.isNotFinished();
    }

    public boolean isFinished() {
        return !status.isNotFinished();
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

    private List<Line> getLine(Function<Position, Location> filter) {
        Map<Location, Line> lines = board.entrySet()
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

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return Objects.equals(board, board1.board) &&
                Objects.equals(status, board1.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, status);
    }
}
