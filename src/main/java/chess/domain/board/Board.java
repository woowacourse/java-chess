package chess.domain.board;

import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.attribute.Team;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public final class Board{
    private static final MoveValidator validator = new MoveValidator();

    private final Map<Position, Piece> squares;

    public Board(Map<Position, Piece> squares) {
        this.squares = squares;
    }

    private static double scoreOfPiece(Entry<Position, Piece> entry) {
        return entry.getValue().getScore();
    }

    public Piece findByPosition(Position position) {
        return squares.get(position);
    }

    public void move(Position from, Position to) {
        validator.validateMove(squares, from, to);
        replace(from, to, squares.get(from));
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

    private double getPawnMinusScore(Team team) {
        List<Column> pawnsColumns = squares.entrySet().stream()
                .filter(entry -> isSameColor(entry.getKey(), team) && entry.getValue().getName() == Name.PAWN)
                .map(entry -> entry.getKey().getColumn())
                .collect(Collectors.toList());

        long oneColumnPawnCount = pawnsColumns.stream()
                .filter(column -> countSameColumnPawn(column, team) <= 1)
                .count();
        return (pawnsColumns.size() - oneColumnPawnCount) * 0.5;
    }

    private int countSameColumnPawn(Column column, Team team) {
        return (int) squares.entrySet().stream()
                .filter(entry -> entry.getKey().isEqualColumn(column)
                        && entry.getValue().getName() == Name.PAWN
                        && entry.getValue().isSameTeamOrEmpty(team))
                .count();
    }

    public Map<Position, Piece> getSquares() {
        return new HashMap<>(squares);
    }

    public boolean isCheckmate(Position to) {
        return findByPosition(to).getName() == Name.KING;
    }
}
