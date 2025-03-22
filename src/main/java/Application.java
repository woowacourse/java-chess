import chess.board.Position;
import chess.piece.Piece;
import chess.piece.Pieces;
import console.Console;
import console.Input;
import console.Output;
import console.util.ConvertPosition;
import java.util.Set;

public class Application {

    private final Console console = new Console(new Input(), new Output());

    public static void main(String[] args) {
        Application chess = new Application();
        chess.start();
    }

    private void start() {
        console.start();

        Pieces pieces = Pieces.generate();

        while(true){
            console.display(pieces);
            console.turn(pieces.color());
            pieces = move(pieces);
        }
    }

    private Pieces move(Pieces pieces) {
        String[] movePosition = console.read().split(" ");
        Position source = new ConvertPosition().convert(movePosition[0]);
        Position destination = new ConvertPosition().convert(movePosition[1]);

        Piece piece = pieces.get(source);
        Set<Piece> piecesSet = pieces.toSet();
        piecesSet.remove(piece);
        piecesSet.add(piece.move(destination, piecesSet));

        return new Pieces(pieces.color().opposite(), piecesSet);
    }
}
