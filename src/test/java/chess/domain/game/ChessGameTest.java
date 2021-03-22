package chess.domain.game;

import static chess.domain.piece.type.PieceWithColorType.B_BP;
import static chess.domain.piece.type.PieceWithColorType.B_KG;
import static chess.domain.piece.type.PieceWithColorType.B_NT;
import static chess.domain.piece.type.PieceWithColorType.B_PN;
import static chess.domain.piece.type.PieceWithColorType.B_QN;
import static chess.domain.piece.type.PieceWithColorType.B_RK;
import static chess.domain.piece.type.PieceWithColorType.W_BP;
import static chess.domain.piece.type.PieceWithColorType.W_KG;
import static chess.domain.piece.type.PieceWithColorType.W_NT;
import static chess.domain.piece.type.PieceWithColorType.W_PN;
import static chess.domain.piece.type.PieceWithColorType.W_QN;
import static chess.domain.piece.type.PieceWithColorType.W_RK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.dto.request.CommandRequestDTO;
import chess.controller.dto.request.MoveRequestDTO;
import chess.controller.dto.response.ScoresResponseDTO;
import chess.domain.board.setting.BoardCustomSetting;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.board.setting.BoardSetting;
import chess.utils.PositionConverter;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ChessGameTest {
    private static final String MOVE = "move";
    private static final String EMPTY_CELL_STATUS = ".";

    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(new BoardDefaultSetting());
    }


    @DisplayName("King이 잡혔는지 확인")
    @Nested
    class KingDead {
        @DisplayName("King이 1개만 잡혔을 때")
        @Test
        void isOneKingDead() {
            BoardSetting customBoardSetting = new BoardCustomSetting(
                Arrays.asList(
                    null, B_KG, B_RK, null, null, null, null, null,
                    B_PN, null, B_PN, B_BP, null, null, null, null,
                    null, B_PN, null, null, B_QN, null, null, null,
                    null, null, null, null, null, null, null, null,
                    null, null, null, null, null, W_NT, W_QN, null,
                    null, null, null, null, null, W_PN, null, W_PN,
                    null, null, null, null, null, W_PN, W_PN, null,
                    null, null, null, null, W_RK, null, null, null)
            );

            ChessGame chessGame = new ChessGame(customBoardSetting);

            assertThat(chessGame.isKingDead()).isTrue();
        }

        @DisplayName("2개의 킹들이 모두 잡혔을 때")
        @Test
        void isAllKingsDead() {
            BoardSetting customBoardSetting = new BoardCustomSetting(
                Arrays.asList(
                    null, null, B_RK, null, null, null, null, null,
                    B_PN, null, B_PN, B_BP, null, null, null, null,
                    null, B_PN, null, null, B_QN, null, null, null,
                    null, null, null, null, null, null, null, null,
                    null, null, null, null, null, W_NT, W_QN, null,
                    null, null, null, null, null, W_PN, null, W_PN,
                    null, null, null, null, null, W_PN, W_PN, null,
                    null, null, null, null, W_RK, null, null, null)
            );

            ChessGame chessGame = new ChessGame(customBoardSetting);

            assertThat(chessGame.isKingDead()).isTrue();
        }

        @DisplayName("King이 잡히지 않았을 때")
        @Test
        void isNotKingDead() {
            BoardSetting customBoardSetting = new BoardCustomSetting(
                Arrays.asList(
                    null, B_KG, B_RK, null, null, null, null, null,
                    B_PN, null, B_PN, B_BP, null, null, null, null,
                    null, B_PN, null, null, B_QN, null, null, null,
                    null, null, null, null, null, null, null, null,
                    null, null, null, null, null, W_NT, W_QN, null,
                    null, null, null, null, null, W_PN, null, W_PN,
                    null, null, null, null, null, W_PN, W_PN, null,
                    null, null, null, null, W_RK, W_KG, null, null)
            );

            ChessGame chessGame = new ChessGame(customBoardSetting);

            assertThat(chessGame.isKingDead()).isFalse();
        }
    }

    @DisplayName("점수 계산")
    @Nested
    class ScoreCalculate {
        @DisplayName("Pawn이 한 File에 2개 이상 존재하는 경우")
        @Test
        void scores() {
            BoardSetting customBoardSetting = new BoardCustomSetting(
                Arrays.asList(
                    null, B_KG, B_RK, null, null, null, null, null,
                    B_PN, null, B_PN, B_BP, null, null, null, null,
                    null, B_PN, null, null, B_QN, null, null, null,
                    null, null, null, null, null, null, null, null,
                    null, null, null, null, null, W_NT, W_QN, null,
                    null, null, null, null, null, W_PN, null, W_PN,
                    null, null, null, null, null, W_PN, W_PN, null,
                    null, null, null, null, W_RK, W_KG, null, null)
            );

            ChessGame chessGame = new ChessGame(customBoardSetting);
            ScoresResponseDTO scoresResponseDTO = chessGame.getScores();

            assertThat(scoresResponseDTO.getBlackTeamScore()).isEqualTo(20);
            assertThat(scoresResponseDTO.getWhiteTeamScore()).isEqualTo(19.5);
        }

        @DisplayName("기물을 이동하여 적 기물을 잡은 후, Pawn이 한 File에 2개 이상 존재하는 경우")
        @Test
        void scoresAfterKillEnemyPieceByPawn() {
            BoardSetting customBoardSetting = new BoardCustomSetting(
                Arrays.asList(
                    B_RK, B_NT, B_BP, B_QN, B_KG, B_BP, B_NT, B_RK,
                    B_PN, null, B_PN, B_PN, B_PN, B_PN, B_PN, B_PN,
                    null, null, null, null, null, null, null, null,
                    null, B_PN, null, null, null, null, null, null,
                    W_PN, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null,
                    null, W_PN, W_PN, W_PN, W_PN, W_PN, W_PN, W_PN,
                    W_RK, W_NT, W_BP, W_QN, W_KG, W_BP, W_NT, W_RK)
            );

            ChessGame chessGame = new ChessGame(customBoardSetting);
            CommandRequestDTO commandRequestDTO
                = new CommandRequestDTO("move", "a4", "b5");
            MoveRequestDTO moveRequestDTO = new MoveRequestDTO("white", commandRequestDTO);

            chessGame.move(moveRequestDTO);
            ScoresResponseDTO scoresResponseDTO = chessGame.getScores();

            assertThat(scoresResponseDTO.getBlackTeamScore()).isEqualTo(37);
            assertThat(scoresResponseDTO.getWhiteTeamScore()).isEqualTo(37);
        }
    }

    @DisplayName("말 이동 가능 여부 판단")
    @Nested
    class Movable {

        @DisplayName("Rook, Bishop, Queen, King")
        @Nested
        class RookBishopQueenKing {
            private static final String colorOfPieceToMove = "black";

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

                    assertCannotMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCannotMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCannotMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCannotMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCannotMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCannotMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCannotMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCannotMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCannotMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCannotMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCannotMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                    assertCanMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
                }
            }
        }

        @DisplayName("Knight 이동")
        @Nested
        class Knight {
            private static final String colorOfPieceToMove = "black";

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

                assertCannotMove(chessGame, startPositionInput, destinationInput,
                    colorOfPieceToMove);
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

                assertCanMove(chessGame, startPositionInput, destinationInput, colorOfPieceToMove);
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

                assertCanMove(chessGame, startPositionInput, destinationInput, colorOfPieceToMove);
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

                assertCanMove(chessGame, startPositionInput, destinationInput, colorOfPieceToMove);
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

                assertCanMove(chessGame, startPositionInput, destinationInput, colorOfPieceToMove);
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

                assertCanMove(chessGame, startPositionInput, destinationInput, colorOfPieceToMove);
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

                assertCanMove(chessGame, startPositionInput, destinationInput, colorOfPieceToMove);
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

                assertCanMove(chessGame, startPositionInput, destinationInput, colorOfPieceToMove);
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

                assertCanMove(chessGame, startPositionInput, destinationInput, colorOfPieceToMove);
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

                assertCannotMove(chessGame, startPositionInput, destinationInput,
                    colorOfPieceToMove);
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

                assertCanMove(chessGame, startPositionInput, destinationInput, colorOfPieceToMove);
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

                assertCanMove(chessGame, startPositionInput, destinationInput, colorOfPieceToMove);
            }
        }

        @DisplayName("Pawn 이동")
        @Nested
        class Pawn {
            @DisplayName("흑 팀인 경우")
            @Nested
            class BlackTeam {
                private static final String colorOfPieceToMove = "black";

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

                    assertCannotMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                        assertCanMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCannotMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCannotMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCannotMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCanMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCannotMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCannotMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCannotMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCanMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCanMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCannotMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCannotMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
                    }
                }
            }

            @DisplayName("백 팀인 경우")
            @Nested
            class WhiteTeam {
                private static final String colorOfPieceToMove = "white";

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

                    assertCannotMove(chessGame, startPositionInput, destinationInput,
                        colorOfPieceToMove);
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

                        assertCanMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCannotMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCannotMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCannotMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCanMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCannotMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCannotMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCannotMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCanMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCanMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCannotMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
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

                        assertCannotMove(chessGame, startPositionInput, destinationInput,
                            colorOfPieceToMove);
                    }
                }
            }
        }
    }

    private void assertCanMove(ChessGame chessGame, String startPositionInput,
        String destinationInput, String colorOfMovingPiece) {

        List<String> cellsStatus = chessGame.boardStatus().getCellsStatus();
        String pieceToMove
            = cellsStatus.get(PositionConverter.convertToCellsStatusIndex(startPositionInput));

        CommandRequestDTO commandRequestDTO
            = new CommandRequestDTO(MOVE, startPositionInput, destinationInput);
        MoveRequestDTO moveRequestDTO = new MoveRequestDTO(colorOfMovingPiece, commandRequestDTO);

        chessGame.move(moveRequestDTO);
        List<String> cellsStatusAfterMove = chessGame.boardStatus().getCellsStatus();

        assertPiecePosition(startPositionInput, EMPTY_CELL_STATUS, cellsStatusAfterMove);
        assertPiecePosition(destinationInput, pieceToMove, cellsStatusAfterMove);
    }

    private void assertPiecePosition(String positionInput, String expectedCellStatus,
        List<String> actualCellsStatus) {

        assertThat(
            actualCellsStatus.get(
                PositionConverter.convertToCellsStatusIndex(positionInput)
            )
        ).isEqualTo(expectedCellStatus);
    }

    private void assertCannotMove(ChessGame chessGame, String startPositionInput,
        String destinationInput, String colorOfMovingPiece) {

        List<String> cellsStatus = chessGame.boardStatus().getCellsStatus();

        String startPositionCellStatus
            = cellsStatus.get(PositionConverter.convertToCellsStatusIndex(startPositionInput));
        String destinationCellStatus
            = cellsStatus.get(PositionConverter.convertToCellsStatusIndex(destinationInput));

        CommandRequestDTO commandRequestDTO
            = new CommandRequestDTO(MOVE, startPositionInput, destinationInput);
        MoveRequestDTO moveRequestDTO = new MoveRequestDTO(colorOfMovingPiece, commandRequestDTO);

        assertThatThrownBy(() -> chessGame.move(moveRequestDTO))
            .isInstanceOf(IllegalArgumentException.class);

        List<String> cellsStatusAfterMove = chessGame.boardStatus().getCellsStatus();

        assertPiecePosition(startPositionInput, startPositionCellStatus, cellsStatusAfterMove);
        assertPiecePosition(destinationInput, destinationCellStatus, cellsStatusAfterMove);
    }
}