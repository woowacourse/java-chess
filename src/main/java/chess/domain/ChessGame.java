package chess.domain;

import chess.view.Character;
import chess.domain.piece.character.Team;

import java.util.Map;

public class ChessGame {
    private final Board board;
    private Team currentTeam;

    public ChessGame(Board board) {
        this.board = board;
        this.currentTeam = Team.WHITE;
    }

    public void movePiece(Positions positions) {
        board.validateSameTeamByPosition(positions.source(), currentTeam);
        board.move(positions);
        currentTeam = currentTeam.opponent();
    }

    public CheckState findCheck() {
        if (board.findCheckState(currentTeam.opponent()) != CheckState.SAFE) {
            throw new IllegalArgumentException("체크 상태를 벗어나지 않았습니다.");
        }
        return board.findCheckState(currentTeam);
    }

    public Board getBoard() {
        return board;
    }
}
