package chess.domain.state;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.domain.ChessBoard;
import chess.domain.Command;
import chess.domain.piece.Team;
import java.util.HashMap;
import java.util.Map;

public class End implements State {

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State start() {
        throw new IllegalArgumentException("이미 게임이 종료되었습니다.");
    }

    @Override
    public State stop() {
        throw new IllegalArgumentException("이미 게임이 종료되었습니다.");
    }

    @Override
    public Map<Team, Double> status(ChessBoard chessBoard) {
        Double whiteScore = calculateScore(chessBoard, WHITE);
        Double blackScore = calculateScore(chessBoard, BLACK);

        Map<Team, Double> result = new HashMap<>();
        result.put(WHITE, whiteScore);
        result.put(BLACK, blackScore);

        return result;
    }

    private double calculateScore(ChessBoard chessBoard, Team team) {
        return chessBoard.calculateByTeam(team);
    }

    @Override
    public State changeTurn(Command command, ChessBoard chessBoard) {
        throw new IllegalArgumentException("게임을 종료합니다.");
    }
}
