package chess.domain.game;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class ActivePiecesTest {

    private ActivePieces activePieces;

    @BeforeEach
    void setUp() {
        activePieces = new ActivePieces();
    }

    @Test
    void 흑백_모두_폰8개가_생성된다() {
        List<Piece> actual = activePieces.findAll();

        List<Piece> expectedPawns = new ArrayList<>();
        IntStream.range(0, 8)
                .peek(fileIdx -> expectedPawns.add(Pawn.of(BLACK, fileIdx)))
                .forEach(fileIdx -> expectedPawns.add(Pawn.of(WHITE, fileIdx)));

        assertThat(actual).containsOnlyOnceElementsOf(expectedPawns);
    }

    @Test
    void 흑백_모두_킹1개_퀸1개_룩2개_비숍2개_나이트2개가_생성된다() {
        List<Piece> actual = activePieces.findAll();

        List<Piece> expectedStrongmen = List.of(
                new King(BLACK), new Queen(BLACK), new King(WHITE), new Queen(WHITE),
                Rook.ofLeft(BLACK), Rook.ofRight(BLACK), Rook.ofLeft(WHITE), Rook.ofRight(WHITE),
                Bishop.ofLeft(BLACK), Bishop.ofRight(BLACK), Bishop.ofLeft(WHITE), Bishop.ofRight(WHITE),
                Knight.ofLeft(BLACK), Knight.ofRight(BLACK), Knight.ofLeft(WHITE), Knight.ofRight(WHITE));

        assertThat(actual).containsOnlyOnceElementsOf(expectedStrongmen);
    }
}
