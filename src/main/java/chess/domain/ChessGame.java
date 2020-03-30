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

    public static final double DEFAULT_PAWN_POINT = 1d;
    public static final double DUPLICATED_PAWN_POINT = 0.5d;
    private static final double HALF = 2d;
    private static final int PAWN_DUPLICATION_COUNT = 1;
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
            blackSum += getPawnPointsByFile(file, Player.BLACK);
            whiteSum += getPawnPointsByFile(file, Player.WHITE);
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
        return state.getRemainPiece(player)
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() instanceof Pawn)
                .filter(entry -> entry.getKey().isSameFile(file))
                .mapToDouble(entry -> entry.getValue().getPoint())
                .sum();
    }

}
