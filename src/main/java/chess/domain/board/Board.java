package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.normal.*;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.property.Color;
import chess.domain.piece.property.Kind;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public final class Board {

    private static final double STACKED_PAWN_SCORE = 0.5;
    private static final int BOTH_KINGS_ALIVE = 2;
    private static final int WINNER_COLOR_INDEX = 0;
    private static final int DOUBLE_PAWN = 2;

    private static final Map<Position, Piece> initialBoard = new HashMap<>();

    static {
        initializeBlackPieces();
        initializeBlackPawns();
        initializeWhitePawns();
        initializeWhitePieces();
    }

    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board createInitialBoard() {
        return new Board(new HashMap<>(initialBoard));
    }

    private static void initializeBlackPieces() {
        initialBoard.put(new Position(File.A, Rank.EIGHT), new Rook(Color.BLACK));
        initialBoard.put(new Position(File.B, Rank.EIGHT), new Knight(Color.BLACK));
        initialBoard.put(new Position(File.C, Rank.EIGHT), new Bishop(Color.BLACK));
        initialBoard.put(new Position(File.D, Rank.EIGHT), new Queen(Color.BLACK));
        initialBoard.put(new Position(File.E, Rank.EIGHT), new King(Color.BLACK));
        initialBoard.put(new Position(File.F, Rank.EIGHT), new Bishop(Color.BLACK));
        initialBoard.put(new Position(File.G, Rank.EIGHT), new Knight(Color.BLACK));
        initialBoard.put(new Position(File.H, Rank.EIGHT), new Rook(Color.BLACK));
    }

    private static void initializeBlackPawns() {
        initialBoard.put(new Position(File.A, Rank.SEVEN), new Pawn(Color.BLACK));
        initialBoard.put(new Position(File.B, Rank.SEVEN), new Pawn(Color.BLACK));
        initialBoard.put(new Position(File.C, Rank.SEVEN), new Pawn(Color.BLACK));
        initialBoard.put(new Position(File.D, Rank.SEVEN), new Pawn(Color.BLACK));
        initialBoard.put(new Position(File.E, Rank.SEVEN), new Pawn(Color.BLACK));
        initialBoard.put(new Position(File.F, Rank.SEVEN), new Pawn(Color.BLACK));
        initialBoard.put(new Position(File.G, Rank.SEVEN), new Pawn(Color.BLACK));
        initialBoard.put(new Position(File.H, Rank.SEVEN), new Pawn(Color.BLACK));
    }

    private static void initializeWhitePawns() {
        initialBoard.put(new Position(File.A, Rank.TWO), new Pawn(Color.WHITE));
        initialBoard.put(new Position(File.B, Rank.TWO), new Pawn(Color.WHITE));
        initialBoard.put(new Position(File.C, Rank.TWO), new Pawn(Color.WHITE));
        initialBoard.put(new Position(File.D, Rank.TWO), new Pawn(Color.WHITE));
        initialBoard.put(new Position(File.E, Rank.TWO), new Pawn(Color.WHITE));
        initialBoard.put(new Position(File.F, Rank.TWO), new Pawn(Color.WHITE));
        initialBoard.put(new Position(File.G, Rank.TWO), new Pawn(Color.WHITE));
        initialBoard.put(new Position(File.H, Rank.TWO), new Pawn(Color.WHITE));
    }

    private static void initializeWhitePieces() {
        initialBoard.put(new Position(File.A, Rank.ONE), new Rook(Color.WHITE));
        initialBoard.put(new Position(File.B, Rank.ONE), new Knight(Color.WHITE));
        initialBoard.put(new Position(File.C, Rank.ONE), new Bishop(Color.WHITE));
        initialBoard.put(new Position(File.D, Rank.ONE), new Queen(Color.WHITE));
        initialBoard.put(new Position(File.E, Rank.ONE), new King(Color.WHITE));
        initialBoard.put(new Position(File.F, Rank.ONE), new Bishop(Color.WHITE));
        initialBoard.put(new Position(File.G, Rank.ONE), new Knight(Color.WHITE));
        initialBoard.put(new Position(File.H, Rank.ONE), new Rook(Color.WHITE));
    }

    public void confirmMove(final Position source, final Position target, Color color) {
        validate(color, source, target);

        Set<Position> movablePath = getPiece(source).computePath(source, target);
        Map<Position, Boolean> isEmptySquare = generateIsEmptySquare(movablePath);

        validateMove(source, target, isEmptySquare);
        move(source, target);
    }

    private Map<Position, Boolean> generateIsEmptySquare(final Set<Position> movablePath) {
        return movablePath.stream()
                .collect(toMap(
                        position -> position,
                        position -> getPiece(position).isEmpty()
                ));
    }

    private void validate(final Color color, final Position source, final Position target) {
        validateIsEmptySquare(source);
        validateSourceColor(source, color);
        validateTargetColor(source, target);
    }

    private void validateIsEmptySquare(final Position source) {
        if (getPiece(source).isEmpty()) {
            throw new IllegalArgumentException("비어있는 칸입니다.");
        }
    }

    private void validateSourceColor(final Position source, final Color color) {
        if (getPiece(source).differsColor(color)) {
            throw new IllegalArgumentException("움직일 수 있는 기물이 아닙니다.");
        }
    }

    private void validateTargetColor(final Position source, final Position target) {
        if (getPiece(source).equalsColor(getPiece(target))) {
            throw new IllegalArgumentException("자신의 기물이 있는 곳으로 이동할 수 없습니다.");
        }
    }

    private void validateMove(final Position source, final Position target, final Map<Position, Boolean> isEmptySquare) {
        if (!getPiece(source).canMove(isEmptySquare, source, target)) {
            throw new IllegalArgumentException("유효한 움직임이 아닙니다.");
        }
    }

    private void move(final Position source, final Position target) {
        board.put(target, getPiece(source));
        board.remove(source);
    }

    public double computeScore(final Color color) {
        double sum = board.values().stream()
                .filter(piece -> piece.equalsColor(color))
                .mapToDouble(Piece::getScore).sum();

        Map<String, Long> pawnsByFile = countPawnsByFile(color);
        long stackedPawnsCount = sumStackedPawns(pawnsByFile);
        sum -= stackedPawnsCount * STACKED_PAWN_SCORE;

        return sum;
    }

    private Map<String, Long> countPawnsByFile(final Color color) {
        return board.keySet().stream()
                .filter(position -> board.get(position).equalsColor(color))
                .filter(position -> board.get(position).getKind() == Kind.PAWN)
                .collect(groupingBy(Position::getFile, counting()));
    }

    private Long sumStackedPawns(final Map<String, Long> pawnsByFile) {
        return pawnsByFile.values().stream()
                .filter(value -> value >= DOUBLE_PAWN)
                .reduce(0L, Long::sum);
    }

    public Color computeWinner() {
        List<Color> kingColors = board.values().stream()
                .filter(piece -> piece.getKind() == Kind.KING)
                .map(Piece::getColor)
                .collect(Collectors.toList());

        if (kingColors.size() == BOTH_KINGS_ALIVE) {
            return Color.NONE;
        }
        return kingColors.get(WINNER_COLOR_INDEX);
    }

    private Piece getPiece(Position position) {
        return board.getOrDefault(position, new Empty());
    }

    public Map<Position, Piece> getBoard() {
        return board.entrySet().stream()
                .collect(toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public String toString() {
        return board.toString();
    }
}
