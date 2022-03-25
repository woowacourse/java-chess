package chess.domain.generator;

import chess.domain.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.ArrayList;
import java.util.List;

public class CustomGenerator implements Generator {

    @Override
    public List<Piece> generate() {
        final List<Piece> pieces = new ArrayList<>();
        pieces.addAll(createRook());
        pieces.addAll(createKnight());
        pieces.addAll(createBishop());
        pieces.add(createQueen());
        pieces.add(createKing());
        return pieces;
    }

    private List<Piece> createRook() {
        return List.of(new Rook(new Position(1, 'a')), new Rook(new Position(1, 'h')));
    }

    private List<Piece> createKnight() {
        return List.of(new Knight(new Position(1, 'b')), new Knight(new Position(1, 'g')));
    }

    private List<Piece> createBishop() {
        return List.of(new Bishop(new Position(1, 'c')), new Bishop(new Position(1, 'f')));
    }

    private Piece createQueen() {
        return new Queen(new Position(1, 'd'));
    }

    private Piece createKing() {
        return new King(new Position(1, 'e'));
    }
}
