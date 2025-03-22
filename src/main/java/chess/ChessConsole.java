package chess;

import chess.view.InputView;
import chess.view.ResultView;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class ChessConsole {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        Board board = BoardFactory.makeBoard();

        Deque<Team> deque = new ArrayDeque<>(List.of(Team.WHITE, Team.BLACK));
        while (!board.isFinished()){
            Team currentTeam = deque.poll();
            resultView.showBoard(board);
            resultView.showTeam(currentTeam);
            List<Position> positions = inputView.readPosition();

            board.move(positions.getFirst(), positions.getLast(), currentTeam);
            deque.offer(currentTeam);
            resultView.showBoard(board);
        }
    }
}
