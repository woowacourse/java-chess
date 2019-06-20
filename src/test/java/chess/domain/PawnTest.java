package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PawnTest {
    @Test
    void 생성() {
        assertDoesNotThrow(() -> new Pawn(true));
    }

    @Test
    void 흰색팀_직선_한칸_이동() {
        Piece pawn = new Pawn(true);
        List<Point> points = new ArrayList<>();
        points.add(new Point("a3"));
        assertThat(pawn.getCandidatePoints(new Point("a2"), new Point("a3"))).isEqualTo(points);
    }

    @Test
    void 흰색팀_대각선_한칸_이동() {
        Piece pawn = new Pawn(true);
        List<Point> points = new ArrayList<>();
        points.add(new Point("b3"));
        assertThat(pawn.getCandidatePoints(new Point("a2"), new Point("b3"))).isEqualTo(points);
    }

    @Test
    void 검은색팀_직선_한칸_이동() {
        Piece pawn = new Pawn(false);
        List<Point> points = new ArrayList<>();
        points.add(new Point("a6"));
        assertThat(pawn.getCandidatePoints(new Point("a7"), new Point("a6"))).isEqualTo(points);
    }

    @Test
    void 검은색팀_대각선_한칸_이동() {
        Piece pawn = new Pawn(false);
        List<Point> points = new ArrayList<>();
        points.add(new Point("b6"));
        assertThat(pawn.getCandidatePoints(new Point("a7"), new Point("b6"))).isEqualTo(points);
    }

    @Test
    void 검은색팀_대각선_잘못된_이동() {
        Piece pawn = new Pawn(false);
        assertThat(pawn.getCandidatePoints(new Point("a7"), new Point("b5")).size()).isEqualTo(0);
    }

    @Test
    void 흰색팀_대각선_잘못된_이동() {
        Piece pawn = new Pawn(true);
        assertThat(pawn.getCandidatePoints(new Point("a2"), new Point("b4")).size()).isEqualTo(0);
    }

    @Test
    void 검은색팀_직선_잘못된_이동() {
        Piece pawn = new Pawn(false);
        assertThat(pawn.getCandidatePoints(new Point("a7"), new Point("a4")).size()).isEqualTo(0);
    }

    @Test
    void 흰색팀_직선_잘못된_이동() {
        Piece pawn = new Pawn(true);
        assertThat(pawn.getCandidatePoints(new Point("a2"), new Point("a5")).size()).isEqualTo(0);
    }
}
