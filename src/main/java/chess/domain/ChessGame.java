package chess.domain;

import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;

import java.util.Map;
import java.util.function.Consumer;

public class ChessGame {
    private final Board board;
    private Team currentTeam;

    public ChessGame(Board board) {
        this.board = board;
        this.currentTeam = Team.WHITE;
    }

    public Map<Position, Character> movePiece(Positions positions, Consumer<CheckState> printCheck) {
        board.validateSameTeamByPosition(positions.source(), currentTeam);
        board.move(positions);
        printCheck.accept(validateCheck());
        currentTeam = currentTeam.opponent();
        return board.mapPositionToCharacter();
    }

    private CheckState validateCheck() {
        if (board.findCheckState(currentTeam) != CheckState.SAFE) {
            throw new IllegalArgumentException("체크 상태를 벗어나지 않았습니다.");
        }
        return board.findCheckState(currentTeam.opponent());
    }
}
