package chess.board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import chess.piece.type.Bishop;
import chess.piece.type.King;
import chess.piece.type.Knight;
import chess.piece.type.Pawn;
import chess.piece.type.Piece;
import chess.piece.type.Queen;
import chess.piece.type.Rook;
import chess.score.Score;
import chess.team.Team;

public class ChessBoard {
    private static final int MINIMUM_LINE = 0;
    private static final int LIMIT_LINE = 8;
    private static final double PAWN_REDUCE_VALUE = 0.5;
    private static final char COL_START = 'a';

    private final Map<Location, Piece> board;

    public ChessBoard() {
        this.board = new HashMap<>();
        putNoble(1, Team.WHITE);
        putPawns(2, Team.WHITE);
        putPawns(7, Team.BLACK);
        putNoble(8, Team.BLACK);
    }

    public boolean canMove(Location now, Location destination) {
        Piece piece = board.get(now);
        boolean isNotSameTeam = isNotSameTeam(destination, piece);
        if (board.containsKey(now)) {
            return isNotSameTeam
                    && piece.canMove(now, destination)
                    && !piece.hasObstacle(board, now, destination);
        }
        return false;
    }

    private boolean isNotSameTeam(Location destination, Piece piece) {
        boolean isNotSameTeam = true;
        if (board.containsKey(destination)) {
            isNotSameTeam = !piece.isSameTeam(board.get(destination));
        }
        return isNotSameTeam;
    }

    private void putNoble(int row, Team team) {
        board.put(new Location(row, 'a'), new Rook(team));
        board.put(new Location(row, 'b'), new Knight(team));
        board.put(new Location(row, 'c'), new Bishop(team));
        board.put(new Location(row, 'd'), new Queen(team));
        board.put(new Location(row, 'e'), new King(team));
        board.put(new Location(row, 'f'), new Bishop(team));
        board.put(new Location(row, 'g'), new Knight(team));
        board.put(new Location(row, 'h'), new Rook(team));
    }

    private void putPawns(int row, Team team) {
        for (int i = MINIMUM_LINE; i < LIMIT_LINE; i++) {
            board.put(new Location(row, (char) (i + COL_START)), new Pawn(team));
        }
    }

    // 팀별 위치, 체스 정보를 가져온다.
    public Map<Location, Piece> giveMyPiece(Team team) {
        return board.keySet().stream()
                .filter(location -> board.get(location).isSameTeam(team))
                .collect(Collectors.toMap(location -> location, board::get));
    }

    public Map<Location, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public void move(Location now, Location destination) {
        if (board.containsKey(destination)) {
            board.remove(destination);
        }
        Piece piece = board.remove(now);
        board.put(destination, piece);
    }

    public Score calculateReducePawnScore(Team team) {
        int reducePawnScroe = 0;
        for (int row = MINIMUM_LINE; row < LIMIT_LINE; row++) {
            int fixRow = row;
            int sameRowPawnSize = (int) board.keySet().stream()
                    .filter(location -> location.is(fixRow))
                    .filter(location -> board.get(location).isSameTeam(team))
                    .filter(location -> board.get(location) instanceof Pawn)
                    .count();
            reducePawnScroe += sameRowPawnSize * PAWN_REDUCE_VALUE;
        }
        return new Score(reducePawnScroe);
    }

    public boolean isCorrectTeam(Location location, Team team) {
        return board.get(location).isSameTeam(team);
    }
}
