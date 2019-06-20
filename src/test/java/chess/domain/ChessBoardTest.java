package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChessBoardTest {
    @Test
    void 폰_직선경로_유닛_없음() throws Exception {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Pawn(Team.WHITE));

        Initializer testInitializer = mock(ChessBoardInitializer.class);
        when(testInitializer.create()).thenReturn(map);

        ChessBoard chessBoard = new ChessBoard(testInitializer);
        chessBoard.move(Position.create(1,1), Position.create(1,2));

        assertThat(map.get(Position.create(1, 2)))
                .isEqualTo(chessBoard.getUnit(Position.create(1, 2)).get());
    }

    @Test
    void 폰_직선경로_아군() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1,1), new Pawn(Team.WHITE));
        map.put(Position.create(1, 2), new Bishop(Team.WHITE));

        Initializer testInitializer = mock(ChessBoardInitializer.class);
        when(testInitializer.create()).thenReturn(map);

        ChessBoard chessBoard = new ChessBoard(testInitializer);

        assertThrows(Exception.class, () -> {
            chessBoard.canMove(Position.create(1,1), Position.create(1,2));
        });
    }

    @Test
    void 폰_직선경로_적군() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Pawn(Team.WHITE));
        map.put(Position.create(1, 2), new Bishop(Team.BLACK));

        Initializer testInitializer = mock(ChessBoardInitializer.class);
        when(testInitializer.create()).thenReturn(map);

        ChessBoard chessBoard = new ChessBoard(testInitializer);

        assertThrows(Exception.class, () -> {
            chessBoard.move(Position.create(1,1), Position.create(1,2));
        });
    }

    @Test
    void 폰_대각선경로_아군() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Pawn(Team.WHITE));
        map.put(Position.create(2, 2), new Bishop(Team.WHITE));

        Initializer testInitializer = mock(ChessBoardInitializer.class);
        when(testInitializer.create()).thenReturn(map);

        ChessBoard chessBoard = new ChessBoard(testInitializer);

        assertThrows(Exception.class, () -> {
            chessBoard.canMove(Position.create(1,1), Position.create(2,2));
        });
    }

    @Test
    void 폰_대각선경로_적군() throws Exception {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Pawn(Team.WHITE));
        map.put(Position.create(2, 2), new Bishop(Team.BLACK));

        Initializer testInitializer = mock(ChessBoardInitializer.class);
        when(testInitializer.create()).thenReturn(map);

        ChessBoard chessBoard = new ChessBoard(testInitializer);

        chessBoard.move(Position.create(1,1), Position.create(2,2));
        assertThat(map.get(Position.create(2, 2)))
                .isEqualTo(chessBoard.getUnit(Position.create(2, 2)).get());
    }

    @Test
    void 비숍_경로_도착지에_아군있음() {

    }

    @Test
    void 비숍_경로_도착지에_적군있음() {


    }

    @Test
    void 비숍_경로_중간에_유닛_있음() {

    }

    @Test
    void 룩_경로_도착지에_아군있음() {


    }

    @Test
    void 룩_경로_도착지에_적군있음() {


    }

    @Test
    void 룩_경로_중간에_유닛_있음() {


    }

    @Test
    void 나이트_경로_도착지에_아군있음() {

    }

    @Test
    void 나이트_경로_도착지에_적군있음() {


    }

    @Test
    void 나이트_경로_중간에_유닛있음() {


    }

    @Test
    void 퀸_경로_도착지에_아군있음() {

    }

    @Test
    void 퀸_경로_도착지에_적군있음() {


    }

    @Test
    void 퀸_경로_중간에_유닛있음() {


    }

    @Test
    void 킹_경로_도착지에_아군있음() {

    }

    @Test
    void 킹_경로_도착지에_적군있음() {


    }

    @Test
    void 킹_경로_중간에_유닛있음() {


    }
}
