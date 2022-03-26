package chess.domain.state;

import static chess.domain.piece.Team.BLACK;

import chess.domain.ChessBoard;
import chess.domain.Command;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.Map;

public class Black implements State {
    @Override
    public State start() {
        throw new IllegalArgumentException();
    }

    @Override
    public State stop() {
        return new End();
    }

    @Override
    public State changeTurn(Command command, ChessBoard chessBoard) {
        checkTeam(command, chessBoard);

        chessBoard.move(command);

        System.out.println("black");

        return new White();
    }

    @Override
    public double calculateBlackScore(ChessBoard chessBoard) {
        throw new IllegalArgumentException();
    }

    @Override
    public double calculateWhiteScore(ChessBoard chessBoard) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    private void checkTeam(Command command, ChessBoard chessBoard) {
        Map<String, Position> positions = command.makePosition();

        Position source = positions.get("source");

        Team team = chessBoard.findTeam(source);

        if (!(team == BLACK)) {
            throw new IllegalArgumentException("흰팀 말은 검은색 팀이 옮길 수 없습니다.");
        }
    }
}
