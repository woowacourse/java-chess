package chess.service;

import chess.domains.board.Board;
import chess.domains.position.Position;

public class ChessService {
    public static double calculateScore(Board board) {
        return board.calculateScore();
    }

    public static void move(Board board, String source, String target) {
        Position sourcePosition = Position.ofPositionName(source);
        Position targetPosition = Position.ofPositionName(target);

        board.move(sourcePosition, targetPosition);
    }
}
