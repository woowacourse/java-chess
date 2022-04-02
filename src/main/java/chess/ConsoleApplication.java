package chess;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import chess.domain.piece.Piece;
import java.util.Map;

public class ConsoleApplication {

    public static void main(String[] args) {
        final Map<Position, Piece> pieces = (new CreateCompleteBoardStrategy()).createPieces();
        Chess chess = new Chess();
        chess.run(new Board(pieces));
    }
}
