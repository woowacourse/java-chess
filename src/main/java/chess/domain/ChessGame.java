package chess.domain;

import chess.domain.pieces.Piece;

import java.util.Map;

public class ChessGame {
    private Board board;
    private ChessTeam turn;

    public ChessGame() {
        board = BoardCreator.create();
        turn = ChessTeam.WHITE;
    }

    public boolean play(String input) {
        String[] split = input.split(" ");
        if (split.length != 3) {
            throw new IllegalArgumentException();
        }
        if (!split[0].equals("move")) {
            throw new IllegalArgumentException();
        }

        board.play(parse(split[1]), parse(split[2]), turn);
        turn = turn.change();
        return checkKing();
    }

    private boolean checkKing() {
        return board.check(turn);
    }

    public ChessResult winner() {
        return result(turn.change());
    }

    private Point parse(String destination) {
        String axis = "abcdefgh";
        int x = axis.indexOf(destination.charAt(0)) + 1;
        int y = Integer.parseInt(String.valueOf(destination.charAt(1)));
        return Point.get(x, y);
    }

    public Piece get(Point point) {
        return board.get(point);
    }

    public ChessResult result(ChessTeam white) {
        return new ChessResult(board.result(white));
    }

    public Map<String, String> status() {
        return board.status();
    }
}
