package chess.domain;

import chess.domain.piece.character.Team;
import chess.dto.BoardStatusDto;
import chess.dto.MovementDto;
import chess.exception.ImpossibleMoveException;

public class ChessGame {
    private final Board board;
    private Team currentTeam;

    public ChessGame(Board board) {
        this.board = board;
        this.currentTeam = Team.WHITE;
    }

    public BoardStatusDto movePiece(MovementDto movementDto) {
        board.validateSameTeamByPosition(movementDto.source(), currentTeam);
        board.move(movementDto);
        validateCheck();
        currentTeam = currentTeam.opponent();
        return new BoardStatusDto(board.mapPositionToCharacter(), checkStatus());
    }

    private void validateCheck() {
        if (board.isChecked(currentTeam)) {
            throw new ImpossibleMoveException("체크 상태를 벗어나지 않았습니다.");
        }
    }

    private Status checkStatus() {
        if (board.isChecked(currentTeam)) {
            if (board.isCheckmate(currentTeam)) {
                return Status.CHECKMATE;
            }
            return Status.CHECK;
        }
        return Status.NORMAL;
    }
}
