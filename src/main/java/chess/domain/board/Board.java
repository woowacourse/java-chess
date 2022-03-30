package chess.domain.board;

import chess.domain.board.position.Position;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Score;
import chess.domain.piece.attribute.Team;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class Board {
    private static final String NO_MOVE_ERROR_MESSAGE = "이동할 수 없는 위치입니다.";

    private final Map<Position, Piece> squares;

    public Board(Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public Piece findByPosition(Position position) {
        return squares.get(position);
    }

    public void move(Position from, Position to) {
        validateMove(from, to);
        replace(from, to, squares.get(from));
    }

    private void validateMove(Position from, Position to) {
        checkNotSameTeam(from, to);
        checkCanMove(from, to);
        checkRoute(from, to);
    }

    private void checkNotSameTeam(Position from, Position to) {
        if (squares.get(from).isSameTeam(squares.get(to))) {
            throw new IllegalArgumentException();
        }
    }

    private void checkCanMove(Position from, Position to) {
        if (!squares.get(from).canMove(squares.get(to), from, to) && squares.get(to).isPiece()) {
            throw new IllegalArgumentException(NO_MOVE_ERROR_MESSAGE);
        }
    }

    private void checkRoute(Position from, Position to) {
        List<Position> route = findByPosition(from).calculateRoute(from, to);
        for (Position position : route) {
            checkIsPiece(position);
        }
    }

    private void checkIsPiece(Position position) {
        if (findByPosition(position).isPiece()) {
            throw new IllegalArgumentException(NO_MOVE_ERROR_MESSAGE);
        }
    }

    private void replace(Position from, Position to, Piece sourceAbstractPiece) {
        squares.replace(to, sourceAbstractPiece);
        squares.replace(from, new EmptyPiece());
    }

    public boolean isSameColor(Position position, Team team) {
        return findByPosition(position).getTeam() == team;
    }

    public Map<Team, Double> getScoreOfTeams() {
        Map<Team, Double> scoreOfTeams = new EnumMap<>(Team.class);
        scoreOfTeams.put(Team.WHITE, getInitScore(Team.WHITE) - getPawnMinusScore(Team.WHITE));
        scoreOfTeams.put(Team.BLACK, getInitScore(Team.BLACK) - getPawnMinusScore(Team.BLACK));
        return scoreOfTeams;
    }

    private double getInitScore(Team team) {
        return squares.entrySet().stream()
                .filter(entry -> isSameColor(entry.getKey(), team))
                .mapToDouble(Board::scoreOfPiece)
                .sum();
    }

    private static double scoreOfPiece(Entry<Position, Piece> entry) {
        return Score.valueOf(entry.getValue()).getValue();
    }

    private double getPawnMinusScore(Team team) {
        return (double) squares.entrySet().stream()
                .filter(entry -> isSameColor(entry.getKey(), team))
                .filter(entry -> entry.getValue().isPawn())
                .filter(entry -> isOtherPawnInRank(entry.getKey()))
                .count() / 2;
    }

    private boolean isOtherPawnInRank(Position position) {
        System.out.println();
        return squares.entrySet().stream()
                .anyMatch(entry -> entry.getKey().isEqualRank(position) && entry.getValue().isPawn());
    }

    public Map<Position, Piece> getSquares() {
        return new HashMap<>(squares);
    }

    public boolean isCheckmate(Position to) {
        return findByPosition(to).isKing();
    }
}
