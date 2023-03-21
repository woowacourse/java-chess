package view;

import domain.board.Board;
import domain.board.ChessGame;
import domain.piece.move.Coordinate;
import domain.square.Square;

import java.util.Map;

public final class OutputView {

    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다." + System.lineSeparator() +
            "> 게임 시작 : start" + System.lineSeparator() +
            "> 게임 종료 : end" + System.lineSeparator() +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    public static final String GAME_END_MESSAGE = "게임을 종료합니다.";

    public void printBoard(final ChessGame chessGame) {
        Board board = chessGame.getBoard();
        Map<Coordinate, Square> squareLocations = board.getSquareLocations();

        printSquares(squareLocations);
    }

    // TODO: 뷰가 도메인에 직접적으로 의존하지 않도록 변경
    private void printSquares(final Map<Coordinate, Square> squareLocations) {
        StringBuilder allSquares = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            StringBuilder oneRank = makeRank(squareLocations, i);
            allSquares.insert(0, oneRank);
            allSquares.insert(0, System.lineSeparator());
        }
        System.out.println(allSquares);
    }

    private static StringBuilder makeRank(
            final Map<Coordinate, Square> squareLocations,
            final int rankNumber
    ) {
        StringBuilder rank = new StringBuilder();
        for (int col = 0; col < 8; col++) {
            Coordinate coordinate = new Coordinate(rankNumber, col);
            rank.append(PieceTypeMapper.getTarget(squareLocations.get(coordinate)));
        }
        return rank;
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
