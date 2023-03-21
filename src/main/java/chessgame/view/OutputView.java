package chessgame.view;

import chessgame.domain.board.Board;
import chessgame.domain.piece.Coordinate;
import chessgame.domain.square.Square;

import java.util.Map;

public class OutputView {

    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다." + System.lineSeparator() +
            "> 게임 시작 : start" + System.lineSeparator() +
            "> 게임 종료 : end" + System.lineSeparator() +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final int BOARD_RANK = 8;
    private static final int BOARD_FILE = 8;

    public void printGameStartMessage() {
        System.out.println(GAME_START_MESSAGE);
    }

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
            stringBuilder.append(PieceMapper.getTarget(square));
        }
        stringBuilder.append(System.lineSeparator());
        return stringBuilder;
    }

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }
}
