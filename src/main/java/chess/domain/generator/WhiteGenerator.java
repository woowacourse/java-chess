package chess.domain.generator;

import chess.domain.Position;
import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.List;

public class WhiteGenerator implements Generator {

    @Override
    public List<Piece> generate() {
        final List<Piece> pieces = new ArrayList<>();
        pieces.addAll(createPawn());
        pieces.addAll(createRook());
        pieces.addAll(createKnight());
        pieces.addAll(createBishop());
        pieces.add(createQueen());
        pieces.add(createKing());
        return pieces;
    }

    private List<Piece> createPawn() {
        final List<Piece> pawns = new ArrayList<>();
        for (int i = 0; i < PAWN_COUNT_PER_PLAYER; i++) {
            pawns.add(new Pawn(Position.of(2, (char) ('a' + i))));
        }
        return pawns;
    }

    private List<Piece> createRook() {
        return List.of(new Rook(Position.of(1, 'a')), new Rook(Position.of(1, 'h')));
    }

    private List<Piece> createKnight() {
        return List.of(new Knight(Position.of(1, 'b')), new Knight(Position.of(1, 'g')));
    }

    private List<Piece> createBishop() {
        return List.of(new Bishop(Position.of(1, 'c')), new Bishop(Position.of(1, 'f')));
    }

    private Piece createQueen() {
        return new Queen(Position.of(1, 'd'));
    }

    private Piece createKing() {
        return new King(Position.of(1, 'e'));
    }
}
