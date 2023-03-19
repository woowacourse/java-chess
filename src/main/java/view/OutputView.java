package view;

import domain.board.ChessGame;
import domain.square.Square;
import domain.board.Rank;

public final class OutputView {

    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다." + System.lineSeparator() +
            "> 게임 시작 : start" + System.lineSeparator() +
            "> 게임 종료 : end" + System.lineSeparator() +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    public static final String GAME_END_MESSAGE = "게임을 종료합니다.";

    public void printBoard(final ChessGame chessGame) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Rank rank : chessGame.getBoard().getRanks()) {
            stringBuilder.insert(0, makeRank(rank));
        }
        System.out.println(stringBuilder);
    }

    private StringBuilder makeRank(final Rank rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Square square : rank.getSquares()) {
            stringBuilder.append(PieceTypeMapper.getTarget(square));
        }
        stringBuilder.append(System.lineSeparator());
        return stringBuilder;
    }

    public void printGameStartMessage() {
        System.out.println(GAME_START_MESSAGE);
    }

    public void printGameEndMessage() {
        System.out.println(GAME_END_MESSAGE);
    }

    public void printExceptionMessage(final String message) {
        System.out.println(message);
    }
}
