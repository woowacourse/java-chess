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
        Position source = new Position(XPosition.B, YPosition.THREE);
        Position target = new Position(XPosition.B, YPosition.FOUR);

        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("자신의 턴에 자신의 말만 이동할 수 있다.(성공)")
    void moveOwnTurnOwnPieceSuccess() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        Position source = new Position(XPosition.B, YPosition.TWO);
        Position target = new Position(XPosition.B, YPosition.THREE);
        assertDoesNotThrow(() -> chessBoard.move(source, target));
    }

    @Test
    @DisplayName("자신의 턴에 자신의 말만 이동할 수 있다.(실패)")
    void moveOwnTurnOwnPieceFail() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        Position source = new Position(XPosition.B, YPosition.SEVEN);
        Position target = new Position(XPosition.B, YPosition.SIX);

        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("move 이후에 Turn 이 변경된다.")
    void changeTurnAfterMove() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        Position whiteTurnSource = new Position(XPosition.B, YPosition.TWO);
        Position whiteTurnTarget = new Position(XPosition.B, YPosition.THREE);
        Position blackTurnSource = new Position(XPosition.B, YPosition.SEVEN);
        Position blackTurnTarget = new Position(XPosition.B, YPosition.SIX);

        chessBoard.move(whiteTurnSource, whiteTurnTarget);
        assertDoesNotThrow(() -> chessBoard.move(blackTurnSource, blackTurnTarget));
    }

    @ParameterizedTest
    @MethodSource("availableTargets")
    @DisplayName("target은 source와 다른 팀이거나 null 이다.")
    void checkTargetSuccess(Piece targetPiece) {
        Position source = new Position(XPosition.A, YPosition.ONE);
        Position target = new Position(XPosition.A, YPosition.EIGHT);
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                Arrays.stream(XPosition.values())
                        .forEach(x -> Arrays.stream(YPosition.values())
                                .forEach(y -> board.put(new Position(x, y), null)));
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
        Position source = new Position(XPosition.A, YPosition.ONE);
        Position target = new Position(XPosition.A, YPosition.EIGHT);
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                Arrays.stream(XPosition.values())
                        .forEach(x -> Arrays.stream(YPosition.values())
                                .forEach(y -> board.put(new Position(x, y), null)));
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
        Position source = new Position(XPosition.A, YPosition.ONE);
        Position waypoint = new Position(XPosition.C, YPosition.THREE);
        Position target = new Position(XPosition.E, YPosition.FIVE);
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                Arrays.stream(XPosition.values())
                        .forEach(x -> Arrays.stream(YPosition.values())
                                .forEach(y -> board.put(new Position(x, y), null)));
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
        Position source = new Position(XPosition.B, YPosition.ONE);
        assertDoesNotThrow(() -> chessBoard.move(source, target));
    }

    private static Stream<Position> availableKnights() {
        return Stream.of(
                new Position(XPosition.A, YPosition.THREE),
                new Position(XPosition.C, YPosition.THREE)
        );
    }
    /*
    1. 폰이 이동하는 경우
        - 상대 말이 있을 때 - x
        - 상대 말이 없을 때 - o
    2. 폰이 공격하는 경우
        - 상대 말이 있을 때 - o
        - 상대 말이 없을 때 - x
     */
    @ParameterizedTest
    @MethodSource("availableMovePawns")
    @DisplayName("Pawn은 상대 말이 없을 때 이동할 수 있다.")
    void checkPawnMoveNoOpponent(Position target) {
        Position source = new Position(XPosition.B, YPosition.TWO);
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                Arrays.stream(XPosition.values())
                        .forEach(x -> Arrays.stream(YPosition.values())
                                .forEach(y -> board.put(new Position(x, y), null)));
                board.put(source, new Pawn(TeamColor.WHITE));
                return board;
            }
        });

        assertDoesNotThrow(() -> chessBoard.move(source, target));
    }

    private static Stream<Position> availableMovePawns() {
        return Stream.of(
                new Position(XPosition.B, YPosition.THREE),
                new Position(XPosition.B, YPosition.FOUR)
        );
    }

//    @Test
//    @DisplayName("Pawn은 이동 위치에 상대방의 말이 있다면 이동할 수 없다.")
//    void checkPawnImmovableByOpponent() {
//        Position source = new Position(XPosition.B, YPosition.TWO);
//        Position target = new Position(XPosition.B, YPosition.THREE);
//        Position otherTeam = new Position(XPosition.B, YPosition.THREE);
//        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
//            @Override
//            public Map<Position, Piece> generate() {
//                Map<Position, Piece> board = new HashMap<>();
//                Arrays.stream(XPosition.values())
//                        .forEach(x -> Arrays.stream(YPosition.values())
//                                .forEach(y -> board.put(new Position(x, y), null)));
//                board.put(source, new Pawn(TeamColor.WHITE));
//                board.put(otherTeam, new Pawn(TeamColor.BLACK));
//                return board;
//            }
//        });
//
//        assertThatThrownBy(() -> chessBoard.move(source, target))
//                .isInstanceOf(IllegalArgumentException.class);
//    }
}
