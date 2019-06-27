package chess.domain.board;

import chess.domain.path.PathFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    void 플레이어_말_반환() {
        Piece rook = Rook.blackCreate();
        Piece rook2 = Rook.blackCreate();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(2), new Position(7)), rook);
        black.put(new Square(new Position(6), new Position(7)), rook2);
        Player blackPlayer = new DefaultPlayer(black);

        Optional<Piece> returnedRook = blackPlayer.getPiece(new Square(new Position(2), new Position(7)));
        returnedRook.ifPresent(piece -> assertThat(piece).isEqualTo(rook));

        Optional<Piece> returnedRook2 = blackPlayer.getPiece(new Square(new Position(6), new Position(7)));
        returnedRook2.ifPresent(piece -> assertThat(piece).isEqualTo(rook2));
    }
}
