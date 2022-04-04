package chess.domain.state;

import static chess.domain.piece.Team.WHITE;

import chess.domain.ChessBoard;
import chess.domain.Command;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;

public class White implements State {

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State execute(Command command, ChessBoard chessBoard) {
        if (command.isMoveCommand()) {
            checkTeam(command, chessBoard);

            chessBoard.move(command);

            return new Black();
        }
        if (command.isEnd()) {
            return new End();
        }
        return this;
    }

    @Override
    public String getTurn() {
        return "white";
    }

    private void checkTeam(Command command, ChessBoard chessBoard) {
        Positions positions = command.makePositions();

        Position source = positions.getSource();

        Team team = chessBoard.findTeam(source);

        if (!(team == WHITE)) {
            throw new IllegalArgumentException("검은팀 말은 흰색 팀이 옮길 수 없습니다.");
        }
    }
}
