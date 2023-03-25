package chess.domain.game;

import chess.domain.PositionFixtures;
import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.InitialWhitePawn;
import chess.domain.piece.pawn.WhitePawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static chess.domain.PositionFixtures.A1;
import static chess.domain.PositionFixtures.A2;
import static chess.domain.PositionFixtures.A3;
import static chess.domain.PositionFixtures.A4;
import static chess.domain.PositionFixtures.B1;
import static chess.domain.PositionFixtures.C1;
import static chess.domain.PositionFixtures.D1;
import static chess.domain.PositionFixtures.E1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {

    @DisplayName("보드의 사이즈가 8 x 8이 아니라면 생성시 예외를 반환한다.")
    @Test
    void create_fail() {
        assertThatThrownBy(() -> ChessGame.from(Collections.emptyMap()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("체스판의 사이즈는 8 x 8 여야합니다.");
    }

    private Map<Position, Piece> createEmptyBoard() {
        final Map<Position, Piece> board = new HashMap<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                board.put(Position.of(file, rank), Empty.instance());
            }
        }
        return board;
    }

    @DisplayName("일반적인 기물들의 이동 테스트 ")
    @Nested
    public class NormalPieceMoveTest {

        @DisplayName("정상적으로 이동이 가능하면 true를 반환한다.")
        @Test
        void success() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();

            final Position givenPosition = A1;
            rawBoard.put(givenPosition, Rook.instance(Team.WHITE));

            final ChessGame chessGame = ChessGame.from(rawBoard);
            //when && then
            assertThatNoException().isThrownBy(() -> chessGame.move(givenPosition, A3));
        }

        @DisplayName("행마법상 이동 불가능하다면 예외가 발생한다.")
        @Test
        void fail_by_cannot_movable() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();
            final Position givenPosition = A1;
            rawBoard.put(givenPosition, Rook.instance(Team.WHITE));
            final ChessGame chessGame = ChessGame.from(rawBoard);
            //when && then
            assertThatThrownBy(() -> chessGame.move(givenPosition, PositionFixtures.B2))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("행마법상 이동 불가능한 지역입니다.");
        }

        @DisplayName("도착 지점에 아군 기물이 있다면 예외가 발생한다.")
        @Test
        void fail_by_target_is_same_color() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();
            final Position givenSourcePosition = A1;
            rawBoard.put(givenSourcePosition, Rook.instance(Team.WHITE));

            final Position givenTargetPosition = PositionFixtures.A2;
            rawBoard.put(givenTargetPosition, Rook.instance(Team.WHITE));

            final ChessGame chessGame = ChessGame.from(rawBoard);
            //when && then
            assertThatThrownBy(() -> chessGame.move(givenSourcePosition, givenTargetPosition))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
        }

        @DisplayName("이동 경로에 기물이 존재한다면 false를 반환한다.")
        @Test
        void fail_by_path_exist_piece() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();

            final Position givenPosition = A1;
            rawBoard.put(givenPosition, Rook.instance(Team.WHITE));

            final Position givenPathPosition = PositionFixtures.A2;
            rawBoard.put(givenPathPosition, Rook.instance(Team.WHITE));

            final ChessGame chessGame = ChessGame.from(rawBoard);
            //when && then
            assertThatThrownBy(() -> chessGame.move(givenPosition, A3))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동하려는 경로에 기물이 존재합니다.");
        }
    }

    @DisplayName("Pawn의 이동을 테스트한다. ")
    @Nested
    public class FirstPawnMoveTest {

        @DisplayName("폰이 초기 위치가 아니면 두 칸 이동 시 예외가 발생한다.")
        @Test
        void fail_pawn1() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();

            final Position givenSourcePosition = A3;
            rawBoard.put(givenSourcePosition, WhitePawn.instance());

            final Position givenTargetPosition = PositionFixtures.A5;

            final ChessGame chessGame = ChessGame.from(rawBoard);
            //when  && then
            assertThatThrownBy(() -> chessGame.move(givenSourcePosition, givenTargetPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
        }

        @DisplayName("폰은 앞에 기물이 있으면 전진 시 예외가 발생한다.")
        @Test
        void fail_pawn2() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();

            final Position givenSourcePosition = A3;
            rawBoard.put(givenSourcePosition, WhitePawn.instance());

            final Position givenTargetPosition = PositionFixtures.A4;
            rawBoard.put(givenTargetPosition, BlackPawn.instance());

            final ChessGame chessGame = ChessGame.from(rawBoard);
            //when  && then
            assertThatThrownBy(() -> chessGame.move(givenSourcePosition, givenTargetPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
        }

        @DisplayName("폰은 대각선 방향에 적 기물이 없을 시 예외가 발생한다.")
        @Test
        void fail_pawn3() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();

            final Position givenSourcePosition = A3;
            rawBoard.put(givenSourcePosition, WhitePawn.instance());

            final Position givenTargetPosition = PositionFixtures.B4;

            final ChessGame chessGame = ChessGame.from(rawBoard);
            //when  && then
            assertThatThrownBy(() -> chessGame.move(givenSourcePosition, givenTargetPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
        }
    }

    @Nested
    class ScoreTest {
        Map<Position, Piece> rawBoard;

        @BeforeEach
        void init() {
            rawBoard = createEmptyBoard();
            rawBoard.put(A1, Rook.instance(Team.WHITE));
            rawBoard.put(B1, Knight.instance(Team.WHITE));
            rawBoard.put(C1, Bishop.instance(Team.WHITE));
            rawBoard.put(D1, Queen.instance(Team.WHITE));
            rawBoard.put(E1, King.instance(Team.WHITE));
        }

        @DisplayName("흰색 진영의 점수를 구한다.")
        @Test
        void score_test() {
            ChessGame chessGame = ChessGame.from(rawBoard);
            final double result = chessGame.calculateScoreByTeam(Team.WHITE);
            assertThat(result).isEqualTo(19.5);
        }

        @DisplayName("폰이 한 file에 존재하면 해당 폰들은 모두 0.5점 처리한다.")
        @Test
        void score_test2() {
            rawBoard.put(A2, InitialWhitePawn.instance());
            rawBoard.put(A3, WhitePawn.instance());
            final ChessGame chessGame = ChessGame.from(rawBoard);

            final double result = chessGame.calculateScoreByTeam(Team.WHITE);
            assertThat(result).isEqualTo(20.5);
        }

        @DisplayName("폰이 한 file에 존재하면 해당 폰들은 모두 0.5점 처리한다.")
        @Test
        void score_test3() {
            rawBoard.put(A2, InitialWhitePawn.instance());
            rawBoard.put(A3, WhitePawn.instance());
            rawBoard.put(A4, WhitePawn.instance());
            final ChessGame chessGame = ChessGame.from(rawBoard);

            final double result = chessGame.calculateScoreByTeam(Team.WHITE);
            assertThat(result).isEqualTo(21.0);
        }

        @DisplayName("검은색 진영의 점수를 구한다.")
        @Test
        void score_test4() {
            final ChessGame chessGame = ChessGame.from(rawBoard);

            final double result = chessGame.calculateScoreByTeam(Team.BLACK);
            assertThat(result).isEqualTo(0);
        }
    }
}
