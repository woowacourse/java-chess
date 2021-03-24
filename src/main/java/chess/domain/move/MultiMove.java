package chess.domain.move;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class MultiMove implements Movable {

    @Override
    public List<Position> allMovablePosition(Piece piece, Board board, int[] rowDir, int[] colDir) {
        List<Position> movablePositions = new ArrayList<>();

        for (int i = 0; i < rowDir.length; i++) {
            addMovablePositions(piece, board, movablePositions, rowDir[i], colDir[i]);
        }

        return movablePositions;
    }

    private void addMovablePositions(Piece piece, Board board, List<Position> movablePositions, int rowDir, int colDir) {
        int distance = 1;
        Position currentPosition = piece.getPosition();
        Position nextPosition = currentPosition.next(rowDir * distance, colDir * distance);
        Team team = piece.getTeam();

        while (canMove(board, movablePositions, nextPosition, team)) {
            distance++;
            nextPosition = currentPosition.next(rowDir * distance, colDir * distance);
        }
    }

    private boolean canMove(Board board, List<Position> movablePositions, Position nextPosition, Team team) {
        if (!board.validateRange(nextPosition)) {
            return false;
        }
        if (board.piecesByTeam(team).containByPosition(nextPosition)) {
            return false;
        }
        if (board.piecesByTeam(Team.enemyTeam(team)).containByPosition(nextPosition)) {
            movablePositions.add(nextPosition);
            return false;
        }
        movablePositions.add(nextPosition);
        return true;
    }
}
