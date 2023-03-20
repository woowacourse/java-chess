package chess.domain.board;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Board {

    private final Map<Position, Piece> board;
    private Color turn;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
        this.turn = Color.WHITE;
    }

    public static Board initialize() {
        final Map<Position, Piece> result = new HashMap<>();
        result.putAll(initializePiece(Color.WHITE, Rank.ONE));
        result.putAll(initializePawn(Color.WHITE, Rank.TWO));
        result.putAll(initializeEmptyPiece(List.of(Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX)));
        result.putAll(initializePawn(Color.BLACK, Rank.SEVEN));
        result.putAll(initializePiece(Color.BLACK, Rank.EIGHT));
        return new Board(result);
    }

    private static Map<Position, Piece> initializePiece(final Color color, final Rank rank) {
        final List<Piece> pieces = List.of(
                Rook.from(color), Knight.from(color), Bishop.from(color), Queen.from(color),
                King.from(color), Bishop.from(color), Knight.from(color), Rook.from(color)
        );
        final List<File> files = Arrays.stream(File.values()).collect(toList());

        return IntStream.range(0, pieces.size())
                .boxed()
                .collect(toMap(index -> Position.of(files.get(index), rank), pieces::get));
    }

    private static Map<Position, Piece> initializePawn(final Color color, final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, rank))
                .collect(toMap(Function.identity(), ignore -> Pawn.from(color)));
    }

    private static Map<Position, Piece> initializeEmptyPiece(final List<Rank> ranks) {
        return ranks.stream()
                .flatMap(rank -> Arrays.stream(File.values()).map(file -> Position.of(file, rank)))
                .collect(toMap(Function.identity(), ignore -> Empty.get()));
    }

    public void move(final String source, final String target) {
        final Position sourcePosition = Position.from(source);
        final Position targetPosition = Position.from(target);
        final Piece piece = board.get(sourcePosition);

        validate(sourcePosition, targetPosition, piece);
        movePiece(sourcePosition, targetPosition, piece);
    }

    private void validate(final Position sourcePosition, final Position targetPosition, final Piece piece) {
        if (turn.isOpponent(piece.color())) {
            throw new IllegalArgumentException("상대방의 기물을 움직일 수 없습니다.");
        }
        if (isPieceExistsBetweenPositions(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("이동 경로에 기물이 존재합니다.");
        }
        if (piece.isUnmovable(sourcePosition, targetPosition, board.get(targetPosition))) {
            throw new IllegalArgumentException("올바르지 않은 이동 명령어입니다.");
        }
    }

    private boolean isPieceExistsBetweenPositions(final Position sourcePosition, final Position targetPosition) {
        return sourcePosition.between(targetPosition).stream()
                .anyMatch(this::isPieceExists);
    }

    private boolean isPieceExists(final Position position) {
        return !board.get(position).equals(Empty.get());
    }

    private void movePiece(final Position sourcePosition, final Position targetPosition, final Piece piece) {
        board.put(targetPosition, piece);
        board.put(sourcePosition, Empty.get());
        turn = turn.nextTurn();
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
