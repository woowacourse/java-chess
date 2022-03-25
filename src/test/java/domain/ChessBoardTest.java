package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import domain.piece.property.TeamColor;
import domain.piece.unit.Bishop;
import domain.piece.unit.Pawn;
import domain.piece.unit.Piece;
import domain.piece.unit.Rook;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ChessBoardTest {

    @Test
    @DisplayName("Source 위치에는 기물이 있어야한다.")
    void haveToSourceNotNull() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        Position source = Position.of(XPosition.B, YPosition.THREE);
        Position target = Position.of(XPosition.B, YPosition.FOUR);

        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("자신의 턴에 자신의 말만 이동할 수 있다.(성공)")
    void moveOwnTurnOwnPieceSuccess() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        Position source = Position.of(XPosition.B, YPosition.TWO);
        Position target = Position.of(XPosition.B, YPosition.THREE);
        assertDoesNotThrow(() -> chessBoard.move(source, target));
    }

    @Test
    @DisplayName("자신의 턴에 자신의 말만 이동할 수 있다.(실패)")
    void moveOwnTurnOwnPieceFail() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        Position source = Position.of(XPosition.B, YPosition.SEVEN);
        Position target = Position.of(XPosition.B, YPosition.SIX);

        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("move 이후에 Turn 이 변경된다.")
    void changeTurnAfterMove() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        Position whiteTurnSource = Position.of(XPosition.B, YPosition.TWO);
        Position whiteTurnTarget = Position.of(XPosition.B, YPosition.THREE);
        Position blackTurnSource = Position.of(XPosition.B, YPosition.SEVEN);
        Position blackTurnTarget = Position.of(XPosition.B, YPosition.SIX);

        chessBoard.move(whiteTurnSource, whiteTurnTarget);
        assertDoesNotThrow(() -> chessBoard.move(blackTurnSource, blackTurnTarget));
    }

    @ParameterizedTest
    @MethodSource("availableTargets")
    @DisplayName("target은 source와 다른 팀이거나 null 이다.")
    void checkTargetSuccess(Piece targetPiece) {
        Position source = Position.of(XPosition.A, YPosition.ONE);
        Position target = Position.of(XPosition.A, YPosition.EIGHT);
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                Arrays.stream(XPosition.values())
                        .forEach(x -> Arrays.stream(YPosition.values())
                                .forEach(y -> board.put(Position.of(x, y), null)));
                board.put(source, new Rook(TeamColor.WHITE));
                board.put(target, targetPiece);
                return board;
            }
        });

        assertDoesNotThrow(() -> chessBoard.move(source, target));
    }

    private static Stream<Piece> availableTargets() {
        return Stream.of(
                new Rook(TeamColor.BLACK),
                null
        );
    }

    @Test
    @DisplayName("target 은 source 와 같은 팀일 수 없다.")
    void checkTargetFail() {
        Position source = Position.of(XPosition.A, YPosition.ONE);
        Position target = Position.of(XPosition.A, YPosition.EIGHT);
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                Arrays.stream(XPosition.values())
                        .forEach(x -> Arrays.stream(YPosition.values())
                                .forEach(y -> board.put(Position.of(x, y), null)));
                board.put(source, new Rook(TeamColor.WHITE));
                board.put(target, new Rook(TeamColor.WHITE));
                return board;
            }
        });

        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("source 가 target 까지 이동하는 과정에는 모두 비어있어야(null) 이동 가능하다(Knight 는 제외한다).")
    void checkEverySpaceInCrossIsNull() {
        Position source = Position.of(XPosition.A, YPosition.ONE);
        Position waypoint = Position.of(XPosition.C, YPosition.THREE);
        Position target = Position.of(XPosition.E, YPosition.FIVE);
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                Arrays.stream(XPosition.values())
                        .forEach(x -> Arrays.stream(YPosition.values())
                                .forEach(y -> board.put(Position.of(x, y), null)));
                board.put(source, new Bishop(TeamColor.WHITE));
                board.put(waypoint, new Bishop(TeamColor.WHITE));
                board.put(target, new Bishop(TeamColor.BLACK));
                return board;
            }
        });

        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("availableKnights")
    @DisplayName("Knight 는 이동경로에 말이 존재해도 넘어갈 수 있다.")
    void checkEverySpaceInCrossSuccess(Position target) {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        Position source = Position.of(XPosition.B, YPosition.ONE);
        assertDoesNotThrow(() -> chessBoard.move(source, target));
    }

    private static Stream<Position> availableKnights() {
        return Stream.of(
                Position.of(XPosition.A, YPosition.THREE),
                Position.of(XPosition.C, YPosition.THREE)
        );
    }

    @ParameterizedTest
    @MethodSource("availableMovePawns")
    @DisplayName("Pawn은 상대 말이 없을 때 이동할 수 있다.")
    void checkPawnMoveNoOpponent(Position target) {
        Position source = Position.of(XPosition.B, YPosition.TWO);
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                Arrays.stream(XPosition.values())
                        .forEach(x -> Arrays.stream(YPosition.values())
                                .forEach(y -> board.put(Position.of(x, y), null)));
                board.put(source, new Pawn(TeamColor.WHITE));
                return board;
            }
        });

        assertDoesNotThrow(() -> chessBoard.move(source, target));
    }

    private static Stream<Position> availableMovePawns() {
        return Stream.of(
                Position.of(XPosition.B, YPosition.THREE),
                Position.of(XPosition.B, YPosition.FOUR)
        );
    }

    @Test
    @DisplayName("Pawn 은 이동 위치에 상대방의 말이 있다면 이동할 수 없다. 앞으로 한칸 이동한 경우")
    void checkPawnImmovableByOpponent() {
        Position source = Position.of(XPosition.B, YPosition.TWO);
        Position target = Position.of(XPosition.B, YPosition.THREE);
        Position waypoint = Position.of(XPosition.B, YPosition.THREE);
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                Arrays.stream(XPosition.values())
                        .forEach(x -> Arrays.stream(YPosition.values())
                                .forEach(y -> board.put(Position.of(x, y), null)));
                board.put(source, new Pawn(TeamColor.WHITE));
                board.put(waypoint, new Pawn(TeamColor.BLACK));
                return board;
            }
        });

        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("immovableTwoSpace")
    @DisplayName("Pawn 은 이동 위치에 상대방의 말이 있다면 이동할 수 없다. 앞으로 두칸 이동한 경우")
    void checkPawnTwoSpaceImmovableByOpponent(Position waypoint) {
        Position source = Position.of(XPosition.B, YPosition.TWO);
        Position target = Position.of(XPosition.B, YPosition.FOUR);
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                Arrays.stream(XPosition.values())
                        .forEach(x -> Arrays.stream(YPosition.values())
                                .forEach(y -> board.put(Position.of(x, y), null)));
                board.put(source, new Pawn(TeamColor.WHITE));
                board.put(waypoint, new Pawn(TeamColor.BLACK));
                return board;
            }
        });

        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Position> immovableTwoSpace() {
        return Stream.of(
                Position.of(XPosition.B, YPosition.THREE),
                Position.of(XPosition.B, YPosition.FOUR)
        );
    }

    @Test
    @DisplayName("Pawn 의 앞으로 이동은 앞으로 한칸, 시작시는 두칸 가능하다. (실패테스트)")
    void checkPawnImmovablePositions() {
        Position source = Position.of(XPosition.B, YPosition.TWO);
        Position target = Position.of(XPosition.B, YPosition.FIVE);
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                Arrays.stream(XPosition.values())
                        .forEach(x -> Arrays.stream(YPosition.values())
                                .forEach(y -> board.put(Position.of(x, y), null)));
                board.put(source, new Pawn(TeamColor.WHITE));
                return board;
            }
        });

        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("attackPawnPositions")
    @DisplayName("Pawn 의 공격은 대각선 한칸이며, target이 존재해야 한다.")
    void checkPawnAttackSucess(Position target) {
        Position source = Position.of(XPosition.B, YPosition.TWO);
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                Arrays.stream(XPosition.values())
                        .forEach(x -> Arrays.stream(YPosition.values())
                                .forEach(y -> board.put(Position.of(x, y), null)));
                board.put(source, new Pawn(TeamColor.WHITE));
                board.put(target, new Pawn(TeamColor.BLACK));
                return board;
            }
        });

        assertDoesNotThrow(() -> chessBoard.move(source, target));
    }

    @ParameterizedTest
    @MethodSource("attackPawnPositions")
    @DisplayName("Pawn 의 공격은 대각선 한칸이며, target 이 없다면 실패한다.")
    void checkPawnAttackFail(Position target) {
        Position source = Position.of(XPosition.B, YPosition.TWO);
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                Arrays.stream(XPosition.values())
                        .forEach(x -> Arrays.stream(YPosition.values())
                                .forEach(y -> board.put(Position.of(x, y), null)));
                board.put(source, new Pawn(TeamColor.WHITE));
                return board;
            }
        });

        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Position> attackPawnPositions() {
        return Stream.of(
                Position.of(XPosition.A, YPosition.THREE),
                Position.of(XPosition.C, YPosition.THREE)
        );
    }

    @ParameterizedTest
    @MethodSource("attackPawnImpossiblePositions")
    @DisplayName("Pawn 의 공격은 대각선 한칸이다. (실패테스트)")
    void checkPawnAttackFailByUnrelatedPositions (Position target) {
        Position source = Position.of(XPosition.B, YPosition.TWO);
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                Arrays.stream(XPosition.values())
                        .forEach(x -> Arrays.stream(YPosition.values())
                                .forEach(y -> board.put(Position.of(x, y), null)));
                board.put(source, new Pawn(TeamColor.WHITE));
                board.put(target, new Pawn(TeamColor.BLACK));
                return board;
            }
        });

        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Position> attackPawnImpossiblePositions() {
        return Stream.of(
                Position.of(XPosition.A, YPosition.FOUR),
                Position.of(XPosition.B, YPosition.THREE),
                Position.of(XPosition.B, YPosition.FOUR),
                Position.of(XPosition.C, YPosition.FOUR)
        );
    }
}
