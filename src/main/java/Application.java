import chess.board.Board;
import chess.piece.Pieces;
import console.Console;
import console.Input;
import console.Output;

public class Application {

    private final Console console = new Console(new Input(), new Output());

    public static void main(String[] args) {
        Application chess = new Application();
        chess.start();
    }

    private void start() {
        console.start();

        Board board = Board.generate();
        Pieces pieces = Pieces.generate(board);

        console.display(pieces);
    }
}
