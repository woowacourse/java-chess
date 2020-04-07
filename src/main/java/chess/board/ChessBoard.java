package chess.board;

import java.util.*;
import java.util.stream.Collectors;

import chess.location.Location;
import chess.piece.type.Piece;
import chess.player.Player;
import chess.score.Calculatable;
import chess.score.PawnReduceScoreCalculable;
import chess.score.Score;
import chess.team.Team;

public class ChessBoard {
    private static final Calculatable pawnScoreCalculatable = new PawnReduceScoreCalculable();

    private final Map<Location, Piece> board;

    public ChessBoard(Map<Location, Piece> board) {
        this.board = board;
    }

    public boolean canMove(Location now, Location destination) {
        Piece piece = board.get(now);
        boolean isNotSameTeam = isNotSameTeam(destination, piece);
        if (board.containsKey(now)) {
            return isNotSameTeam
                    && piece.canMove(board, now, destination);
        }
        return false;
    }

    public boolean canNotMove(Location now, Location destination) {
        return canMove(now, destination) == false;
    }

    private boolean isNotSameTeam(Location destination, Piece piece) {
        if (board.containsKey(destination)) {
            return piece.isNotSameTeam(board.get(destination));
        }
        return true;
    }

    // 팀별 위치, 체스 정보를 가져온다.
    public List<Piece> giveMyPiece(Team team) {
        return board.values().stream()
                .filter(piece -> piece.isSameTeam(team))
                .collect(Collectors.toList());
    }

    public void move(Location now, Location destination) {
        board.remove(destination);
        Piece piece = board.remove(now);
        board.put(destination, piece);
    }

    public Score calculateReducePawnScore(Player player) {
        return pawnScoreCalculatable.calculate(this, player);
    }

    public boolean isNotCorrectTeam(Location location, Team team) {
        return board.get(location).isNotSame(team);
    }

    public boolean isNotExist(Location now) {
        return Objects.isNull(board.get(now));
    }

    public boolean isExistPieceIn(Location location) {
        return board.containsKey(location);
    }

    public Map<Location, Piece> getBoard() {
        return board;
    }

    public Piece getPieceIn(Location location) {
        return board.get(location);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Location, Piece> entry : board.entrySet()) {
            sb.append("location : " + entry.getKey().toString() + "\n");
            sb.append("piece : " + entry.getValue().toString() + "\n");
        }
        return "ChessBoard{" +
                "board=" + board +
                '}';
    }
}
