package chess.domain;

import chess.domain.board.*;
import chess.domain.piece.Team;

import static chess.controller.ChessController.boardFactory;

public class ChessGame {
    private Board board;
    private Team turnOwner;

    public void settingBoard(String input) {
        board = boardFactory.create();
        turnOwner = Team.WHITE;
    }

    public void move(String command) {
        String[] commands = command.split(" ");
        turnOwner = board.movePiece(convertStringToPosition(commands[1]),
                convertStringToPosition(commands[2]), turnOwner);
    }

    public double status(Team team) {
        return board.calculateScore(team);
    }

    public void end(String input) {
        System.exit(0);
    }

    private Position convertStringToPosition(String input) {
        return Position.of(Horizontal.find(input.substring(0, 1)),
                Vertical.find(input.substring(1, 2)));
    }

    public Board getBoard() {
        return board;
    }
}
