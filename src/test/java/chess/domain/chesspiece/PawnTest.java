package chess.domain.chesspiece;

import chess.domain.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PawnTest {
    @Test
    void 이동가능한_경로_직선_두칸_폰() {
        Position position = Position.of(1, 1);
        ChessPiece pawn = new Pawn(Team.BLACK);
        List<Position> route = pawn.getRouteOfPiece(position, Position.of(3, 1));
        List<Position> testRoute = new ArrayList<>();
        testRoute.add(Position.of(2, 1));
        testRoute.add(Position.of(3, 1));

        for (int i = 0; i < 2; i++) {
            assertThat(route.contains(testRoute.get(i))).isTrue();
        }
    }

    @Test
    void 이동가능한_경로가_직선_한칸() {
        Position position = Position.of(1, 1);
        ChessPiece pawn = new Pawn(Team.BLACK);
        List<Position> route = pawn.getRouteOfPiece(position, Position.of(2, 1));

        assertThat(route.contains(Position.of(2, 1))).isTrue();
    }

    @Test
    void 이동가능한_경로가_대각선_한칸() {
        Position position = Position.of(1, 1);
        ChessPiece pawn = new Pawn(Team.BLACK);
        List<Position> route = pawn.getRouteOfPiece(position, Position.of(2, 0));

        assertThat(route.contains(Position.of(2, 0))).isTrue();
    }

    @Test
    void 이동가능한_경로_직선_두칸_상대_팀_폰() {
        Position position = Position.of(6, 1);
        ChessPiece pawn = new Pawn(Team.WHITE);
        List<Position> route = pawn.getRouteOfPiece(position, Position.of(4, 1));
        List<Position> testRoute = new ArrayList<>();
        testRoute.add(Position.of(5, 1));
        testRoute.add(Position.of(4, 1));

        for (int i = 0; i < 2; i++) {
            assertThat(route.contains(testRoute.get(i))).isTrue();
        }
    }

    @Test
    void 이동가능한_경로가_직선_상대팀_한칸() {
        Position position = Position.of(6, 1);
        ChessPiece pawn = new Pawn(Team.WHITE);
        List<Position> route = pawn.getRouteOfPiece(position, Position.of(5, 1));

        assertThat(route.contains(Position.of(5, 1))).isTrue();
    }

    @Test
    void 이동가능한_경로가_대각선_상대팀_한칸() {
        Position position = Position.of(6, 1);
        ChessPiece pawn = new Pawn(Team.WHITE);
        List<Position> route = pawn.getRouteOfPiece(position, Position.of(5, 2));

        assertThat(route.contains(Position.of(5, 2))).isTrue();
    }

    @Test
    void 이동불가능한_경로_예외발생_뒤로가기_폰() {
        Position position = Position.of(1, 1);
        ChessPiece pawn = new Pawn(Team.BLACK);

        assertThrows(IllegalArgumentException.class, () -> pawn.getRouteOfPiece(position, Position.of(0, 1)));
    }

    @Test
    void 이동불가능한_경로_예외발생_대각선_뒤로가기_폰() {
        Position position = Position.of(1, 1);
        ChessPiece pawn = new Pawn(Team.BLACK);

        assertThrows(IllegalArgumentException.class, () -> pawn.getRouteOfPiece(position, Position.of(0, 0)));
    }

    @Test
    void 이동불가능한_경로_예외발생_상대팀_대각선_뒤로가기_폰() {
        Position position = Position.of(6, 1);
        ChessPiece pawn = new Pawn(Team.WHITE);

        assertThrows(IllegalArgumentException.class, () -> pawn.getRouteOfPiece(position, Position.of(7, 0)));
    }

    @Test
    void 이동불가능한_경로_예외발생_상대팀_직선_뒤로가기_폰() {
        Position position = Position.of(6, 1);
        ChessPiece pawn = new Pawn(Team.WHITE);

        assertThrows(IllegalArgumentException.class, () -> pawn.getRouteOfPiece(position, Position.of(7, 1)));
    }

    @Test
    void 이동불가능한_경로_예외발생_앞으로_세칸_폰() {
        Position position = Position.of(6, 1);
        ChessPiece pawn = new Pawn(Team.WHITE);

        assertThrows(IllegalArgumentException.class, () -> pawn.getRouteOfPiece(position, Position.of(3, 1)));
    }

    @Test
    void 직선으로_두칸씩_두번_이동() {
        Position position = Position.of(6,2);
        ChessPiece pawn = new Pawn(Team.WHITE);

        pawn.getRouteOfPiece(position, Position.of(4, 2));

        assertThrows(IllegalArgumentException.class, () -> pawn.getRouteOfPiece(Position.of(4, 2), Position.of(2, 2)));
    }


    @Test
    void 직선으로_두칸씩_두번_이동_블랙() {
        Position position = Position.of(1,4);
        ChessPiece pawn = new Pawn(Team.BLACK);

        pawn.getRouteOfPiece(position, Position.of(3, 4));

        assertThrows(IllegalArgumentException.class, () -> pawn.getRouteOfPiece(Position.of(3, 4), Position.of(5, 4)));
    }
}