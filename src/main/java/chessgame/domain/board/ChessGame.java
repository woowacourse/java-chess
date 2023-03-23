package chessgame.domain.board;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Camp;

public class ChessGame {

    private final Board board;
    private Camp turn;

    public ChessGame() {
        this.board = new Board();
        this.turn = Camp.WHITE;
    }

    public void move(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        checkTurn(startCoordinate);
        board.move(startCoordinate, endCoordinate);
        changeTurn();
    }

    private void checkTurn(final Coordinate coordinate) {
        if (board.checkCamp(coordinate, turn)) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 현재 차례가 아닌 팀의 기물입니다.");
    }

    private void changeTurn() {
        turn = turn.change();
    }

    public Board getBoard() {
        return board;
    }
}
