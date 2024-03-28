package chess.domain;

import chess.domain.piece.abstractPiece.Piece;
import chess.domain.piece.character.Team;
import chess.exception.ImpossibleMoveException;

public class ChessGame {
    private final Board board;
    private Team currentTeam;

    public ChessGame(Board board) {
        this(board, Team.WHITE);
    }

    public ChessGame(Board board, Team team) {
        this.board = board;
        this.currentTeam = team;
    }

    public Piece movePiece(Movement movement) {
        board.validateSameTeamByPosition(movement.source(), currentTeam);
        Piece movedPiece = board.move(movement);
        validateCheck();
        currentTeam = currentTeam.opponent();
        return movedPiece;
    }

    private void validateCheck() {
        if (board.checkState(currentTeam) == State.CHECKED) {
            throw new ImpossibleMoveException("체크 상태를 벗어나지 않았습니다.");
        }
    }

    public State checkState() {
        return board.checkState(currentTeam);
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public Board getBoard() {
        return board;
    }
}
