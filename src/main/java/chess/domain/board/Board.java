package chess.domain.board;

import static chess.domain.piece.Role.BLANK;
import static chess.domain.piece.Role.INITIAL_PAWN;
import static chess.domain.piece.Role.KING;
import static chess.domain.piece.Role.PAWN;

import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.square.Color;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.domain.square.Team;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Board {
    private static final Color FIRST_TURN_COLOR = Color.WHITE;
    private static final int DEFAULT_KING_COUNT = 2;

    private final Map<Square, Piece> board;
    private Team turn;

    Board(final Map<Square, Piece> board) {
        this.board = board;
        this.turn = Team.from(FIRST_TURN_COLOR);
    }

    public Piece findPiece(final File file, final Rank rank) {
        Square piece = Square.of(file, rank);
        if (!board.containsKey(piece)) {
            Team blankTeam = Team.from(Color.EMPTY);
            return BLANK.create(blankTeam);
        }
        return board.get(piece);
    }

    public void makeMove(final Square sourceSquare, final Square targetSquare) {
        validate(sourceSquare, targetSquare);

        Direction direction = Direction.of(sourceSquare, targetSquare);
        Piece sourcePiece = board.get(sourceSquare);
        int distance = sourceSquare.calculateDistance(targetSquare);

        validateCanNotMove(targetSquare, direction, sourcePiece, distance);
        validateIsNotEmptyPath(sourceSquare, direction, distance);

        move(sourceSquare, targetSquare, sourcePiece);
    }

    private void validate(final Square sourceSquare, final Square targetSquare) {
        validateEmptySourceSquare(sourceSquare);
        validateSourceEqualsTarget(sourceSquare, targetSquare);
        validateSideWrongTurn(sourceSquare);
    }

    private void validateEmptySourceSquare(final Square sourceSquare) {
        if (!board.containsKey(sourceSquare)) {
            throw new IllegalArgumentException("해당 칸에 기물이 없습니다.");
        }
    }

    private void validateSourceEqualsTarget(final Square sourceSquare, final Square targetSquare) {
        if (sourceSquare.equals(targetSquare)) {
            throw new IllegalArgumentException("동일한 칸으로는 이동할 수 없습니다.");
        }
    }

    private void validateSideWrongTurn(final Square sourceSquare) {
        Piece sourcePiece = board.get(sourceSquare);
        if (sourcePiece.isOpposite(turn)) {
            throw new IllegalArgumentException("진영에 맞는 말을 움직여주세요.");
        }
    }

    private void validateCanNotMove(final Square targetSquare, final Direction direction, final Piece sourcePiece,
                                    final int distance) {
        boolean possible = isPossibleMove(targetSquare, direction, sourcePiece, distance);

        if (!possible) {
            throw new IllegalArgumentException("유효하지 않은 이동입니다.");
        }
    }

    private boolean isPossibleMove(final Square targetSquare, final Direction direction, final Piece sourcePiece,
                                   final int distance) {
        if (board.containsKey(targetSquare)) {
            return sourcePiece.canAttack(direction, distance, board.get(targetSquare));
        }
        return sourcePiece.canMove(direction, distance);
    }

    private void validateIsNotEmptyPath(final Square sourceSquare, final Direction direction, final int distance) {
        List<Square> path = findPath(sourceSquare, direction, distance);
        for (Square square : path) {
            validateIsNotEmptySquare(square);
        }
    }

    private void move(final Square sourceSquare, final Square targetSquare, final Piece sourcePiece) {
        board.put(targetSquare, sourcePiece.currentState());
        board.remove(sourceSquare);
        turn = turn.findOpponent();
    }

    private void validateIsNotEmptySquare(final Square square) {
        if (board.containsKey(square)) {
            throw new IllegalArgumentException("해당 칸으로는 이동할 수 없습니다.");
        }
    }

    private List<Square> findPath(final Square sourceSquare, final Direction direction, final int distance) {
        List<Square> squares = new ArrayList<>();
        Square nextSquare = sourceSquare;
        for (int i = 0; i < distance - 1; i++) {
            nextSquare = nextSquare.nextSquare(direction);
            squares.add(nextSquare);
        }
        return squares;
    }

    public double calculateScore(final Team team) {
        double pawnScore = calculatePawnScore(team);
        double majorScore = calculateMajorScore(team);

        return majorScore + pawnScore;
    }

    private double calculatePawnScore(final Team team) {
        return Arrays.stream(File.values())
                .mapToDouble(file -> calculatePawnScoreOfFile(team, file))
                .sum();
    }

    private long calculatePawnScoreOfFile(final Team team, final File file) {
        long score = board.entrySet().stream()
                .filter(entry -> entry.getValue().isSameSide(team))
                .filter(entry -> entry.getValue().hasSameRole(PAWN) || entry.getValue().hasSameRole(INITIAL_PAWN))
                .map(Map.Entry::getKey)
                .filter(square -> square.hasSameFile(file))
                .count();
        if (score >= 2) {
            return score / 2;
        }
        return score;
    }

    private double calculateMajorScore(final Team team) {
        return board.values().stream()
                .filter(piece -> piece.isSameSide(team))
                .filter(piece -> !piece.hasSameRole(PAWN) && !piece.hasSameRole(INITIAL_PAWN))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    public boolean isEnd() {
        return board.values().stream()
                .filter(piece -> piece.hasSameRole(KING))
                .count() < DEFAULT_KING_COUNT;
    }

    public Team findWinner() {
        return board.values().stream()
                .filter(piece -> piece.hasSameRole(KING))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("킹이 존재하지 않습니다."))
                .getTeam();
    }
}
