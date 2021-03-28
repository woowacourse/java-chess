package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Rank;
import chess.domain.board.position.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreCalculatorTest {

    @Test
    @DisplayName("백색에게 남은 말이 없을 때 0점 반환")
    void testWhiteHasNoChessPiece() {
        ScoreCalculator scoreCalculator = emptyScoreCalculator();
        assertThat(scoreCalculator.totalWhiteScore()).isEqualTo(0);
    }

    @Test
    @DisplayName("흑색에게 남은 말이 없을 때 0점 반환")
    void testBlackHasNoChessPiece() {
        ScoreCalculator scoreCalculator = emptyScoreCalculator();
        assertThat(scoreCalculator.totalBlackScore()).isEqualTo(0);
    }

    private ScoreCalculator emptyScoreCalculator() {
        Rank rank = new Rank(new LinkedHashMap<>());
        List<Rank> ranks = new ArrayList<>(Collections.singletonList(rank));
        return new ScoreCalculator(ranks);
    }

    @Test
    @DisplayName("King은 점수가 없음을 확인하는 테스트")
    void testKingHasNoScore() {
        Map<Position, Piece> map = new LinkedHashMap<>();
        map.put(Position.of("e8"), King.createBlack());
        map.put(Position.of("e1"), King.createWhite());

        List<Rank> ranks = Collections.singletonList(new Rank(map));
        ScoreCalculator scoreCalculator = new ScoreCalculator(ranks);

        assertThat(scoreCalculator.totalBlackScore()).isEqualTo(0);
        assertThat(scoreCalculator.totalWhiteScore()).isEqualTo(0);
    }

    @Test
    @DisplayName("Queen은 9점임을 확인하는 테스트")
    void testQueenHasScoreNine() {
        Map<Position, Piece> map = new LinkedHashMap<>();
        map.put(Position.of("d8"), Queen.createBlack());
        map.put(Position.of("d1"), Queen.createWhite());

        List<Rank> ranks = Collections.singletonList(new Rank(map));
        ScoreCalculator scoreCalculator = new ScoreCalculator(ranks);

        assertThat(scoreCalculator.totalBlackScore()).isEqualTo(9);
        assertThat(scoreCalculator.totalWhiteScore()).isEqualTo(9);
    }

    @Test
    @DisplayName("Rook은 5점임을 확인하는 테스트")
    void testRookHasScoreFive() {
        Map<Position, Piece> map = new LinkedHashMap<>();
        map.put(Position.of("a8"), Rook.createBlack());
        map.put(Position.of("h8"), Rook.createBlack());
        map.put(Position.of("a1"), Rook.createWhite());

        List<Rank> ranks = Collections.singletonList(new Rank(map));
        ScoreCalculator scoreCalculator = new ScoreCalculator(ranks);

        assertThat(scoreCalculator.totalBlackScore()).isEqualTo(10);
        assertThat(scoreCalculator.totalWhiteScore()).isEqualTo(5);
    }

    @Test
    @DisplayName("Bishop은 3점임을 확인하는 테스트")
    void testBishopHasScoreThree() {
        Map<Position, Piece> map = new LinkedHashMap<>();
        map.put(Position.of("c8"), Bishop.createBlack());
        map.put(Position.of("f8"), Bishop.createBlack());
        map.put(Position.of("c1"), Bishop.createWhite());

        List<Rank> ranks = Collections.singletonList(new Rank(map));
        ScoreCalculator scoreCalculator = new ScoreCalculator(ranks);

        assertThat(scoreCalculator.totalBlackScore()).isEqualTo(6);
        assertThat(scoreCalculator.totalWhiteScore()).isEqualTo(3);
    }

    @Test
    @DisplayName("Knight은 2.5점임을 확인하는 테스트")
    void testBishopHasScoreTwoHalf() {
        Map<Position, Piece> map = new LinkedHashMap<>();
        map.put(Position.of("b8"), Knight.createBlack());
        map.put(Position.of("g8"), Knight.createBlack());
        map.put(Position.of("b1"), Knight.createWhite());

        List<Rank> ranks = Collections.singletonList(new Rank(map));
        ScoreCalculator scoreCalculator = new ScoreCalculator(ranks);

        assertThat(scoreCalculator.totalBlackScore()).isEqualTo(5);
        assertThat(scoreCalculator.totalWhiteScore()).isEqualTo(2.5);
    }

    @Test
    @DisplayName("Pawn의 기본점수가 1점임을 확인하는 테스트")
    void testPawnBasicScoreOne() {
        Map<Position, Piece> map = new LinkedHashMap<>();
        map.put(Position.of("a7"), Pawn.createBlack());
        map.put(Position.of("b7"), Pawn.createBlack());
        map.put(Position.of("c2"), Pawn.createWhite());

        List<Rank> ranks = Collections.singletonList(new Rank(map));
        ScoreCalculator scoreCalculator = new ScoreCalculator(ranks);

        assertThat(scoreCalculator.totalBlackScore()).isEqualTo(2);
        assertThat(scoreCalculator.totalWhiteScore()).isEqualTo(1);
    }

    @Test
    @DisplayName("같은 색의 Pawn이 같은 열에 있을 경우 0.5점임을 확인하는 테스트")
    void testPawnSameColumnScoreHalf() {
        Map<Position, Piece> map = new LinkedHashMap<>();
        map.put(Position.of("a7"), Pawn.createBlack());
        map.put(Position.of("a6"), Pawn.createBlack());
        map.put(Position.of("a2"), Pawn.createWhite());

        List<Rank> ranks = Collections.singletonList(new Rank(map));
        ScoreCalculator scoreCalculator = new ScoreCalculator(ranks);

        assertThat(scoreCalculator.totalBlackScore()).isEqualTo(1);
        assertThat(scoreCalculator.totalWhiteScore()).isEqualTo(1);
    }

    @Test
    @DisplayName("총 체스말들의 점수의 합이 잘 반환되는지 테스트")
    void testTotalScoreSum() {
        Map<Position, Piece> map = new LinkedHashMap<>();
        map.put(Position.of("a7"), Pawn.createBlack());  // 0.5
        map.put(Position.of("a6"), Pawn.createBlack());  // 0.5
        map.put(Position.of("c2"), Pawn.createBlack());  // 1
        map.put(Position.of("d8"), Queen.createBlack()); // 9
        map.put(Position.of("e8"), King.createBlack());  // 0
        map.put(Position.of("a1"), Rook.createBlack());  // 5

        List<Rank> ranks = Collections.singletonList(new Rank(map));
        ScoreCalculator scoreCalculator = new ScoreCalculator(ranks);

        assertThat(scoreCalculator.totalBlackScore()).isEqualTo(16);
    }
}