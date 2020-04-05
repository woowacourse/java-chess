package chess.board;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import chess.location.Col;
import chess.location.Location;
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

    /*
    TODO : 여기에서 PAWN을 가지고 있는 것이 옳은가 ? 매개변수로 어떤 Piece인지 입력 받고
    같은 라인의 Piece의 수만을 반환받는 것이 더 투명한 ChessBoard를 만드는 것이 아닌가 ?
    여기도 응집도를 높이고 결합도를 낮출 수 있을지 고민해보자.
    */
    public Score calculateReducePawnScore(Team team) {
        double reducePawnScroe = 0;
        for (int col = COL_START; col <= COL_END; col++) {
            Col fixCol = Col.of(col);

            int sameColPawnSize = calculatePawnSameColSize(team, fixCol);
            if (sameColPawnSize == 1) {
                continue;
            }
            reducePawnScroe += (sameColPawnSize * PAWN_REDUCE_VALUE);
        }
        return new Score(reducePawnScroe);
    }

    private int calculatePawnSameColSize(Team team, Col fixCol) {
        int count = 0;
        for (Map.Entry<Location, Piece> element : board.entrySet()) {
            if (element.getKey().isSame(fixCol)
                    && element.getValue().isSameTeam(team)
                    && element.getValue() instanceof Pawn
            ) {
                count++;
            }
        }
        return count;
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
}
