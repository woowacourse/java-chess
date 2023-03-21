package chessgame.view;

import chessgame.domain.board.Board;
import chessgame.domain.piece.Coordinate;
import chessgame.domain.square.Square;

import java.util.Map;

public class OutputView {

    private static final int BOARD_RANK = 8;
    private static final int BOARD_FILE = 8;

    public void printBoard(Board board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int rank = 0; rank < BOARD_RANK; rank++) {
            stringBuilder.insert(0, makeRank(board.getBoard(), rank));
        }
        System.out.println(stringBuilder);
    }

    private StringBuilder makeRank(Map<Coordinate, Square> board, int rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int file = 0; file < BOARD_FILE; file++) {
            Square square = board.get(new Coordinate(rank, file));
            stringBuilder.append(PieceTypeMapper.getTarget(square));
        }
        return stringBuilder;
    }

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }
}
