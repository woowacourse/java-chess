package chess.view;

import chess.domain.Board;
import chess.domain.Position;
import chess.exception.ImpossibleMoveException;
import chess.exception.PieceNotFoundException;

import java.util.Map;

import static chess.domain.Board.BOARD_SIZE;

public class OutputView {

    public static void printBoard(Map<Position, String> board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = BOARD_SIZE - 1; y >= 0; y--) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                Position currentPosition = Position.of(x, y);
                stringBuilder.append(board.getOrDefault(currentPosition, "."));
            }
            stringBuilder.append(System.lineSeparator());
        }
        System.out.println(stringBuilder);
    }

    public static void main(String[] args) throws PieceNotFoundException, ImpossibleMoveException {
        Board board = new Board();
        printBoard(board.nameGroupingByPosition());
        board.move(Position.of(1,1), Position.of(1,3));
        printBoard(board.nameGroupingByPosition());
        board.move(Position.of(6,0), Position.of(7,2));
        printBoard(board.nameGroupingByPosition());
    }
}
