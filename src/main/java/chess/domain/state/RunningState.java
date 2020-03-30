package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.Turn;
import chess.domain.board.Board;
import chess.domain.piece.Pawn;
import chess.domain.piece.PieceState;
import chess.domain.player.Player;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.result.Status;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RunningState implements State {

    public static final double DEFAULT_PAWN_POINT = 1d;
    public static final double DUPLICATED_PAWN_POINT = 0.5d;
    public static final double DEFAULT = 0d;
    private static final int PAWN_DUPLICATION_COUNT = 1;
    private Board board;

    public RunningState(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException("이미 게임이 시작되었습니다.");
    }

    @Override
    public State move(MoveParameter moveParameter, Turn turn) {
        Map<Player, Double> status = new HashMap<>();
        board.move(moveParameter.getSource(), moveParameter.getTarget(), turn);
        if (board.isLost(Player.WHITE)) {
            status.put(Player.BLACK, DEFAULT);
            return new EndState(new Status(status));
        }
        if (board.isLost(Player.BLACK)) {

            status.put(Player.WHITE, DEFAULT);
            return new EndState(new Status(status));
        }
        return this;
    }

    @Override
    public State end() {
        return new EndState(createStatus());
    }

    @Override
    public State status() {
        return new EndState(createStatus());
    }

    public Status createStatus() {
        Map<Player, Double> status = new HashMap<>();
        double blackSum = 0;
        double whiteSum = 0;

        for (File file : File.values()) {
            blackSum += getPawnPointsByFile(file, Player.BLACK);
            whiteSum += getPawnPointsByFile(file, Player.WHITE);
        }

        blackSum = getPlayerSum(blackSum, Player.BLACK);
        whiteSum = getPlayerSum(whiteSum, Player.WHITE);

        status.put(Player.BLACK, blackSum);
        status.put(Player.WHITE, whiteSum);
        return new Status(Collections.unmodifiableMap(status));
    }

    private double getPlayerSum(double blackSum, Player player) {
        blackSum += getRemainPiece(player)
                .values()
                .stream()
                .filter(piece -> !(piece instanceof Pawn))
                .mapToDouble(PieceState::getPoint)
                .sum();
        return blackSum;
    }

    @Override
    public Map<Position, PieceState> getRemainPiece(Player player) {
        return board.getRemainPieces(player);
    }

    @Override
    public Status getStatus() {
        throw new UnsupportedOperationException("게임이 아직 종료되지 않았습니다.");
    }

    private double getPawnPointsByFile(File file, Player player) {

        /* 해당 file의 PAWN 점수합을 계산한다 */
        double duplicatedPawnCount = getDuplicatedPawnCount(file, player);
        if (duplicatedPawnCount > PAWN_DUPLICATION_COUNT) {
            return duplicatedPawnCount * DUPLICATED_PAWN_POINT;
        }
        return duplicatedPawnCount * DEFAULT_PAWN_POINT;

    }

    private double getDuplicatedPawnCount(File file, Player player) {
        /* 해당 file의 PAWN 개수합을 계산한다 */
        return getRemainPiece(player)
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() instanceof Pawn)
                .filter(entry -> entry.getKey().isSameFile(file))
                .mapToDouble(entry -> entry.getValue().getPoint())
                .sum();
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
