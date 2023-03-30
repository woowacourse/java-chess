package chess.domain.chessGame;

import chess.domain.piece.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RefereeTest {

    /**
     * .KR.....  8
     * P.PB....  7
     * .P..Q...  6
     * ........  5
     * .....nq.  4
     * .....p.p  3
     * .....pp.  2
     * ....rk..  1
     *
     * abcdefgh
     *
     * 위와 같은 체스 판의 점수 결과는 검은색(대문자)은 20점. 흰색(소문자)은 19.5점이 된다.
     * 검은색은 5(rook) + 3(bishop) + 9(queen) + 3(pawn) + 0(king) = 20점
     * 흰색은 5(rook) + 2.5(knight) + 9(queen) + 3(pawn, 4개의 pawn이 있지만 세로로 있어 각 0.5이 된다.) + 0(king) = 19.5점
     */

    private static Map<Position, Piece> pieces;

    @BeforeAll
    static void setup() {
        Map<Position, Piece> blackPieces = Map.of(
                Position.of("b8"), new King(Color.BLACK),
                Position.of("c8"), new Rook(Color.BLACK),
                Position.of("a7"), new Pawn(Color.BLACK),
                Position.of("c7"), new Pawn(Color.BLACK),
                Position.of("d7"), new Bishop(Color.BLACK),
                Position.of("b6"), new Pawn(Color.BLACK),
                Position.of("e6"), new Queen(Color.BLACK)
        );

        Map<Position, Piece> whitePieces = Map.of(
                Position.of("f4"), new Knight(Color.WHITE),
                Position.of("g4"), new Queen(Color.WHITE),
                Position.of("f3"), new Pawn(Color.WHITE),
                Position.of("h3"), new Pawn(Color.WHITE),
                Position.of("f2"), new Pawn(Color.WHITE),
                Position.of("g2"), new Pawn(Color.WHITE),
                Position.of("e1"), new Rook(Color.WHITE),
                Position.of("f1"), new King(Color.WHITE)
        );

        pieces = new HashMap<>();
        pieces.putAll(blackPieces);
        pieces.putAll(whitePieces);
    }

    @Test
    @DisplayName("검은색 말의 점수를 계산할 수 있다.")
    void calculateBlackScoreTest() {
        // when
        double score = new Referee(pieces).calculateScore(Color.BLACK);

        // then
        assertThat(score).isEqualTo(20);
    }

    @Test
    @DisplayName("흰색 말의 점수를 계산할 수 있다.")
    void calculateWhiteScoreTest() {
        // when
        double score = new Referee(pieces).calculateScore(Color.WHITE);

        // then
        assertThat(score).isEqualTo(19.5);
    }
}