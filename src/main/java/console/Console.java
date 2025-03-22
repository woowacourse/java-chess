package console;

import chess.piece.Pieces;

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

    public void display(Pieces pieces) {
        output.display(pieces);
    }
}
