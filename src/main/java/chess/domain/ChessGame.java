package chess.domain;

import chess.domain.piece.character.Team;
import chess.exception.ImpossibleMoveException;

public class ChessGame {
    private final Board board;
    private Team currentTeam;

    public ChessGame(Board board) {
        this.board = board;
        this.currentTeam = Team.WHITE;
    }

    public void movePiece(Movement movement) {
        board.validateSameTeamByPosition(movement.source(), currentTeam);
        board.move(movement);
        validateCheck();
        currentTeam = currentTeam.opponent();
    }

    private void validateCheck() {
        if (board.isChecked(currentTeam)) {
            throw new ImpossibleMoveException("체크 상태를 벗어나지 않았습니다.");
        }
    }

    public Status checkStatus() {
        if (board.isChecked(currentTeam)) {
            if (board.isCheckmate(currentTeam)) {
                return Status.CHECKMATE;
            }
            return Status.CHECK;
        }
        return Status.NORMAL;
    }
}
