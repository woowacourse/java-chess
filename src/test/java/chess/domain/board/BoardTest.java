package chess.domain.board;

import static chess.domain.board.Board.SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE;
import static chess.domain.board.File.A;
import static chess.domain.board.File.B;
import static chess.domain.board.File.C;
import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.File.F;
import static chess.domain.board.File.G;
import static chess.domain.board.File.H;
import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.FIVE;
import static chess.domain.board.Rank.FOUR;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.SIX;
import static chess.domain.board.Rank.THREE;
import static chess.domain.board.Rank.TWO;
import static chess.domain.piece.constant.PieceColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.utils.BoardFactory;
import chess.domain.board.utils.ProductionBoardFactory;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.turndecider.AlternatingGameFlow;
import chess.turndecider.FixedGameFlow;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BoardTest {

    private Board board;
    FixedGameFlow gameFlow;
    private final BoardFactory boardFactory = ProductionBoardFactory.getInstance();

    @BeforeEach
    void setUp() {
        gameFlow = new FixedGameFlow();
        board = new Board(boardFactory.create(), gameFlow);
    }

    @Test
    @DisplayName("체스 보드 생성 테스트 : 개수")
    void init_count() {
        //given
        Map<Position, Piece> piecesByPositions = board.getBoard();

        //when
        int actual = piecesByPositions.keySet().size();

        //then
        assertThat(actual).isEqualTo(64);
    }

    @Test
    @DisplayName("체스 보드 생성 테스트 : 폰이 있는 행")
    void init_pawns_only() {
        //given
        Map<Position, Piece> piecesByPositions = board.getBoard();

        // when & then
        List<Piece> expected = Arrays.stream(File.values())
            .map(value -> new Pawn(WHITE))
            .collect(Collectors.toList());

        List<Piece> actual = Arrays.stream(File.values())
            .map(file -> piecesByPositions.get(new Position(file, TWO)))
            .collect(Collectors.toList());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("체스 보드 생성 테스트 : 폰이 있는 행")
    void init_Except_Pawn() {
        //given
        Map<Position, Piece> piecesByPositions = board.getBoard();

        //when
        List<Piece> expected = Arrays.stream(File.values())
            .map(value -> new Pawn(WHITE))
            .collect(Collectors.toList());

        List<Piece> actual = Arrays.stream(File.values())
            .map(file -> piecesByPositions.get(new Position(file, TWO)))
            .collect(Collectors.toList());

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("체스 말이 없는 곳에서 이동 시키면 예외를 던진다.")
    void move_exception() {
        assertThatThrownBy(() -> board.move(new Position(A, THREE), new Position(B, THREE)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE);
    }

    @Test
    @DisplayName("체스 말이 입력한 target으로 정상 이동했는지 확인한다.")
    void move_test() {
        //when
        board.move(new Position(A, TWO), new Position(A, THREE));
        Map<Position, Piece> piecesByPositions = board.getBoard();

        //then
        assertThat(piecesByPositions.get(new Position(A, THREE))).isEqualTo(new Pawn(WHITE));
    }

    @ParameterizedTest
    @CsvSource(value = {"ONE:A", "THREE:C"}, delimiter = ':')
    @DisplayName("퀸은 경로에 다른 기물 있으면 이동할 수 없다")
    void isBlocked(Rank rank, File file) {
        assertThatThrownBy(() ->
            board.move(new Position(C, ONE), new Position(file, rank))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"THREE:C", "THREE:A"}, delimiter = ':')
    @DisplayName("나이트는 경로에 다른 기물 있으면 이동할 수 있다")
    void isNonBlocked(Rank rank, File file) {
        assertDoesNotThrow(() ->
            board.move(new Position(B, ONE), new Position(file, rank))
        );
    }

    @DisplayName("기물이 다른 기물의 이동경로를 막고 있다면 이동이 불가하다")
    @Test
    void isBlockedAfterNightMoved() {
        board.move(new Position(B, ONE), new Position(C, THREE));
        assertThatThrownBy(() ->
            board.move(new Position(C, TWO), new Position(C, FOUR))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동 하는 곳에 아군 기물이 있으면 이동이 불가능 하다")
    @Test
    void isMyTeam() {
        assertThatThrownBy(() ->
            board.move(new Position(A, ONE), new Position(A, TWO))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰을 A2 에서 A4로 이동시켰다면 A4에는 폰이 있다")
    @Test
    void move_pawn_and_now_pawn_is_at_target_pos() {
        board.move(new Position(A, TWO), new Position(A, FOUR));
        Piece findPiece = board.getBoard().get(new Position(A, FOUR));
        assertThat(findPiece).isInstanceOf(Pawn.class);
    }

    @DisplayName("킹이 잡힐 경우 게임은 종료된다")
    @Test
    void when_king_captured_then_game_end() {
        AlternatingGameFlow gameFlow = new AlternatingGameFlow();
        board = new Board(boardFactory.create(), gameFlow);

        board.move(new Position(H, TWO), new Position(H, FOUR)); // 흰 폰
        board.move(new Position(E, SEVEN), new Position(E, FIVE)); // 검은 폰

        board.move(new Position(H, ONE), new Position(H, THREE)); // 흰 룩
        board.move(new Position(E, FIVE), new Position(E, FOUR)); // 검은 폰

        board.move(new Position(H, THREE), new Position(E, THREE)); // 흰 룩
        board.move(new Position(E, EIGHT), new Position(E, SEVEN)); // 검은 킹

        board.move(new Position(E, THREE), new Position(E, FOUR)); // 흰 룩 : 게임 룰 상 이때 체크메이트 !
        board.move(new Position(E, SEVEN), new Position(E, SIX)); // 검은 킹

        board.move(new Position(E, FOUR), new Position(E, SIX)); // 흰 룩

        assertThat(gameFlow.isRunning()).isFalse();
    }

    @Test
    @DisplayName("첫판에 점수를 계산하면 38점이 나온다")
    void when_first_turn_cal_score_then_38() {
        Board board = new Board(boardFactory.create(), new AlternatingGameFlow());
        double score = board.calculateScore();
        assertThat(score).isEqualTo(38.0);
    }

    @Test
    @DisplayName("폰이 같은 File에 두 개 이상 있을 경우 각 0.5점으로 계산한다.")
    void when_pawns_in_same_file() {
        Board board = new Board(boardFactory.create(), new AlternatingGameFlow());

        board.move(new Position(A, TWO), new Position(A, FOUR));
        board.move(new Position(B, SEVEN), new Position(B, FIVE));
        board.move(new Position(A, FOUR), new Position(B, FIVE));

        //then
        double actual = board.calculateScore();
        assertThat(actual).isEqualTo(37.0);
    }
}
