package chess.domain;

import chess.domain.board.*;
import chess.domain.piece.Team;
import chess.view.OutputView;

import java.util.List;

public class ChessGame {
    private static final int HORIZONTAL_INDEX = 0;
    private static final int VERTICAL_INDEX = 1;
    private static final int OPTION_FINISH = 2;
    private static final int TARGET_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;
    private static final String DELIMITER = " ";

    private Board board;
    private boolean start;
    private boolean end;

    public void settingBoard() {
        board = BoardFactory.create();
        start = true;
        end = false;
    }

    public void start() {
        settingBoard();
        board.applyStatus();
    }

    public void move(String input) {
        String[] splitInput = input.split(DELIMITER);

        board.movePiece(convertStringToPosition(splitInput[TARGET_INDEX])
                , convertStringToPosition(splitInput[DESTINATION_INDEX]));
        board.applyStatus();

        end = board.isGameOver();
    }

    public void move(String target, String destination) {
        board.movePiece(convertStringToPosition(target)
                , convertStringToPosition(destination));
        board.applyStatus();

        end = board.isGameOver();
    }

    public List<String> findMovablePosition(String target) {
        return board.movablePositions(convertStringToPosition(target));
    }

    public void status() {
        OutputView.printStatus(board.score(Team.BLACK), board.score(Team.WHITE));
    }

    public void end() {
        end = true;
    }

    private Position convertStringToPosition(String input) {
        return Position.of(Horizontal.find(input.substring(HORIZONTAL_INDEX, VERTICAL_INDEX)),
                Vertical.find(input.substring(VERTICAL_INDEX, OPTION_FINISH)));
    }

    public void changeEnd() {
        end = !end;
    }

    public boolean isBeforeEnd() {
        return !end;
    }

    public boolean isBeforeStart() {
        return !start;
    }

    public Team turn() {
        return board.turn();
    }

    public Board getBoard() {
        return board;
    }

    public BoardStatus boardStatus() {
        return board.getBoardStatus();
    }
}
