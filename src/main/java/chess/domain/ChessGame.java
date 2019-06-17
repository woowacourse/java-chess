package chess.domain;

public class ChessGame {
    ChessBoard chessBoard = new ChessBoard();
    boolean turn = false;

    public void play(String input) {
        String[] split = input.split(" ");
        if (split.length != 3) {
            throw new IllegalArgumentException();
        }

        if(!split[0].equals("play")){
            throw new IllegalArgumentException();
        }
    }
}
