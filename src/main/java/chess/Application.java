package chess;

import chess.model.BoardFactory;
import chess.model.ChessBoard;
import chess.model.element.Color;
import chess.model.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        BoardFactory boardFactory = new BoardFactory();
        ChessBoard board = boardFactory.generateBoard();
        Color color = Color.WHITE;
        while (true) {
            color = color.opposite();
            OutputView.printChessBoard(board);
            List<Position> inputPositions = InputView.readMovePositions();
            board.updatePosition(inputPositions.get(0), inputPositions.get(1), color);
        }
    }
}
