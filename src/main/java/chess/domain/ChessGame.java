package chess.domain;

public class ChessGame {
    private Board board;
    private boolean turn = false;

    public ChessGame() {
        board = BoardCreator.create();
    }

    public void play(String input) {
        String[] split = input.split(" ");
        if (split.length != 3) {
            throw new IllegalArgumentException();
        }
        if (!split[0].equals("move")) {
            throw new IllegalArgumentException();
        }
        board.play(parse(split[1]), parse(split[2]));
    }

    private Point parse(String destination) {
        String axis = "abcdefgh";
        int x = axis.indexOf(destination.charAt(0));
        int y = Integer.parseInt(String.valueOf(destination.charAt(1)));
        return Point.get(x, y);
    }
}
