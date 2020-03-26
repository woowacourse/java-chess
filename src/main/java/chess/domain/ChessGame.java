package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.EnumRepositoryBoardInitializer;
import chess.domain.piece.Pawn;
import chess.domain.piece.PieceState;
import chess.domain.player.Player;
import chess.domain.position.File;
import chess.domain.state.ReadyState;
import chess.domain.state.State;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {

    private State state;
    private Turn turn = Turn.from(Player.WHITE);

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

    public Map<Player, Double> getStatus() {
        Map<Player, Double> status = new HashMap<>();
        double blackSum = 0;
        double whiteSum = 0;

        for (File file : File.values()) {
            blackSum += getPawnPoints(file, Player.BLACK);
            whiteSum += getPawnPoints(file, Player.WHITE);
        }

        blackSum += state.getRemainPiece(Player.BLACK)
                .values()
                .stream()
                .filter(piece -> !(piece instanceof Pawn))
                .mapToDouble(PieceState::getPoint)
                .sum();
        whiteSum += state.getRemainPiece(Player.WHITE)
                .values()
                .stream()
                .filter(piece -> !(piece instanceof Pawn))
                .mapToDouble(PieceState::getPoint)
                .sum();

        status.put(Player.BLACK, blackSum);
        status.put(Player.WHITE, whiteSum);
        return Collections.unmodifiableMap(status);
    }

    private double getPawnPoints(File file, Player player) {
        double pawnSum = state.getRemainPiece(player)
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

    // 각 리스트를 돌면서 pawn이 아닌 애들은 그냥 합해
    // pawn인 애들은 file의 위치가 같은애들이 있는지를 확인하고, 있으면 0.5점이 돼
    // 아니면 1점으로 계산하면 된다
}
