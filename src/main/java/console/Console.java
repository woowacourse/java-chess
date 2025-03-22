package console;

import chess.piece.Color;
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

    public void turn(Color color) {
        output.display(color);
    }

    public void retry(IllegalArgumentException e) {
        output.display(e);
    }
}
