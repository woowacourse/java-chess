package chess.domain;

import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;
import chess.exception.ImpossibleMoveException;
import java.util.Map;

public class ChessGame {
    private final Board board;
    private Team currentTeam;

    public ChessGame(Board board) {
        this.board = board;
        this.currentTeam = Team.WHITE;
    }

    public Map<Position, Character> movePiece(Positions positions, Runnable printCheck) {
        board.validateSameTeamByPosition(positions.source(), currentTeam);
        board.move(positions);
        validateCheck(printCheck);
        currentTeam = currentTeam.opponent();
        return board.mapPositionToCharacter();
    }

    private void validateCheck(Runnable printCheck) {
        if (board.isChecked(currentTeam.opponent())) {
            printCheck.run();
        }
        if (board.isChecked(currentTeam)) {
            throw new ImpossibleMoveException("체크 상태를 벗어나지 않았습니다.");
        }
    }
}
