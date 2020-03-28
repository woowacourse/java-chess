package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.EnumRepositoryBoardInitializer;
import chess.domain.piece.Pawn;
import chess.domain.piece.PieceState;
import chess.domain.player.Team;
import chess.domain.position.File;
import chess.domain.state.ReadyState;
import chess.domain.state.State;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {

    private State state;
    private Turn turn = Turn.from(Team.WHITE);

    public ChessGame() {
        this.state = new ReadyState(new EnumRepositoryBoardInitializer());
    }

    public void start() {
        state = state.start();
    }

    public void move(MoveParameter moveParameter) {
        state = state.move(moveParameter, turn);
    }

    public void end() {
        state = state.end();
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public Map<Team, Double> getStatus() {
        Map<Team, Double> status = new HashMap<>();
        double blackSum = 0;
        double whiteSum = 0;

        for (File file : File.values()) {
            blackSum += getPawnPoints(file, Team.BLACK);
            whiteSum += getPawnPoints(file, Team.WHITE);
        }

        blackSum += getPointsExceptForPawns(Team.BLACK);
        whiteSum += getPointsExceptForPawns(Team.WHITE);

        status.put(Team.BLACK, blackSum);
        status.put(Team.WHITE, whiteSum);
        return Collections.unmodifiableMap(status);
    }

    private double getPawnPoints(File file, Team team) {
        double pawnSum = state.getRemainPiece(team)
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() instanceof Pawn)
                .filter(entry -> entry.getKey().isSameFile(file))
                .mapToDouble(entry -> entry.getValue().getPoint())
                .sum();
        if (pawnSum > 1) {
            return pawnSum / 2d;
        }
        return pawnSum;
    }

    private double getPointsExceptForPawns(Team team) {
        return state.getRemainPiece(team)
                .values()
                .stream()
                .filter(piece -> !(piece instanceof Pawn))
                .mapToDouble(PieceState::getPoint)
                .sum();
    }
}
