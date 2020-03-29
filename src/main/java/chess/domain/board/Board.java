package chess.domain.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import chess.domain.exception.InvalidMovementException;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.GamePiece;
import chess.domain.player.PlayerColor;
import chess.domain.result.ChessResult;

public class Board {

    private static final int NEXT = 1;

    private final Map<Position, GamePiece> board;
    private final Status status;

    private Board(Map<Position, GamePiece> board, Status status) {
        this.board = Collections.unmodifiableMap(new TreeMap<>(board));
        this.status = status;
    }

    public static Board createEmpty() {
        return new Board(createEmptyMap(), Status.READY_STATUS);
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

        return new Board(initialBoard, Status.INITIAL_STATUS);
    }

    private void placePiecesOnInitialPositions(Map<Position, GamePiece> board, GamePiece piece) {
        for (Position position : piece.getOriginalPositions()) {
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
        Map<Position, GamePiece> board = new HashMap<>(this.board);
        GamePiece sourcePiece = board.get(source);
        GamePiece targetPiece = board.get(target);

        validateSourcePiece(sourcePiece);
        sourcePiece.validateMoveTo(board, source, target);

        board.put(target, sourcePiece);
        board.put(source, EmptyPiece.getInstance());

        if (ChessPiece.isKing(targetPiece)) {
            Status nextStatus = status.nextTurn();
            return new Board(board, nextStatus.finish());
        }

        return new Board(board, status.nextTurn());
    }

    private void validateSourcePiece(GamePiece sourcePiece) {
        if (sourcePiece.equals(EmptyPiece.getInstance())) {
            throw new InvalidMovementException("기물이 존재하지 않습니다.");
        }
        if (status.isWhiteTurn() && sourcePiece.is(PlayerColor.BLACK)) {
            throw new InvalidMovementException("해당 플레이어의 턴이 아닙니다.");
        }
        if (status.isBlackTurn() && sourcePiece.is(PlayerColor.WHITE)) {
            throw new InvalidMovementException("해당 플레이어의 턴이 아닙니다.");
        }
    }

    public boolean isNotFinished() {
        return status.isNotFinished();
    }

    public ChessResult calculateResult() {
        return ChessResult.from(board);
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
        return Collections.unmodifiableMap(board);
    }
}
