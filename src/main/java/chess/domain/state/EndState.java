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

public class EndState implements State {

    public static final double DEFAULT_PAWN_POINT = 1d;
    public static final double DUPLICATED_PAWN_POINT = 0.5d;
    private static final int PAWN_DUPLICATION_COUNT = 1;

    private final Status status;

    public EndState(Board board) {
        this.status = createStatus(board);
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException("게임이 종료되었습니다.");
    }

    @Override
    public State move(MoveParameter moveParameter, Turn turn) {
        throw new UnsupportedOperationException("게임이 종료되었습니다.");
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException("이미 종료 되었습니다.");
    }

    @Override
    public State status() {
        throw new UnsupportedOperationException("이미 종료 되었습니다.");
    }

    @Override
    public Board getBoard() {
        throw new UnsupportedOperationException("이미 종료 되었습니다.");
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public Map<Position, PieceState> getRemainPiece(Player player) {
        throw new UnsupportedOperationException("이미 종료 되었습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    private Status createStatus(Board board) {
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
    public Map<Position, PieceState> getRemainPiece(Player player, Board board) {
        return board.getRemainPieces(player);
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
}
