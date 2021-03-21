package chess.domain.piece;

import static chess.domain.piece.type.PieceWithColorType.B_BP;
import static chess.domain.piece.type.PieceWithColorType.B_KG;
import static chess.domain.piece.type.PieceWithColorType.B_NT;
import static chess.domain.piece.type.PieceWithColorType.B_PN;
import static chess.domain.piece.type.PieceWithColorType.B_QN;
import static chess.domain.piece.type.PieceWithColorType.B_RK;
import static chess.domain.piece.type.PieceWithColorType.W_BP;
import static chess.domain.piece.type.PieceWithColorType.W_PN;
import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.setting.BoardCustomSetting;
import chess.domain.board.setting.BoardSetting;
import chess.domain.game.ChessGame;
import chess.domain.piece.type.PieceType;
import chess.domain.player.type.TeamColor;
import chess.domain.position.MoveRoute;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PieceTest {
    private List<Piece> expectedPieces;

    @BeforeEach
    void setUp() {
        expectedPieces = Arrays.asList(
            new Pawn(BLACK),
            new Rook(BLACK),
            new Knight(BLACK),
            new Bishop(BLACK),
            new Queen(BLACK),
            new King(BLACK),
            new Pawn(WHITE),
            new Rook(WHITE),
            new Knight(WHITE),
            new Bishop(WHITE),
            new Queen(WHITE),
            new King(WHITE)
        );
    }

    @DisplayName("기물 캐싱")
    @Test
    void piecesCaching() {
        List<Piece> cachedPieces = new ArrayList<>();
        for (TeamColor teamColor : TeamColor.values()) {
            assertPiecesCachingWithColor(teamColor, cachedPieces);
        }
        assertThat(cachedPieces).containsExactlyInAnyOrderElementsOf(expectedPieces);
    }

    private void assertPiecesCachingWithColor(TeamColor teamColor, List<Piece> cachedPieces) {
        for (PieceType pieceType : PieceType.values()) {
            Piece cachedPiece = Piece.of(pieceType, teamColor);
            cachedPieces.add(cachedPiece);
        }
    }

    @DisplayName("말 이동 가능 여부 판단")
    @Nested
    class Movable {

        @DisplayName("Rook, Bishop, Queen, King")
        @Nested
        class RookBishopQueenKing {
            @DisplayName("Rook 이동")
            @Nested
            class Rook {
                @DisplayName("유효하지 않은 경로로 이동할 수 없다.")
                @ParameterizedTest
                @ValueSource(strings = {"b7", "e7", "g1"})
                void cannotMoveInvalidRoute(String destinationInput) {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_RK, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);
                    String startPositionInput = "d5";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCannotMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("위 방향으로 이동")
                @Test
                void moveUp() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_RK, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "d8";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("아래 방향으로 이동")
                @Test
                void moveDown() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_RK, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "d1";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("오른쪽 방향으로 이동")
                @Test
                void moveRight() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_RK, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "h5";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("왼쪽 방향으로 이동")
                @Test
                void moveLeft() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_RK, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "a5";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("이동경로 중간에 기물이 존재하면, 이동할 수 없다.")
                @Test
                void cannotMoveWhenPieceExistsOnRoute() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, W_BP, null, B_RK, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "a5";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCannotMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("도착위치에 아군 기물이 존재하면, 이동할 수 없다.")
                @Test
                void cannotMoveWhenMyPieceExistsAtDestination() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            B_PN, null, null, B_RK, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "a5";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCannotMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("도착위치에 적 기물이 존재하면, 이동할 수 있다.")
                @Test
                void canMoveWhenEnemyPieceExistsAtDestination() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            W_BP, null, null, B_RK, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "a5";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }
            }

            @DisplayName("Bishop 이동")
            @Nested
            class Bishop {
                @DisplayName("유효하지 않은 경로로 이동할 수 없다.")
                @ParameterizedTest
                @ValueSource(strings = {"b5", "e7", "g1"})
                void cannotMoveInvalidRoute(String destinationInput) {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_BP, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);
                    String startPositionInput = "d5";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCannotMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("왼쪽 위 대각선 방향으로 이동")
                @Test
                void moveLeftUpDiagonal() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_BP, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "a8";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("왼쪽 아래 대각선 방향으로 이동")
                @Test
                void moveLeftDownDiagonal() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_BP, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "a2";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("오른쪽 위 대각선 방향으로 이동")
                @Test
                void moveRightUpDiagonal() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_BP, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "g8";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("오른쪽 아래 대각선 방향으로 이동")
                @Test
                void moveRightDownDiagonal() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_BP, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "h1";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("이동경로 중간에 기물이 존재하면, 이동할 수 없다.")
                @Test
                void cannotMoveWhenPieceExistsOnRoute() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, W_BP, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_BP, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "a8";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCannotMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("도착위치에 아군 기물이 존재하면, 이동할 수 없다.")
                @Test
                void cannotMoveWhenMyPieceExistsAtDestination() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            B_PN, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_BP, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "a8";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCannotMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("도착위치에 적 기물이 존재하면, 이동할 수 있다.")
                @Test
                void canMoveWhenEnemyPieceExistsAtDestination() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            W_BP, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_BP, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "a8";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }
            }

            @DisplayName("Queen 이동")
            @Nested
            class Queen {
                @DisplayName("유효하지 않은 경로로 이동할 수 없다.")
                @ParameterizedTest
                @ValueSource(strings = {"b5", "e7", "g1"})
                void cannotMoveInvalidRoute(String destinationInput) {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_BP, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCannotMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("위 방향으로 이동")
                @Test
                void moveUp() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_QN, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "d8";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("아래 방향으로 이동")
                @Test
                void moveDown() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_QN, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "d1";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("오른쪽 방향으로 이동")
                @Test
                void moveRight() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_QN, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "h5";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("왼쪽 방향으로 이동")
                @Test
                void moveLeft() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_QN, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "a5";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("왼쪽 위 대각선 방향으로 이동")
                @Test
                void moveLeftUpDiagonal() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_QN, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "a8";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("왼쪽 아래 대각선 방향으로 이동")
                @Test
                void moveLeftDownDiagonal() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_QN, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "a2";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("오른쪽 위 대각선 방향으로 이동")
                @Test
                void moveRightUpDiagonal() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_QN, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "g8";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("오른쪽 아래 대각선 방향으로 이동")
                @Test
                void moveRightDownDiagonal() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_QN, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "h1";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("이동경로 중간에 기물이 존재하면, 이동할 수 없다.")
                @Test
                void cannotMoveWhenPieceExistsOnRoute() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, W_BP, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_QN, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "a8";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCannotMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("도착위치에 아군 기물이 존재하면, 이동할 수 없다.")
                @Test
                void cannotMoveWhenMyPieceExistsAtDestination() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            B_PN, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_QN, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "a8";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCannotMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("도착위치에 적 기물이 존재하면, 이동할 수 있다.")
                @Test
                void canMoveWhenEnemyPieceExistsAtDestination() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            W_BP, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_QN, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "a8";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }
            }

            @DisplayName("King 이동")
            @Nested
            class King {
                @DisplayName("유효하지 않은 경로로 이동할 수 없다.")
                @ParameterizedTest
                @ValueSource(strings = {"b7", "e7", "d3"})
                void cannotMoveInvalidRoute(String destinationInput) {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_KG, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);
                    String startPositionInput = "d5";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCannotMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("위 방향으로 한 칸 이동")
                @Test
                void moveUp() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_KG, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "d6";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("아래 방향으로 한 칸 이동")
                @Test
                void moveDown() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_KG, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "d4";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("오른쪽 방향으로 한 칸 이동")
                @Test
                void moveRight() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_KG, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "e5";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("왼쪽 방향으로 한 칸 이동")
                @Test
                void moveLeft() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_KG, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "c5";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("왼쪽 위 대각선 방향으로 한 칸 이동")
                @Test
                void moveLeftUpDiagonal() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_KG, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "c6";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("왼쪽 아래 대각선 방향으로 한 칸 이동")
                @Test
                void moveLeftDownDiagonal() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_KG, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "c4";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("오른쪽 위 대각선 방향으로 한 칸 이동")
                @Test
                void moveRightUpDiagonal() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_KG, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "e6";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("오른쪽 아래 대각선 방향으로 한 칸 이동")
                @Test
                void moveRightDownDiagonal() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_KG, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "e4";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("도착위치에 아군 기물이 존재하면, 이동할 수 없다.")
                @Test
                void cannotMoveWhenMyPieceExistsAtDestination() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, B_PN, null, null, null, null, null,
                            null, null, null, B_KG, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "c6";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCannotMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("도착위치에 적 기물이 존재하면, 이동할 수 있다.")
                @Test
                void canMoveWhenEnemyPieceExistsAtDestination() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, W_BP, null, null, null, null, null,
                            null, null, null, B_KG, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "c6";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }
            }

            @DisplayName("Knight 이동")
            @Nested
            class Knight {
                @DisplayName("유효하지 않은 경로로 이동할 수 없다.")
                @ParameterizedTest
                @ValueSource(strings = {"b7", "d8", "f3"})
                void cannotMoveInvalidRoute(String destinationInput) {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_NT, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);
                    String startPositionInput = "d5";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCannotMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("왼쪽 왼쪽 위 방향으로 한 번 이동")
                @Test
                void moveLeftLeftUp() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_NT, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "b6";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("왼쪽 왼쪽 아래 방향으로 한 번 이동")
                @Test
                void moveLeftLeftDown() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_NT, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "c3";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("왼쪽 위 위 방향으로 한 번 이동")
                @Test
                void moveLeftUpUp() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_NT, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "c7";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("왼쪽 아래 아래 방향으로 한 번 이동")
                @Test
                void moveLeftDownDown() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_NT, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "c3";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("오른쪽 오른쪽 위 방향으로 한 번 이동")
                @Test
                void moveRightRightUp() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_NT, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "f6";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("오른쪽 오른쪽 아래 방향으로 한 번 이동")
                @Test
                void moveRightRightDown() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_NT, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "f4";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("오른쪽 위 위 방향으로 한 번 이동")
                @Test
                void moveRightUpUp() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_NT, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "e7";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("오른쪽 아래 아래 방향으로 한 번 이동")
                @Test
                void moveRightDownDown() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_NT, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "e3";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("도착위치에 아군 기물이 존재하면, 이동할 수 없다.")
                @Test
                void cannotMoveWhenMyPieceExistsAtDestination() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, B_PN, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_NT, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "c7";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCannotMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("이동 경로 중간에 적 기물이 존재해도, 이동할 수 있다.")
                @Test
                void canMoveWhenEnemyPieceExistsOnRoute() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, W_BP, null, null, null, null,
                            null, null, null, B_NT, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "c7";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }

                @DisplayName("도착위치에 적 기물이 존재하면, 이동할 수 있다.")
                @Test
                void canMoveWhenEnemyPieceExistsAtDestination() {
                    BoardSetting customBoardSetting = new BoardCustomSetting(
                        Arrays.asList(
                            null, null, null, null, null, null, null, null,
                            null, null, W_BP, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, B_NT, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null)
                    );

                    ChessGame chessGame = new ChessGame(customBoardSetting);

                    String startPositionInput = "d5";
                    String destinationInput = "c7";
                    MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                    assertCanMoveTo(moveRoute, chessGame.board());
                }
            }

            @DisplayName("Pawn 이동")
            @Nested
            class Pawn {
                @DisplayName("흑 팀인 경우")
                @Nested
                class BlackTeam {
                    @DisplayName("유효하지 않은 경로로 이동할 수 없다.")
                    @ParameterizedTest
                    @ValueSource(strings = {"d2", "e3", "a5"})
                    void cannotMoveInvalidRoute(String destinationInput) {
                        BoardSetting customBoardSetting = new BoardCustomSetting(
                            Arrays.asList(
                                null, null, null, null, null, null, null, null,
                                null, null, null, null, null, null, null, null,
                                null, null, null, null, null, null, null, null,
                                null, null, null, B_PN, null, null, null, null,
                                null, null, null, null, null, null, null, null,
                                null, null, null, null, null, null, null, null,
                                null, null, null, null, null, null, null, null,
                                null, null, null, null, null, null, null, null)
                        );

                        ChessGame chessGame = new ChessGame(customBoardSetting);
                        String startPositionInput = "d5";
                        MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                        assertCannotMoveTo(moveRoute, chessGame.board());
                    }

                    @DisplayName("한 칸 전진")
                    @Nested
                    class MoveForwardOneCell {
                        @DisplayName("아래 방향으로 한 칸 이동")
                        @Test
                        void moveForwardOneCell() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, B_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d5";
                            String destinationInput = "d4";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCanMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("도착위치에 기물이 존재하면, 이동할 수 없다.")
                        @Test
                        void cannotMoveWhenPieceExistsAtDestination() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, B_PN, null, null, null, null,
                                    null, null, null, W_BP, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d5";
                            String destinationInput = "d4";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCannotMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("위 방향으로 이동할 수 없다.")
                        @Test
                        void cannotMoveBackwardOneCell() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, B_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d5";
                            String destinationInput = "d6";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCannotMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("처음 위치가 아닌 곳에서 앞으로 두 칸 전진할 수 없다.")
                        @Test
                        void cannotMoveForwardTwoCellWhenNotAtFirstPosition() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, B_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d5";
                            String destinationInput = "d3";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCannotMoveTo(moveRoute, chessGame.board());
                        }
                    }

                    @DisplayName("두 칸 전진")
                    @Nested
                    class MoveForwardTwoCells {
                        @DisplayName("처음 위치에 있을 때, 앞으로 두 칸 전진 이동")
                        @Test
                        void moveForwardTwoCellWhenAtFirstPosition() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, B_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d7";
                            String destinationInput = "d5";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCanMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("도착위치에 기물이 존재하면, 이동할 수 없다.")
                        @Test
                        void cannotMoveWhenPieceExistsAtDestination() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, B_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, W_BP, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d7";
                            String destinationInput = "d5";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCannotMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("이동 경로 중간에 기물이 존재하면, 이동할 수 없다.")
                        @Test
                        void cannotMoveWhenPieceExistsOnRoute() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, B_PN, null, null, null, null,
                                    null, null, null, W_BP, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d7";
                            String destinationInput = "d5";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCannotMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("위 방향으로 이동할 수 없다.")
                        @Test
                        void moveBackwardTwoCell() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, B_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d5";
                            String destinationInput = "d7";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCannotMoveTo(moveRoute, chessGame.board());
                        }
                    }

                    @DisplayName("대각선 한 칸 이동")
                    @Nested
                    class MoveDiagonalOneCell {
                        @DisplayName("적이 왼쪽 대각선에 있을 때, 이동 가능")
                        @Test
                        void moveDiagonalLeftWhenEnemyPieceExists() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, B_PN, null, null, null, null,
                                    null, null, W_BP, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d7";
                            String destinationInput = "c6";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCanMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("적이 오른쪽 대각선에 있을 때, 이동 가능")
                        @Test
                        void moveDiagonalRightWhenEnemyPieceExists() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, B_PN, null, null, null, null,
                                    null, null, null, null, W_BP, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d7";
                            String destinationInput = "e6";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCanMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("도착위치에 적이 존재하지 않을 때, 왼쪽 대각선 이동 불가능")
                        @Test
                        void cannotMoveDiagonalLeftWhenEnemyPieceNotExistsAtDestination() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, B_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d7";
                            String destinationInput = "c6";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCannotMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("도착위치에 적이 존재하지 않을 때, 오른쪽 대각선 이동 불가능")
                        @Test
                        void cannotMoveDiagonalRightWhenEnemyPieceNotExistsAtDestination() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, B_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d7";
                            String destinationInput = "e6";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCannotMoveTo(moveRoute, chessGame.board());
                        }
                    }
                }

                @DisplayName("백 팀인 경우")
                @Nested
                class WhiteTeam {
                    @DisplayName("유효하지 않은 경로로 이동할 수 없다.")
                    @ParameterizedTest
                    @ValueSource(strings = {"d8", "e7", "a8"})
                    void cannotMoveInvalidRoute(String destinationInput) {
                        BoardSetting customBoardSetting = new BoardCustomSetting(
                            Arrays.asList(
                                null, null, null, null, null, null, null, null,
                                null, null, null, null, null, null, null, null,
                                null, null, null, null, null, null, null, null,
                                null, null, null, W_PN, null, null, null, null,
                                null, null, null, null, null, null, null, null,
                                null, null, null, null, null, null, null, null,
                                null, null, null, null, null, null, null, null,
                                null, null, null, null, null, null, null, null)
                        );

                        ChessGame chessGame = new ChessGame(customBoardSetting);
                        String startPositionInput = "d5";
                        MoveRoute moveRoute = new MoveRoute(startPositionInput, destinationInput);

                        assertCannotMoveTo(moveRoute, chessGame.board());
                    }

                    @DisplayName("한 칸 전진")
                    @Nested
                    class MoveForwardOneCell {
                        @DisplayName("위 방향으로 한 칸 이동")
                        @Test
                        void moveForwardOneCell() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, W_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d5";
                            String destinationInput = "d6";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCanMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("도착위치에 기물이 존재하면, 이동할 수 없다.")
                        @Test
                        void cannotMoveWhenPieceExistsAtDestination() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, B_BP, null, null, null, null,
                                    null, null, null, W_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d5";
                            String destinationInput = "d6";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCannotMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("아래 방향으로 이동할 수 없다.")
                        @Test
                        void cannotMoveBackwardOneCell() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, W_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d5";
                            String destinationInput = "d4";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCannotMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("처음 위치가 아닌 곳에서 앞으로 두 칸 전진할 수 없다.")
                        @Test
                        void cannotMoveForwardTwoCellWhenNotAtFirstPosition() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, W_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d5";
                            String destinationInput = "d7";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCannotMoveTo(moveRoute, chessGame.board());
                        }
                    }

                    @DisplayName("두 칸 전진")
                    @Nested
                    class MoveForwardTwoCells {
                        @DisplayName("처음 위치에 있을 때, 앞으로 두 칸 전진 이동")
                        @Test
                        void moveForwardTwoCellWhenAtFirstPosition() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, W_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d2";
                            String destinationInput = "d4";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCanMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("도착위치에 기물이 존재하면, 이동할 수 없다.")
                        @Test
                        void cannotMoveWhenPieceExistsAtDestination() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, B_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, W_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d2";
                            String destinationInput = "d4";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCannotMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("이동 경로 중간에 기물이 존재하면, 이동할 수 없다.")
                        @Test
                        void cannotMoveWhenPieceExistsOnRoute() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, B_PN, null, null, null, null,
                                    null, null, null, B_PN, null, null, null, null,
                                    null, null, null, W_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d2";
                            String destinationInput = "d4";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCannotMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("아래 방향으로 이동할 수 없다.")
                        @Test
                        void moveBackwardTwoCell() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, W_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d3";
                            String destinationInput = "d1";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCannotMoveTo(moveRoute, chessGame.board());
                        }
                    }

                    @DisplayName("대각선 한 칸 이동")
                    @Nested
                    class MoveDiagonalOneCell {
                        @DisplayName("적이 왼쪽 대각선에 있을 때, 이동 가능")
                        @Test
                        void moveDiagonalLeftWhenEnemyPieceExists() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, B_PN, null, null, null, null, null,
                                    null, null, null, W_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d2";
                            String destinationInput = "c3";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCanMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("적이 오른쪽 대각선에 있을 때, 이동 가능")
                        @Test
                        void moveDiagonalRightWhenEnemyPieceExists() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, B_PN, null, null, null,
                                    null, null, null, W_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d2";
                            String destinationInput = "e3";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCanMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("도착위치에 적이 존재하지 않을 때, 왼쪽 대각선 이동 불가능")
                        @Test
                        void cannotMoveDiagonalLeftWhenEnemyPieceNotExistsAtDestination() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, W_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d2";
                            String destinationInput = "c3";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCannotMoveTo(moveRoute, chessGame.board());
                        }

                        @DisplayName("도착위치에 적이 존재하지 않을 때, 오른쪽 대각선 이동 불가능")
                        @Test
                        void cannotMoveDiagonalRightWhenEnemyPieceNotExistsAtDestination() {
                            BoardSetting customBoardSetting = new BoardCustomSetting(
                                Arrays.asList(
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null, null,
                                    null, null, null, W_PN, null, null, null, null,
                                    null, null, null, null, null, null, null, null)
                            );

                            ChessGame chessGame = new ChessGame(customBoardSetting);

                            String startPositionInput = "d2";
                            String destinationInput = "e3";
                            MoveRoute moveRoute
                                = new MoveRoute(startPositionInput, destinationInput);

                            assertCannotMoveTo(moveRoute, chessGame.board());
                        }
                    }
                }
            }
        }
    }

    private void assertCanMoveTo(MoveRoute moveRoute, Board board) {
        Piece piece = findPieceToMove(moveRoute, board);

        assertThat(piece.isMovableTo(moveRoute, board)).isTrue();
    }

    private void assertCannotMoveTo(MoveRoute moveRoute, Board board) {
        Piece piece = findPieceToMove(moveRoute, board);

        assertThatThrownBy(() -> piece.isMovableTo(moveRoute, board))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private Piece findPieceToMove(MoveRoute moveRoute, Board board) {
        return board.findPiece(
            Position.of(moveRoute.startPosition().file(), moveRoute.startPosition().rank())
        );
    }
}