package console;

import chess.board.Board;

public class Console {
    private final Input input;
    private final Output output;

    public Console(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public String read(){
        return input.read();
    }

    public void start() {
        output.start();
    }

    public void display(Board board) {
        output.display(board);
    }
}
