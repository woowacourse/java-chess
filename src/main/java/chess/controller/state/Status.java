package chess.controller.state;

import chess.controller.Command;
import chess.domain.Score;
import chess.domain.chess.ChessGame;
import chess.domain.piece.TeamColor;

public class Status implements State {
    private final ChessGame chessGame;
    private final TeamColor teamColor;

    private Status(ChessGame chessGame, TeamColor teamColor) {
        this.chessGame = chessGame;
        this.teamColor = teamColor;
    }

    public static State of(ChessGame chessGame, TeamColor teamColor) {
        Score score = new Score();
        System.out.println("status");
        return new Status(chessGame, teamColor);
    }

    @Override
    public State checkCommand(Command command) {
        if (command.isEnd()) {
            return new End();
        }
        if (command.isMove()) {
            Move move = new Move(chessGame, teamColor);
            return move.checkCommand(command);
        }
        if (command.isStatus()) {
            return Status.of(chessGame, teamColor);
        }
        throw new UnsupportedOperationException("end, move 명령어만 입력 가능합니다.");
    }

    @Override
    public boolean isRun() {
        return true;
    }
}
