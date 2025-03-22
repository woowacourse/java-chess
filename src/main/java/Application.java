import chess.ChessGame;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import chess.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;
import java.nio.file.LinkPermission;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();
        play(chessGame);
    }

    private static void play(ChessGame chessGame) {
        List<Piece> pieces = chessGame.getPieces();

        Map<Position,Piece> positionPieceMap = new HashMap<>();
        for(Piece piece : pieces){
            Position position = piece.getPosition();
            positionPieceMap.put(position,piece);
        }

        outputView.printChessMap(positionPieceMap);
    }
}
