package chess;

import chess.piece.Empty;
import chess.piece.Piece;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chessboard {
    private final Map<Square, Piece> board;

    public Chessboard() {
        this.board = new HashMap<>();
        for (File file : File.values()) {
            putFile(file);
        }
    }

    private void putFile(File file) {
        for (Rank rank : Rank.values()) {
            board.put(new Square(file, rank), new Empty());
        }
    }

    public void putPiece(Rank rank, List<Piece> pieces) {
        List<File> values = Arrays.asList(File.values());
        for (int i = 0, end = values.size(); i < end; i++) {
            board.put(new Square(values.get(i), rank), pieces.get(i));
        }
    }

    public Piece getPieceAt(Square square){
        return board.get(square);
    }
}
