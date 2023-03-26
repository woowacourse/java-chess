package chess.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.game.state.running.BlackTurnState;
import chess.game.state.running.WhiteTurnState;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChessGameTest {

    ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame();
    }

    @Test
    @DisplayName("게임이 대기 상태일때 체스말을 움직이면 예외가 발생한다.")
    void ChessGame_MoveWhenWaitingState() {
        // given
        Position target = Position.of(1, 1);
        Position source = Position.of(2, 2);

        // expect
        assertThatThrownBy(() -> chessGame.movePiece(target, source))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 잘못된 게임의 상태 입니다.(상태: 대기중)");
    }

    @Test
    @DisplayName("게임이 종료 상태일때 체스말을 움직이면 예외가 발생한다.")
    void ChessGame_MoveWhenEndState() {
        // given
        Position target = Position.of(1, 1);
        Position source = Position.of(2, 2);

        // when
        chessGame.end();

        // expect
        assertThatThrownBy(() -> chessGame.movePiece(target, source))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 잘못된 게임의 상태 입니다.(상태: 종료됨)");
    }

    @Test
    @DisplayName("게임이 진행 상태일때 게임을 시작하면 예외가 발생한다.")
    void ChessGame_StartWhenRunningState() {
        // given
        TurnStrategy turnStrategy = new RandomTurnStrategy();

        // when
        chessGame.start(turnStrategy);

        // then
        assertThatThrownBy(() -> chessGame.start(turnStrategy))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 잘못된 게임의 상태 입니다.(상태: 진행중)");
    }

    @Test
    @DisplayName("게임이 종료 상태일때 게임을 시작하면 예외가 발생한다.")
    void ChessGame_StartWhenEndState() {
        // given
        TurnStrategy turnStrategy = new RandomTurnStrategy();

        // when
        chessGame.end();

        // then
        assertThatThrownBy(() -> chessGame.start(turnStrategy))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 잘못된 게임의 상태 입니다.(상태: 종료됨)");
    }

    @Test
    @DisplayName("게임이 대기 상태일때 체스판을 가져오면 예외가 발생한다.")
    void ChessGame_GetBoardWhenWaitingState() {
        // expect
        assertThatThrownBy(() -> chessGame.getBoard())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 잘못된 게임의 상태 입니다.(상태: 대기중)");
    }


    @Test
    @DisplayName("게임이 종료 상태일때 체스판을 가져오면 예외가 발생한다.")
    void ChessGame_GetBoardWhenEndState() {
        // when
        chessGame.end();

        // expect
        assertThatThrownBy(() -> chessGame.getBoard())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 잘못된 게임의 상태 입니다.(상태: 종료됨)");
    }

    @Test
    @DisplayName("나의 턴이 아닌데 말을 움직이면 예외가 발생한다.")
    void move_Invalid_Turn() {
        // given
        chessGame.start(new MockTurnStrategy(BlackTurnState.STATE));

        Position source = Position.of(2, 1);
        Position target = Position.of(2, 3);

        // expect
        assertThatThrownBy(() -> chessGame.movePiece(source, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 해당 팀의 턴이 아닙니다.");
    }

    @Test
    @DisplayName("턴을 바꾸면 상대방의 턴으로 넘어가야 한다.")
    void move_Next_Turn() {
        // given
        chessGame.start(new MockTurnStrategy(WhiteTurnState.STATE));

        Position source = Position.of(2, 1);
        Position target = Position.of(2, 3);

        // when
        chessGame.changeTurn();

        // then
        assertThatThrownBy(() -> chessGame.movePiece(source, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 해당 팀의 턴이 아닙니다.");
    }

    @Test
    @DisplayName("빈 칸을 움직이면 예외가 발생해야 한다.")
    void move_EmptySquare() {
        // given
        chessGame.start(new MockTurnStrategy(WhiteTurnState.STATE));

        Position source = Position.of(0, 3);
        Position target = Position.of(0, 4);

        // expect
        assertThatThrownBy(() -> chessGame.movePiece(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 빈 칸은 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("각 팀별 점수가 정확하게 계산되어야 한다.")
    void getTeamScore_Success() {
        // given
        Map<Position, Piece> squares = BoardFactory.createEmptyBoard();
        squares.put(Position.of(0, 6), Pawn.of(Team.BLACK));
        squares.put(Position.of(1, 5), Pawn.of(Team.BLACK));
        squares.put(Position.of(1, 7), Pawn.of(Team.BLACK));
        squares.put(Position.of(2, 7), Rook.of(Team.BLACK));
        squares.put(Position.of(2, 6), Pawn.of(Team.BLACK));
        squares.put(Position.of(3, 6), Bishop.of(Team.BLACK));
        squares.put(Position.of(4, 5), Queen.of(Team.BLACK));

        squares.put(Position.of(4, 0), Rook.of(Team.WHITE));
        squares.put(Position.of(5, 0), King.of(Team.WHITE));
        squares.put(Position.of(5, 1), Pawn.of(Team.WHITE));
        squares.put(Position.of(5, 2), Pawn.of(Team.WHITE));
        squares.put(Position.of(5, 3), Knight.of(Team.WHITE));
        squares.put(Position.of(6, 1), Pawn.of(Team.WHITE));
        squares.put(Position.of(6, 3), Queen.of(Team.WHITE));
        squares.put(Position.of(7, 2), Pawn.of(Team.WHITE));
        chessGame.load(() -> new Board(squares), () -> WhiteTurnState.STATE);
        // when
        double whiteScore = chessGame.getTeamScore(Team.WHITE);
        double blackScore = chessGame.getTeamScore(Team.BLACK);

        // then
        assertThat(blackScore)
                .isEqualTo(20);
        assertThat(whiteScore)
                .isEqualTo(19.5);
    }

    @ParameterizedTest
    @CsvSource({"1,1","2,1","3,1.5","4,2","5,2.5"})
    @DisplayName("같은 세로줄에 폰이 있으면 각 0.5점씩 계산되어야 한다.")
    void getTeamScore_Same_X_Pawn(int pawnCount, double expect) {
        // given
        Map<Position, Piece> squares = BoardFactory.createEmptyBoard();
        for (int i = 1; i <= pawnCount; i++) {
            squares.put(Position.of(0, i), Pawn.of(Team.WHITE));
        }
        chessGame.load(() -> new Board(squares), () -> WhiteTurnState.STATE);

        // when
        double whiteScore = chessGame.getTeamScore(Team.WHITE);

        // then
        assertThat(whiteScore)
                .isEqualTo(expect);
    }
}
