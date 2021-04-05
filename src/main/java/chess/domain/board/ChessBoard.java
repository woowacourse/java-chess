package chess.domain.board;

import chess.domain.feature.Color;
import chess.domain.piece.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {
    public static final int BOARD_SIZE = 8;
    private static final double PAWN_PUNISHMENT_RATIO = 0.5;
    private static final int NUMBER_OF_KINGS = 2;
    private static final int MINIMUM_PUNISHABLE_PAWN_COUNT = 2;
    public static final int KING_DEATH_SCORE = -1;

    private final Map<Position, Piece> board = new LinkedHashMap<>();

    public ChessBoard() {
        initBlank();
    }

    public void initBoard() {
        initBlack();
        initWhite();
    }

    private void initBlank() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board.put(Position.of(i, j), new Blank(Position.of(i, j)));
            }
        }
    }

    private void initBlack() {
        replace(Position.of("a8"), new Rook(Color.BLACK, Position.of("a8")));
        replace(Position.of("b8"), new Knight(Color.BLACK, Position.of("b8")));
        replace(Position.of("c8"), new Bishop(Color.BLACK, Position.of("c8")));
        replace(Position.of("d8"), new Queen(Color.BLACK, Position.of("d8")));
        replace(Position.of("e8"), new King(Color.BLACK, Position.of("e8")));
        replace(Position.of("f8"), new Bishop(Color.BLACK, Position.of("f8")));
        replace(Position.of("g8"), new Knight(Color.BLACK, Position.of("g8")));
        replace(Position.of("h8"), new Rook(Color.BLACK, Position.of("h8")));

        for (String position : Arrays.asList("a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7")) {
            replace(Position.of(position), new Pawn(Color.BLACK, Position.of(position)));
        }
    }

    private void initWhite() {
        replace(Position.of("a1"), new Rook(Color.WHITE, Position.of("a1")));
        replace(Position.of("b1"), new Knight(Color.WHITE, Position.of("b1")));
        replace(Position.of("c1"), new Bishop(Color.WHITE, Position.of("c1")));
        replace(Position.of("d1"), new Queen(Color.WHITE, Position.of("d1")));
        replace(Position.of("e1"), new King(Color.WHITE, Position.of("e1")));
        replace(Position.of("f1"), new Bishop(Color.WHITE, Position.of("f1")));
        replace(Position.of("g1"), new Knight(Color.WHITE, Position.of("g1")));
        replace(Position.of("h1"), new Rook(Color.WHITE, Position.of("h1")));

        for (String position : Arrays.asList("a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2")) {
            replace(Position.of(position), new Pawn(Color.WHITE, Position.of(position)));
        }
    }

    public void move(Position sourcePosition, Position targetPosition) {
        Direction direction = Path.findDirection(this, sourcePosition, targetPosition);

        Piece sourcePiece = getPiece(sourcePosition);
        sourcePiece.move(this, direction, targetPosition);
    }

    public void replace(Position position, Piece piece) {
        board.replace(position, piece);
    }

    public List<Direction> getCandidateDirections(Position position) {
        return board.get(position).directions();
    }

    public Map<Position, Piece> getChessBoard() {
        return board;
    }

    public Piece getPiece(Position position) {
        return board.get(position);
    }

    public boolean isOngoing() {
        long kingCount = board.values()
                .stream()
                .filter(Piece::isKing)
                .count();
        return kingCount == NUMBER_OF_KINGS;
    }

    public double getScore(Color color) {
        if (isKingDead(color)) {
            return KING_DEATH_SCORE;
        }
        double score = calculateScoreBeforePunishment(color);
        Map<Column, Long> pawnCountPerColumn = calculatePawnCount(color);
        double punishmentScore = calculatePunishmentScore(pawnCountPerColumn);
        return score - punishmentScore;
    }

    private boolean isKingDead(Color color) {
        return board.values()
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .noneMatch(Piece::isKing);
    }

    private double calculateScoreBeforePunishment(Color color) {
        return board.values()
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::score)
                .sum();
    }

    private Map<Column, Long> calculatePawnCount(Color color) {
        return board.entrySet()
                .stream()
                .filter(positionPieceEntry -> positionPieceEntry.getValue().isPawn())
                .filter(positionPieceEntry -> positionPieceEntry.getValue().isSameColor(color))
                .collect(Collectors.groupingBy(positionPieceEntry -> positionPieceEntry.getKey().getColumn(), Collectors.counting()));
    }

    private double calculatePunishmentScore(Map<Column, Long> pawnCountPerColumn) {
        return pawnCountPerColumn.values()
                .stream()
                .filter(count -> count >= MINIMUM_PUNISHABLE_PAWN_COUNT)
                .mapToDouble(count -> count * PAWN_PUNISHMENT_RATIO)
                .sum();
    }
}
