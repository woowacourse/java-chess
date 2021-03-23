package chess.domain;

import chess.domain.board.*;
import chess.domain.piece.Team;

public class ChessGame {
    private Board board;
    private Team turnOwner;

    public void settingBoard() {
        board = BoardFactory.create();
        turnOwner = Team.WHITE;
    }

    public void move(String target, String destination) {
        turnOwner = board.movePiece(convertStringToPosition(target),
                convertStringToPosition(destination), turnOwner);
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
