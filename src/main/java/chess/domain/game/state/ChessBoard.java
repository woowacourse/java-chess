package chess.domain.game.state;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.File;
import chess.domain.piece.position.Position;
import chess.domain.piece.position.Rank;
import chess.domain.piece.property.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    public static final int DUPLICATION_COUNT_STANDARD = 2;
    public static final double DUPLICATED_PAWN_SCORE = 0.5;

    private final Map<Position, Piece> board = new HashMap<>();

    public void move(Position source, Position target) {
        Piece sourcePiece = getPiece(source);
        Piece targetPiece = getPiece(target);

        validateExist(source);
        validateMove(source, target);

        changeState(sourcePiece, targetPiece);
        changePosition(source, target);
    }

    public void validateExist(Position position) {
        if (!isExist(getPiece(position))) {
            throw new IllegalArgumentException("해당 위치에 체스말이 존재하지 않습니다.");
        }
    }

    private boolean isExist(Piece piece) {
        return piece != null;
    }

    private void validateMove(Position source, Position target) {
        if (!canMove(source, target)) {
            throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
        }
    }

    private boolean canMove(Position source, Position target) {
        Piece piece = getPiece(source);
        List<Position> movablePaths = piece.getMovablePaths(source, this);

        return movablePaths.contains(target);
    }

    public boolean canMoveOrKillByOneStep(Position source, Direction direction) {
        Position target = source.getNext(direction);
        if (source.isBlocked(direction)) {
            return false;
        }

        return !isFilled(target) || canKill(source, target);
    }

    public boolean canOnlyMoveByOneStep(Position source, Direction direction) {
        Position target = source.getNext(direction);
        if (source.isBlocked(direction)) {
            return false;
        }

        return !isFilled(target);
    }

    public boolean canKill(Position source, Position target) {
        Piece sourcePiece = getPiece(source);
        Piece targetPiece = getPiece(target);

        return isFilled(target) && !sourcePiece.isSameColor(targetPiece.getColor());
    }

    private void changeState(Piece sourcePiece, Piece targetPiece) {
        kill(targetPiece);
        sourcePiece.updateState();
    }

    private void kill(Piece piece) {
        if (isExist(piece)) {
            piece.die();
        }
    }

    private void changePosition(Position source, Position target) {
        board.put(target, getPiece(source));
        board.remove(source);
    }

    public void putPiece(Position position, Piece piece) {
        board.put(position, piece);
    }

    public boolean isFilled(Position target) {
        return board.containsKey(target);
    }

    public Map<Color, Double> computeScore() {
        Map<Color, Double> scores = new HashMap<>();
        scores.put(Color.WHITE, calculateScore(Color.WHITE));
        scores.put(Color.BLACK, calculateScore(Color.BLACK));

        return scores;
    }

    private double calculateScore(Color color) {
        List<Piece> pieces = board.values().stream()
            .filter(piece -> piece.isSameColor(color))
            .collect(Collectors.toList());
        double total = Piece.computeScore(pieces);

        return total - calculateDuplicatePawn(color);
    }

    private double calculateDuplicatePawn(Color color) {
        List<Integer> pawnCounts = new ArrayList<>();
        for (File file : File.values()) {
            int pawnCount = (int) Arrays.stream(Rank.values())
                .map(rank -> Position.of(file, rank))
                .map(board::get)
                .filter(piece -> new Pawn(color).isSame(piece))
                .count();
            pawnCounts.add(pawnCount);
        }
        return DUPLICATED_PAWN_SCORE * countDuplicatePawn(pawnCounts);
    }

    private double countDuplicatePawn(List<Integer> counts) {
        return counts.stream()
                .filter(count -> count >= DUPLICATION_COUNT_STANDARD)
                .mapToDouble(i -> i)
                .sum();
    }

    public Piece getPiece(Position target) {
        return board.get(target);
    }

    public Map<Position, Piece> getBoard() {
        return new HashMap<>(board);
    }
}
