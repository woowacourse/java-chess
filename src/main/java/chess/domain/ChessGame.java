package chess.domain;

import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;
import java.util.List;
import java.util.Map;

public class ChessGame {
    private final Board board;
    private Team currentTeam;

    public ChessGame(Board board) {
        this.board = board;
        this.currentTeam = Team.WHITE;
    }

    public Map<Position, Character> movePiece(List<Position> positions, Runnable runnable) {
        board.validateSameTeamByPosition(positions.get(0), currentTeam);
        board.move(positions.get(0), positions.get(1));
        validateCheck(runnable);
        currentTeam = currentTeam.opponent();
        return board.mapPositionToCharacter();
    }

    private void validateCheck(Runnable runnable) {
        if (board.isChecked(currentTeam.opponent())) {
            runnable.run();
        }
        if (board.isChecked(currentTeam)) {
            throw new IllegalArgumentException("체크 상태를 벗어나지 않았습니다.");
        }
    }
}
