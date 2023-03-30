package chess.game;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

import java.util.Objects;

public class ChessGame {

    private int id = 0;
    private final Board board;
    private Turn turn;

    public ChessGame(Board board, Turn turn) {
        this.board = board;
        this.turn = turn;
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

    public Board getChessBoard() {
        return new Board(board.getBoard());
    }

    public Team getCurrentTeam() {
        return turn.getTeam();
    }

    public Turn getCurrentTurn() {
        return new Turn(turn.getTeam());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
