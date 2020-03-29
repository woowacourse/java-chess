package chess.domain.board;

import chess.domain.Team;
import chess.domain.piece.MoveInformation;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.X;
import chess.domain.position.Y;
import chess.domain.score.Score;
import chess.domain.score.ScoreCalculator;
import java.util.Collections;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public void move(Team current, String keyFromPosition, String keyToPosition) {
        move(current, Position.of(keyFromPosition), Position.of(keyToPosition));
    }

    public void move(Team current, Position from, Position to) {
        Piece piece = board.get(from);
        validateBelongsCurrentTeam(current, piece);

        if (!piece.canMove(new MoveInformation(board, from, to))) {
            throw new IllegalArgumentException(from + "에서 " + to + "로 이동할 수 없습니다.");
        }
        board.remove(from);
        board.put(to, piece);
    }

    private void validateBelongsCurrentTeam(Team current, Piece piece) {
        if (!piece.belongs(current)) {
            throw new IllegalArgumentException("현재 팀의 기물만 움직일 수 있습니다.");
        }
    }

    public boolean isPieceOnBoard(Team team, PieceType pieceType) {
        return this.board.entrySet().stream()
            .anyMatch(entry -> entry.getValue().belongs(team)
                && entry.getValue().is(pieceType));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Y y : Y.values()) {
            stringBuilder.append(makeStringByX(y));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String makeStringByX(Y y) {
        StringBuilder stringBuilder = new StringBuilder();

        for (X x : X.values()) {
            stringBuilder.append(makeStringByPosition(Position.of(x, y)));
        }
        return stringBuilder.toString();
    }

    private String makeStringByPosition(Position position) {
        if (board.containsKey(position)) {
            return board.get(position)
                .getAcronym();
        }
        return ".";
    }

    public Score calculateScore() {
        return ScoreCalculator.calculate(Collections.unmodifiableMap(board));
    }
}
