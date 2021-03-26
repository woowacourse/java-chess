package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import chess.domain.piece.Team;

import static chess.controller.ChessController.boardFactory;

public class ChessGame {
    private static final int TARGET_POSITION_INDEX = 1;
    private static final int DESTINATION_POSITION_INDEX = 2;
    private static final String DELIMITER = " ";

    private Board board;
    private Team turnOwner;

    public void settingBoard() {
        board = boardFactory.create();
        turnOwner = Team.WHITE;
    }

    public void move(String command) {
        boardInitializeCheck();
        String[] commands = command.split(DELIMITER);
        turnOwner = board.movePiece(convertStringToPosition(commands[TARGET_POSITION_INDEX]),
                convertStringToPosition(commands[DESTINATION_POSITION_INDEX]), turnOwner);
    }

    public double status(Team team) {
        boardInitializeCheck();
        return board.calculateScore(team);
    }

    public void end() {
        System.exit(0);
    }

    private Position convertStringToPosition(String input) {
        return Position.of(Horizontal.find(input.substring(0, 1)),
                Vertical.find(input.substring(1, 2)));
    }

    private void boardInitializeCheck() {
        if (board == null) {
            throw new IllegalArgumentException("보드가 세팅되지 않았습니다. start 명령어를 입력해주세요.");
        }
    }

    public Board getBoard() {
        return board;
    }
}
