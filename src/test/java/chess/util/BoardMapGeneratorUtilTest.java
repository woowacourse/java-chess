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
        List<Piece> expected = List.of(Piece.of(BLACK, PAWN), Piece.of(WHITE, PAWN),
                Piece.of(BLACK, KNIGHT), Piece.of(WHITE, KNIGHT),
                Piece.of(BLACK, BISHOP), Piece.of(WHITE, BISHOP),
                Piece.of(BLACK, ROOK), Piece.of(WHITE, ROOK),
                Piece.of(BLACK, QUEEN), Piece.of(WHITE, QUEEN),
                Piece.of(BLACK, KING), Piece.of(WHITE, KING));

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
                Piece.of(WHITE, KNIGHT), Piece.of(WHITE, KNIGHT),
                Piece.of(WHITE, BISHOP), Piece.of(WHITE, BISHOP),
                Piece.of(WHITE, ROOK), Piece.of(WHITE, ROOK),
                Piece.of(WHITE, QUEEN), Piece.of(WHITE, KING));

        assertThat(actual).containsAll(expected);
    }

    @Test
    void 흑색_킹1개_퀸1개_룩2개_비숍2개_나이트2개_생성() {
        List<Piece> actual = new ArrayList<>(boardMap.values());
        List<Piece> expected = List.of(
                Piece.of(BLACK, KNIGHT), Piece.of(BLACK, KNIGHT),
                Piece.of(BLACK, BISHOP), Piece.of(BLACK, BISHOP),
                Piece.of(BLACK, ROOK), Piece.of(BLACK, ROOK),
                Piece.of(BLACK, QUEEN), Piece.of(BLACK, KING));

        assertThat(actual).containsAll(expected);
    }
}
