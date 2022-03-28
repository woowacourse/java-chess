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
        throw new IllegalArgumentException("이미 게임을 시작하였습니다.");
    }

    @Override
    public State stop() {
        return new End();
    }

    @Override
    public State changeTurn(Command command, ChessBoard chessBoard) {
        checkTeam(command, chessBoard);

        chessBoard.move(command);

        return new White();
    }

    @Override
    public double calculateBlackScore(ChessBoard chessBoard) {
        throw new IllegalArgumentException("지금은 점수를 계산할 수 없습니다.");
    }

    @Override
    public double calculateWhiteScore(ChessBoard chessBoard) {
        throw new IllegalArgumentException("지금은 점수를 계산할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    private void checkTeam(Command command, ChessBoard chessBoard) {
        Map<String, Position> positions = command.makePositions();

        Position source = positions.get("source");

        Team team = chessBoard.findTeam(source);

        if (!(team == BLACK)) {
            throw new IllegalArgumentException("흰팀 말은 검은색 팀이 옮길 수 없습니다.");
        }
    }
}
