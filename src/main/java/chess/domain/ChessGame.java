package chess.domain;

import chess.domain.board.*;
import chess.domain.piece.Team;

public class ChessGame {
    private Board board;

    public void settingBoard() {
        board = BoardFactory.create();
    }

    public void move(String target, String destination) {
        board.movePiece(convertStringToPosition(target),
                convertStringToPosition(destination));
    }

    public double status(Team team) {
        return board.calculateScore(team);
    }

    public void end() {
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
