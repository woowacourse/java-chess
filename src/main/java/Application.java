import chess.board.Position;
import chess.piece.Piece;
import chess.piece.Pieces;
import console.Console;
import console.Input;
import console.Output;
import console.util.ConvertPosition;
import java.util.Set;
import java.util.function.Supplier;

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
            Pieces finalPieces = pieces;
            pieces = process(() -> move(finalPieces));
        }
    }

    private Pieces move(Pieces pieces) {
        String[] movePosition = console.read().split(" ");
        Position source = new ConvertPosition().convert(movePosition[0]);
        Position destination = new ConvertPosition().convert(movePosition[1]);

        validateColor(pieces, source);

        Piece piece = pieces.get(source);
        Set<Piece> piecesSet = pieces.toSet();
        piecesSet.remove(piece);

        if(piece.isDiffernetColor(pieces.get(destination).color())){
            piecesSet.remove(pieces.get(destination));
        }

        piecesSet.add(piece.move(destination, piecesSet));

        return new Pieces(pieces.color().opposite(), piecesSet);
    }

    private static void validateColor(Pieces pieces, Position source) {
        if(!pieces.isSameColor(source)){
            throw new IllegalArgumentException("[ERROR] 같은 팀 말만 움직이기!");
        }
    }

    public Pieces process(Supplier<Pieces> action) {
        while(true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                console.retry(e);
            }
        }
    }
}
