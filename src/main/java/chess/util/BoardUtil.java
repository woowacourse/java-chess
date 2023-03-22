package chess.util;

import chess.domain.board.Position;
import chess.domain.pieces.Bishop;
import chess.domain.pieces.King;
import chess.domain.pieces.Knight;
import chess.domain.pieces.Name;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Place;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rook;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardUtil {

    public List<String> compressBoard(final Map<Position, Piece> board) {
        StringBuilder positions = new StringBuilder();
        StringBuilder pieces = new StringBuilder();

        board.entrySet()
                .forEach(entry -> {
                    positions.append(String.valueOf(entry.getKey().getCol()) + entry.getKey().getRow());
                    pieces.append(entry.getValue().getName());
                });

        return List.of(positions.toString(), pieces.toString());
    }

    public Map<Position, Piece> unCompressBoard(final List<String> compressedBoard) {
        Map<Position, Piece> chessBoard = new HashMap<>();

        String positions = compressedBoard.get(0);
        String pieces = compressedBoard.get(1);

        for (int i = 0; i < positions.length(); i += 2) {
            String rawPosition = positions.substring(i, i + 2);
            Position position = Position.from(rawPosition);

            String rawPiece = pieces.substring(i / 2, i / 2 + 1);
            Piece piece = parsePiece(rawPiece);

            chessBoard.put(position, piece);
        }

        return chessBoard;
    }

    private Piece parsePiece(final String name) {
        if (name.equals("r") || name.equals("R")) {
            return new Rook(new Name(name));
        }

        if (name.equals("n") || name.equals("N")) {
            return new Knight(new Name(name));
        }

        if (name.equals("b") || name.equals("B")) {
            return new Bishop(new Name(name));
        }

        if (name.equals("q") || name.equals("Q")) {
            return new Queen(new Name(name));
        }

        if (name.equals("p") || name.equals("P")) {
            return new Pawn(new Name(name));
        }

        if (name.equals("k") || name.equals("K")) {
            return new King(new Name(name));
        }

        return new Place();
    }
}
