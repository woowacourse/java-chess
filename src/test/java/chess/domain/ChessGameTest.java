package chess.domain;

import chess.domain.chess.ChessGame;
import chess.domain.chess.Team;
import chess.domain.chess.UnitScoreBoard;
import chess.domain.chess.exception.PawnIllegalMovingRuleException;
import chess.domain.chess.exception.SameTeamTargetUnitException;
import chess.domain.chess.exception.UnitInterceptionAlongPathException;
import chess.domain.chess.initializer.ChessBoardInitializer;
import chess.domain.chess.initializer.Initializer;
import chess.domain.chess.initializer.SettableChessBoardInitializer;
import chess.domain.chess.unit.*;
import chess.domain.geometric.Position;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChessGameTest {
    @Test
    void 폰_직선경로_유닛_없음() throws Exception {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Pawn(Team.WHITE));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);
        chessGame.move(Position.create(1, 1), Position.create(1, 2));

        assertThat(chessGame.getUnit(Position.create(1, 2)).get()).isInstanceOf(Pawn.class);
    }

    @Test
    void 폰_직선경로_아군() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Pawn(Team.WHITE));
        map.put(Position.create(1, 2), new Bishop(Team.WHITE));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);

        assertThrows(SameTeamTargetUnitException.class, () -> {
            chessGame.move(Position.create(1, 1), Position.create(1, 2));
        });
    }

    @Test
    void 폰_직선경로_적군() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Pawn(Team.WHITE));
        map.put(Position.create(1, 2), new Bishop(Team.BLACK));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);

        assertThrows(PawnIllegalMovingRuleException.class, () -> {
            chessGame.move(Position.create(1, 1), Position.create(1, 2));
        });
    }

    @Test
    void 폰_대각선경로_아군() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Pawn(Team.WHITE));
        map.put(Position.create(2, 2), new Bishop(Team.WHITE));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);

        assertThrows(SameTeamTargetUnitException.class, () -> {
            chessGame.validateMove(Position.create(1, 1), Position.create(2, 2));
        });
    }

    @Test
    void 폰_대각선경로_적군() throws Exception {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Pawn(Team.WHITE));
        map.put(Position.create(2, 2), new Bishop(Team.BLACK));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);

        chessGame.move(Position.create(1, 1), Position.create(2, 2));
        assertThat(chessGame.getUnit(Position.create(2, 2)).get().getTeam())
                .isEqualTo(Team.WHITE);
    }

    @Test
    void 비숍_경로_도착지에_아군있음() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Bishop(Team.WHITE));
        map.put(Position.create(5, 5), new Rook(Team.WHITE));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);

        assertThrows(SameTeamTargetUnitException.class, () -> {
            chessGame.move(Position.create(1, 1), Position.create(5, 5));
        });
    }

    @Test
    void 비숍_경로_도착지에_적군있음() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Bishop(Team.WHITE));
        map.put(Position.create(5, 5), new Rook(Team.BLACK));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);
        chessGame.move(Position.create(1, 1), Position.create(5, 5));

        assertThat(chessGame.getUnit(Position.create(5, 5)).get()).isInstanceOf(Bishop.class);

    }

    @Test
    void 비숍_경로_중간에_유닛_있음() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Bishop(Team.WHITE));
        map.put(Position.create(3, 3), new Bishop(Team.BLACK));
        map.put(Position.create(5, 5), new Rook(Team.BLACK));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);

        assertThrows(UnitInterceptionAlongPathException.class, () -> {
            chessGame.move(Position.create(1, 1), Position.create(5, 5));
        });

    }

    @Test
    void 룩_경로_도착지에_아군있음() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Rook(Team.WHITE));
        map.put(Position.create(1, 5), new Pawn(Team.WHITE));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);

        assertThrows(SameTeamTargetUnitException.class, () -> {
            chessGame.move(Position.create(1, 1), Position.create(1, 5));
        });

    }

    @Test
    void 룩_경로_도착지에_적군있음() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Rook(Team.WHITE));
        map.put(Position.create(1, 5), new Bishop(Team.BLACK));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);
        chessGame.move(Position.create(1, 1), Position.create(1, 5));

        assertThat(chessGame.getUnit(Position.create(1, 5)).get()).isInstanceOf(Rook.class);
    }

    @Test
    void 룩_경로_중간에_유닛_있음() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Rook(Team.WHITE));
        map.put(Position.create(1, 3), new Bishop(Team.WHITE));
        map.put(Position.create(1, 5), new Bishop(Team.BLACK));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);

        assertThrows(UnitInterceptionAlongPathException.class, () -> {
            chessGame.move(Position.create(1, 1), Position.create(1, 5));
        });
    }

    @Test
    void 나이트_경로_도착지에_아군있음() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Knight(Team.WHITE));
        map.put(Position.create(3, 2), new Rook(Team.WHITE));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);

        assertThrows(SameTeamTargetUnitException.class, () -> {
            chessGame.move(Position.create(1, 1), Position.create(3, 2));
        });

    }

    @Test
    void 나이트_경로_도착지에_적군있음() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Knight(Team.WHITE));
        map.put(Position.create(3, 2), new Bishop(Team.BLACK));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);
        chessGame.move(Position.create(1, 1), Position.create(3, 2));

        assertThat(chessGame.getUnit(Position.create(3, 2)).get()).isInstanceOf(Knight.class);
    }

    @Test
    void 나이트_경로_중간에_유닛있음() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Knight(Team.WHITE));
        map.put(Position.create(2, 2), new Bishop(Team.WHITE));
        map.put(Position.create(3, 2), new Bishop(Team.BLACK));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);
        chessGame.move(Position.create(1, 1), Position.create(3, 2));

        assertThat(chessGame.getUnit(Position.create(3, 2)).get()).isInstanceOf(Knight.class);
    }

    @Test
    void 퀸_경로_도착지에_아군있음() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Queen(Team.WHITE));
        map.put(Position.create(5, 5), new Rook(Team.WHITE));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);

        assertThrows(SameTeamTargetUnitException.class, () ->
                chessGame.move(Position.create(1, 1), Position.create(5, 5)));

    }

    @Test
    void 퀸_경로_도착지에_적군있음() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Queen(Team.WHITE));
        map.put(Position.create(5, 5), new Bishop(Team.BLACK));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);
        chessGame.move(Position.create(1, 1), Position.create(5, 5));

        assertThat(chessGame.getUnit(Position.create(5, 5)).get()).isInstanceOf(Queen.class);

    }

    @Test
    void 퀸_경로_중간에_유닛있음() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Queen(Team.WHITE));
        map.put(Position.create(3, 3), new Bishop(Team.WHITE));
        map.put(Position.create(5, 5), new Bishop(Team.BLACK));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);

        assertThrows(UnitInterceptionAlongPathException.class, () -> {
            chessGame.move(Position.create(1, 1), Position.create(5, 5));
        });

    }

    @Test
    void 킹_경로_도착지에_아군있음() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new King(Team.WHITE));
        map.put(Position.create(1, 2), new Rook(Team.WHITE));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);

        assertThrows(SameTeamTargetUnitException.class, () -> {
            chessGame.move(Position.create(1, 1), Position.create(1, 2));
        });

    }

    @Test
    void 킹_경로_도착지에_적군있음() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new King(Team.WHITE));
        map.put(Position.create(1, 2), new Bishop(Team.BLACK));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);
        chessGame.move(Position.create(1, 1), Position.create(1, 2));

        assertThat(chessGame.getUnit(Position.create(1, 2)).get()).isInstanceOf(King.class);

    }

    @Test
    void 킹_경로_중간에_유닛있음() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Rook(Team.WHITE));
        map.put(Position.create(1, 3), new Bishop(Team.WHITE));
        map.put(Position.create(1, 5), new Bishop(Team.BLACK));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);

        assertThrows(UnitInterceptionAlongPathException.class, () -> {
            chessGame.move(Position.create(1, 1), Position.create(1, 5));
        });
    }

    @Test
    void 최초_점수_계산() {
        ChessGame chessGame = new ChessGame(new ChessBoardInitializer());
        UnitScoreBoard unitScoreBoard = new UnitScoreBoard(chessGame.getUnits());
        Map<Team, Double> scoreInfo = unitScoreBoard.sumScore();

        assertThat(scoreInfo.get(Team.BLACK)).isEqualTo(38.0);
    }

    @Test
    void 수직_폰_점수_계산() {
        Map<Position, Unit> map = new HashMap<>();
        map.put(Position.create(1, 1), new Pawn(Team.WHITE));
        map.put(Position.create(1, 2), new Pawn(Team.WHITE));
        map.put(Position.create(1, 3), new Pawn(Team.WHITE));
        map.put(Position.create(2, 2), new Queen(Team.WHITE));
        map.put(Position.create(3, 1), new Pawn(Team.WHITE));
        map.put(Position.create(3, 2), new Pawn(Team.WHITE));
        map.put(Position.create(4, 2), new Pawn(Team.WHITE));

        Initializer testInitializer = new SettableChessBoardInitializer(map, Team.WHITE);

        ChessGame chessGame = new ChessGame(testInitializer);
        UnitScoreBoard unitScoreBoard = new UnitScoreBoard(chessGame.getUnits());
        Map<Team, Double> scoreInfo = unitScoreBoard.sumScore();

        assertThat(scoreInfo.get(Team.WHITE))
                .isEqualTo(12.5);
    }
}
