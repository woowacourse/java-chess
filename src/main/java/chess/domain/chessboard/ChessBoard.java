package chess.domain.chessboard;

import chess.domain.position.Direction;
import chess.domain.chesspiece.*;
import chess.domain.position.Position;

import java.util.*;

import static chess.domain.chesspiece.Role.*;

public class ChessBoard {

    private final Map<Position, Piece> chessBoard;

    private ChessBoard(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoard initializeChessBoard() {
        return new ChessBoard(ChessBoardGenerator.initializeBoard());
    }

    public void move(Position source, Position target) {
        Piece piece = chessBoard.get(source);
        piece.getRoute(source, target)
             .forEach(this::checkObstacle);

        if (piece.isPawn()) {
            checkPawnMoving(source, target);
        }
        checkTeam(target, piece);

        chessBoard.put(source, new Empty());
        chessBoard.put(target, piece);
    }

    private void checkPawnMoving(Position source, Position target) {
        if (Direction.isUpDown(source, target)) {
            checkObstacle(target);
            return;
        }
        checkIsEmpty(target);
    }

    private void checkObstacle(Position position) {
        Piece obstacle = chessBoard.get(position);
        if (obstacle.getRole() != EMPTY) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    private void checkIsEmpty(Position target) {
        Piece targetPiece = chessBoard.get(target);
        if(targetPiece.getRole() == EMPTY) {
            throw new IllegalArgumentException("폰은 공격할 때만 대각선으로 이동할 수 있습니다.");
        }
    }

    private void checkTeam(Position target, Piece piece) {
        Piece targetPiece = chessBoard.get(target);
        if (piece.isTeam(targetPiece)) {
            throw new IllegalArgumentException("같은 팀이 있는 곳으로는 이동할 수 없습니다.");
        }
    }

    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }
}
