package chess.domain.board;

import chess.domain.pieces.Bishop;
import chess.domain.pieces.King;
import chess.domain.pieces.Queen;
import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import java.util.Map;

public class GameOverBoardFactory implements BoardFactory {
    @Override
    public Board createBoard() {
        Map<Square, Piece> initialArrangement = Map.of(
                Square.of(File.A, Rank.ONE), new Bishop(Color.BLACK),
                Square.of(File.B, Rank.TWO), new Queen(Color.BLACK),
                Square.of(File.B, Rank.EIGHT), new King(Color.WHITE)
        );
        return new Board(initialArrangement);
    }
}
