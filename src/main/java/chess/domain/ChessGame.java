package chess.domain;

import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ChessGame {
    private final Board board;
    private Team currentTeam;

    public ChessGame(Board board) {
        this.board = board;
        this.currentTeam = Team.BLACK;
    }

    public void movePiece(List<Position> positions,
                          Consumer<Map<Position, Character>> consumer,
                          Runnable runnable) {
        board.validateOppositeTeamByPosition(positions.get(0), currentTeam);
        board.move(positions.get(0), positions.get(1));
        consumer.accept(board.mapPositionToCharacter());

        validateCheck(currentTeam, runnable);
        currentTeam = currentTeam.opponent();
    }

    private void validateCheck(Team currentTeam, Runnable runnable) {
        if (board.isChecked(currentTeam)) {
            runnable.run();
        }

        if (board.isChecked(currentTeam.opponent())) {
            throw new IllegalArgumentException("체크 상태를 벗어나지 않았습니다.");
        }
    }
}
