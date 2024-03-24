package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ChessGame {

    public final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void movePiece(Position from, Position to) {
        List<Position> movablePositions = generateMovablePositions(from);
        if (movablePositions.contains(to)) {
            board.movePiece(from, to);
            return;
        }
        throw new IllegalArgumentException("기물을 해당 위치로 이동시킬 수 없습니다.");
    }

    private List<Position> generateMovablePositions(Position position) {
        Piece piece = board.findPieceByPosition(position);
        Map<Direction, Queue<Position>> candidateAllPositions = piece.generateAllDirectionPositions(position);
        return new PositionsFilter().generateValidPositions(candidateAllPositions, piece, board);
    }


    public Board getBoard() {
        return board;
    }
}
