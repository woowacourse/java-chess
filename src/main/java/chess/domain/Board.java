package chess.domain;

import static chess.domain.piece.PieceType.INITIAL_PAWN;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;
import static java.lang.Math.*;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private final Map<Square, Piece> board;

    public Board() {
        this.board = Pieces.init();
    }

    public Board(Map<Square, Piece> board) {
        this.board = board;
    }

    public boolean isSameTeam(final Square source, final Team team) {
        Piece piece = findPieceBy(source);
        return piece.team() == team;
    }

    public void move(final Square source, final Square target) {
        validateTarget(source, target);
        updateSquare(source, target);
    }

    private void validateTarget(final Square source, final Square target) {
        if (!canMove(source, target)) {
            throw new IllegalArgumentException("이동할 수 없는 칸입니다.");
        }
    }

    private void updateSquare(final Square source, final Square target) {
        Piece piece = findPieceBy(source);
        if (piece.isSameType(INITIAL_PAWN)) {
            piece = new Pawn(piece.team());
        }
        board.put(target, piece);
        board.remove(source);
    }

    private boolean canMove(final Square source, final Square target) {
        final Piece piece = findPieceBy(source);
        final int fileInterval = File.calculate(source.file(), target.file());
        final int rankInterval = Rank.calculate(source.rank(), target.rank());
        piece.canMove(fileInterval, rankInterval, isEnemy(target, piece));
        if (piece.isSameType(KNIGHT)) {
            return !board.containsKey(target);
        }
        return canMoveNextSquare(source, fileInterval, rankInterval);
    }

    private boolean isEnemy(final Square target, final Piece piece) {
        if (board.containsKey(target)) {
            return !isSameTeam(piece, board.get(target));
        }
        return false;
    }

    private Piece findPieceBy(final Square square) {
        if (!board.containsKey(square)) {
            throw new IllegalArgumentException("말이 있는 위치를 입력해주세요.");
        }
        return board.get(square);
    }

    private boolean canMoveNextSquare(final Square source, final int fileInterval, final int rankInterval) {
        int fileMoveDirection = getMoveDirection(fileInterval);
        int rankMoveDirection = getMoveDirection(rankInterval);
        int interval = getMoveInterval(fileInterval, rankInterval);

        return checkNextSquares(source, List.of(fileMoveDirection, rankMoveDirection), interval);
    }

    private boolean checkNextSquares(final Square source, final List<Integer> direction, int interval) {
        Square current = source;
        do {
            current = current.next(direction.get(0), direction.get(1));
            interval--;
        } while (interval > 0 && canMoveToNext(source, current));
        return canMoveToNext(source, current);
    }

    private boolean canMoveToNext(final Square source, final Square nextSquare) {
        Piece piece = board.get(source);
        return !(board.containsKey(nextSquare) && isSameTeam(piece, board.get(nextSquare)));
    }

    private boolean isSameTeam(final Piece piece, final Piece nextPiece) {
        return nextPiece.team() == piece.team();
    }

    private int getMoveDirection(final int interval) {
        return Integer.compare(0, interval);
    }

    private int getMoveInterval(final int fileInterval, final int rankInterval) {
        return max(abs(fileInterval), abs(rankInterval));
    }

    public Map<Square, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public double calculateTotalScoreBy(Team team) {
        return board.entrySet().stream()
                .filter(entry -> entry.getValue().team() == team)
                .mapToDouble(entry -> calculateScore(entry.getKey(), entry.getValue()))
                .sum();
    }

    private double calculateScore(Square square, Piece piece) {
        if (piece.isSameType(PAWN) || piece.isSameType(INITIAL_PAWN)) {
            if (countPawnByFileAndTeam(square, piece) > 1) {
                return 0.5;
            }
        }

        return piece.score();
    }

    private long countPawnByFileAndTeam(Square square, Piece piece) {
        return board.entrySet().stream()
                .filter(entry -> entry.getKey().file() == square.file())
                .filter(entry -> entry.getValue().isSameType(PAWN) || entry.getValue().isSameType(INITIAL_PAWN))
                .filter(entry -> entry.getValue().team() == piece.team())
                .count();
    }

    public long countKing() {
        return board.values().stream()
                .filter(piece -> piece.isSameType(KING))
                .count();
    }

    @Override
    public String toString() {
        return board.entrySet().stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .collect(Collectors.joining(","));
    }
}
