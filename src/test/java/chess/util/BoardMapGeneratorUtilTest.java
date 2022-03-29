package chess.util;

import static chess.domain.board.piece.Color.BLACK;
import static chess.domain.board.piece.Color.WHITE;
import static chess.domain.board.piece.PieceType.BISHOP;
import static chess.domain.board.piece.PieceType.KING;
import static chess.domain.board.piece.PieceType.KNIGHT;
import static chess.domain.board.piece.PieceType.PAWN;
import static chess.domain.board.piece.PieceType.QUEEN;
import static chess.domain.board.piece.PieceType.ROOK;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.piece.NonPawn;
import chess.domain.board.piece.Pawn;
import chess.domain.board.piece.Piece;
import chess.domain.board.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class BoardMapGeneratorUtilTest {

    private static Map<Position, Piece> boardMap;

    @BeforeAll
    static void setup() {
        boardMap = BoardMapGeneratorUtil.initFullChessBoard();
    }

    @Test
    void 모든_종류의_체스말이_흑백_모두에_대해_생성() {
        List<Piece> actual = new ArrayList<>(boardMap.values());
        List<Piece> expected = List.of(new Pawn(BLACK), new Pawn(WHITE),
                new NonPawn(BLACK, KNIGHT), new NonPawn(WHITE, KNIGHT),
                new NonPawn(BLACK, BISHOP), new NonPawn(WHITE, BISHOP),
                new NonPawn(BLACK, ROOK), new NonPawn(WHITE, ROOK),
                new NonPawn(BLACK, QUEEN), new NonPawn(WHITE, QUEEN),
                new NonPawn(BLACK, KING), new NonPawn(WHITE, KING));

        assertThat(actual).containsAll(expected);
    }

    @Test
    void 체스말은_총_32개_생성() {
        int actual = boardMap.values()
                .size();

        assertThat(actual).isEqualTo(32);
    }

    @Test
    void 백색_폰_8개_생성() {
        long actual = boardMap.values()
                .stream()
                .filter(piece -> piece.hasColorOf(WHITE))
                .filter(piece -> piece.hasTypeOf(PAWN))
                .count();

        assertThat(actual).isEqualTo(8);
    }

    @Test
    void 흑색_폰_8개_생성() {
        long actual = boardMap.values()
                .stream()
                .filter(piece -> piece.hasColorOf(BLACK))
                .filter(piece -> piece.hasTypeOf(PAWN))
                .count();

        assertThat(actual).isEqualTo(8);
    }


    @Test
    void 백색_킹1개_퀸1개_룩2개_비숍2개_나이트2개_생성() {
        List<Piece> actual = new ArrayList<>(boardMap.values());
        List<Piece> expected = List.of(
                new NonPawn(WHITE, KNIGHT), new NonPawn(WHITE, KNIGHT),
                new NonPawn(WHITE, BISHOP), new NonPawn(WHITE, BISHOP),
                new NonPawn(WHITE, ROOK), new NonPawn(WHITE, ROOK),
                new NonPawn(WHITE, QUEEN), new NonPawn(WHITE, KING));

        assertThat(actual).containsAll(expected);
    }

    @Test
    void 흑색_킹1개_퀸1개_룩2개_비숍2개_나이트2개_생성() {
        List<Piece> actual = new ArrayList<>(boardMap.values());
        List<Piece> expected = List.of(
                new NonPawn(BLACK, KNIGHT), new NonPawn(BLACK, KNIGHT),
                new NonPawn(BLACK, BISHOP), new NonPawn(BLACK, BISHOP),
                new NonPawn(BLACK, ROOK), new NonPawn(BLACK, ROOK),
                new NonPawn(BLACK, QUEEN), new NonPawn(BLACK, KING));

        assertThat(actual).containsAll(expected);
    }
}