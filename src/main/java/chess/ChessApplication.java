package chess;

import chess.controller.ChessController;
import chess.domain.board.Position;

import java.util.List;

public class ChessApplication {
    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        final List<Position> allPositions = Position.getAllPositions();
        for (Position position: allPositions) {
            System.out.println(position.getX() + "-" + position.getY());
        }
        chessController.run();
    }
}
