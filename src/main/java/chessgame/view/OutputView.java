package chessgame.view;

import chessgame.domain.chessgame.Board;
import chessgame.domain.chessgame.Camp;
import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Piece;

import java.util.Map;

public class OutputView {

    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다." + System.lineSeparator() +
            "> 게임 시작 : start" + System.lineSeparator() +
            "> 게임 종료 : end" + System.lineSeparator() +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String GAME_STATUS = "%s 점수 : %.1f점" + System.lineSeparator();
    private static final int BOARD_RANK = 8;
    private static final int BOARD_FILE = 8;

    public void printGameStartMessage() {
        System.out.println(GAME_START_MESSAGE);
    }

    public void printBoard(final Board board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int rank = 0; rank < BOARD_RANK; rank++) {
            stringBuilder.insert(0, makeRank(board.getBoard(), rank));
        }
        stringBuilder.insert(0, System.lineSeparator());
        System.out.println(stringBuilder);
    }

    private StringBuilder makeRank(final Map<Coordinate, Piece> board, final int rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int file = 0; file < BOARD_FILE; file++) {
            Piece piece = board.get(Coordinate.fromOnBoard(rank, file));
            stringBuilder.append(PieceMapper.getTarget(piece));
        }
        stringBuilder.append(System.lineSeparator());
        return stringBuilder;
    }

    public void printGameStatus(final Map<Camp, Double> status) {
        status.forEach((key, value) -> System.out.printf(GAME_STATUS,
                CampMapper.getTarget(key), value));
    }

    public void printGameResult(final Board board,
                                final Map<Camp, Double> status) {
        printGameStatus(status);
        printBoard(board);
    }

    public void printExceptionMessage(final String message) {
        System.out.println(message);
    }
}
