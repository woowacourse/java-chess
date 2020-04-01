package chess.board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import chess.location.Location;
import chess.location.Row;
import chess.piece.type.Pawn;
import chess.piece.type.Piece;
import chess.score.Score;
import chess.team.Team;

import static chess.board.ChessBoardCreater.*;

public class ChessBoard {
    private static final double PAWN_REDUCE_VALUE = 0.5;

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
    public Map<Location, Piece> giveMyPiece(Team team) {
        return board.keySet().stream()
                .filter(location -> board.get(location).isSameTeam(team))
                .collect(Collectors.toMap(location -> location, board::get));
    }

    public Map<Location, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public void move(Location now, Location destination) {
        board.remove(destination);
        Piece piece = board.remove(now);
        board.put(destination, piece);
    }

    /*
    TODO : 여기에서 PAWN을 가지고 있는 것이 옳은가 ? 매개변수로 어떤 Piece인지 입력 받고
    같은 라인의 Piece의 수만을 반환받는 것이 더 투명한 ChessBoard를 만드는 것이 아닌가 ?
    여기도 응집도를 높이고 결합도를 낮출 수 있을지 고민해보자.
    */
    public Score calculateReducePawnScore(Team team) {
        int reducePawnScroe = 0;
        for (int row = MINIMUM_LINE; row < LIMIT_LINE; row++) {
            Row fixRow = Row.of(row);
            int sameRowPawnSize = (int) board.keySet().stream()
                    .filter(location -> location.isSameRow(fixRow))
                    .filter(location -> board.get(location).isSameTeam(team))
                    .filter(location -> board.get(location) instanceof Pawn)
                    .count();
            reducePawnScroe += sameRowPawnSize * PAWN_REDUCE_VALUE;
        }
        return new Score(reducePawnScroe);
    }

    public boolean isNotCorrectTeam(Location location, Team team) {
        return board.get(location).isNotSame(team);
    }

    public boolean isNotExist(Location now) {
        return Objects.isNull(board.get(now));
    }
}
