package console;

import chess.board.Board;
import chess.board.Position;

public class Output {

    public void start() {
        System.out.println("체스 게임을 시작합니다!!");
    }

    public void display(Board board) {
        for (Position position : board.positions()) {

        }
    }
}
