package chess.score;

import chess.board.ChessBoard;
import chess.location.Col;
import chess.location.Location;
import chess.piece.type.Pawn;
import chess.piece.type.Piece;
import chess.player.Player;
import chess.team.Team;

import java.util.Map;

import static chess.board.ChessBoardCreater.COL_END;
import static chess.board.ChessBoardCreater.COL_START;

// TODO : ChessBoard 제거 하고 Player에서 받을 수 있음
public class PawnReduceScoreCalculable implements Calculatable {
    private static final double PAWN_REDUCE_VALUE = 0.5;

    @Override
    public Score calculate(ChessBoard chessBoard, Player player) {
        double reducePawnScroe = 0;
        for (int col = COL_START; col <= COL_END; col++) {
            Col fixCol = Col.of(col);

            int sameColPawnSize = calculatePawnSameColSize(chessBoard, player.getTeam(), fixCol);
            if (sameColPawnSize == 1) {
                continue;
            }
            reducePawnScroe += (sameColPawnSize * PAWN_REDUCE_VALUE);
        }
        return new Score(reducePawnScroe);
    }

    private int calculatePawnSameColSize(ChessBoard chessBoard, Team team, Col fixCol) {
        int count = 0;
        Map<Location, Piece> board = chessBoard.getBoard();

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
}
