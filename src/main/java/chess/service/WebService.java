package chess.service;

import chess.domains.board.Board;
import chess.domains.piece.PieceColor;
import chess.domains.position.Position;

public class WebService {

    public static String turn(Board board) {
        return board.getTeamColor().name();
    }

    public static void move(Board board, String source, String target) {
        Position sourcePosition = Position.ofPositionName(source);
        Position targetPosition = Position.ofPositionName(target);
        board.move(sourcePosition, targetPosition);
    }

    public static double calculateScore(Board board, PieceColor pieceColor) {
        return board.calculateScore(pieceColor);
    }
}
