package view;

import domain.board.Board;
import domain.square.Square;
import domain.board.Rank;

public class OutputView {

    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다." + System.lineSeparator() +
            "> 게임 시작 : start" + System.lineSeparator() +
            "> 게임 종료 : end" + System.lineSeparator() +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";

    public void printBoard(Board board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Rank rank : board.getRanks()) {
            stringBuilder.insert(0, makeRank(rank));
        }
        System.out.println(stringBuilder);
    }

    private StringBuilder makeRank(Rank rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Square square : rank.getPieces()) {
            stringBuilder.append(PieceTypeMapper.getTarget(square));
        }
        stringBuilder.append(System.lineSeparator());
        return stringBuilder;
    }

    public void printGameStartMessage() {
        System.out.println(GAME_START_MESSAGE);
    }

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }
}
