package chess.game;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.dto.SquareResponse;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class ChessGame {

    private final Board board;
    private Turn turn;

    public ChessGame(Board board) {
        this.board = board;
        this.turn = new Turn(Team.WHITE);
    }

    public void move(Position source, Position target) {
        validateTurn(source);
        validatePosition(source, target);
        if (!board.canMove(source, target)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다.");
        }
        board.move(source, target);
        changeTurn();
    }

    public boolean isEndCondition() {
        return !board.isKingAlive(turn.getTeam());
    }

    public double calculateScore(Team team) {
        return board.calculateScore(team);
    }

    private void validatePosition(Position source, Position target) {
        if (Objects.equals(source, target)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다.");
        }
    }

    private void changeTurn() {
        turn = turn.next();
    }

    private void validateTurn(Position source) {
        Piece sourcePiece = board.findByPosition(source);
        if (isInvalidTurn(sourcePiece)) {
            throw new IllegalArgumentException("[ERROR] " + turn.getTeam().name() + "팀의 말만 이동할 수 있습니다.");
        }
    }

    private boolean isInvalidTurn(Piece sourcePiece) {
        return !turn.isCorrectWith(sourcePiece);
    }

    public List<SquareResponse> getBoard() {
        return board.getBoard().entrySet().stream()
                .map(entry -> SquareResponse.of(entry.getKey(), entry.getValue()))
                .collect(toList());
    }

    public Team getLosingTeam() {
        return turn.getTeam();
    }

    public Team getWinningTeam() {
        return turn.next().getTeam();
    }
}
