package chess.domain.game;

import chess.domain.piece.Team;

import static chess.view.PositionConverter.convertToSourcePosition;
import static chess.view.PositionConverter.convertToTargetPosition;

public class ChessGame {

    private final Board board;
    private Team turn;

    public ChessGame(Board board) {
        this.board = board;
        this.turn = Team.getStartTeam();
    }

    public void progress(String command) {
        Position source = convertToSourcePosition(command);
        Position target = convertToTargetPosition(command);
        if (board.isCorrectTeam(source, turn)) {
            board.move(source, target);
            turn = turn.reverse();
            return;
        }
        throw new IllegalArgumentException("[ERROR] 지금은 " + turn + "차례입니다.");
    }
}
