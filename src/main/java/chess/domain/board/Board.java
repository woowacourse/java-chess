package chess.domain.board;

import chess.domain.board.position.Position;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Score;
import chess.domain.piece.attribute.Team;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class Board {
    private static final String NO_MOVE_ERROR_MESSAGE = "이동할 수 없는 위치입니다.";

    private final Map<Position, Piece> squares;

    public Board(Map<Position, Piece> squares) {
        this.squares = squares;
    }

    private static double scoreOfPiece(Entry<Position, Piece> positionPiece) {
        return Score.valueOf(positionPiece.getValue()).getValue();
    }

    public Piece findByPosition(Position position) {
        return squares.get(position);
    }

    public void move(Position from, Position to) {
        Piece sourcePiece = squares.get(from);
        Piece targetPiece = squares.get(to);

        validateNotSameTeam(sourcePiece, targetPiece);

        if (!sourcePiece.canMove(targetPiece, from, to) && targetPiece.isPiece()) {
            throw new IllegalArgumentException(NO_MOVE_ERROR_MESSAGE);
        }

        validateNotHurdle(from, to);

        squares.replace(to, sourcePiece);
        squares.replace(from, new EmptyPiece());
    }

    public boolean isSameColor(Position position, Team team) {
        return findByPosition(position).getColor() == team;
    }

    private void validateNotSameTeam(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isSameTeam(targetPiece)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateNotHurdle(Position from, Position to) {
        List<Position> route = findByPosition(from).getRoute(from, to);

        for (Position position : route) {
            if (findByPosition(position).isPiece()) {
                throw new IllegalArgumentException(NO_MOVE_ERROR_MESSAGE);
            }
        }
    }

    public Map<Team, Double> getTotalStatus() {
        Map<Team, Double> totalScore = new EnumMap<>(Team.class);
        totalScore.put(Team.WHITE, getScore(Team.WHITE));
        totalScore.put(Team.BLACK, getScore(Team.BLACK));

        return totalScore;
    }

    private double getScore(Team team) {
        return squares.entrySet().stream()
                .filter(positionPiece -> isSameColor(positionPiece.getKey(), team))
                .mapToDouble(Board::scoreOfPiece)
                .sum();
    }

    public boolean isKing(Position to) {
        return findByPosition(to).isKing();
    }
}
