package chess.domain.state;

import static chess.domain.piece.Team.WHITE;

import chess.domain.ChessBoard;
import chess.domain.Command;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.Map;

public class White implements State {

    @Override
    public State start() {
        throw new IllegalArgumentException();
    }

    @Override
    public State stop() {
        throw new IllegalArgumentException();
    }

    @Override
    public State changeTurn(Command command, ChessBoard chessBoard) {
        checkTeam(command, chessBoard);

        chessBoard.move(command);

        return new Black();
    }

    private void checkTeam(Command command, ChessBoard chessBoard) {
        Map<String, Position> positions = command.makePosition();

        Position source = positions.get("source");

        Team team = chessBoard.findTeam(source);

        if (!(team == WHITE)) {
            throw new IllegalArgumentException("검은팀 말은 흰색 팀이 옮길 수 없습니다.");
        }
    }
}
