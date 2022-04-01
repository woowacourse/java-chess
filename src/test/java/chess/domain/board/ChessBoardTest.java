package chess.domain.board;

import static chess.domain.board.ChessBoard.SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE;
import static chess.domain.board.position.File.A;
import static chess.domain.board.position.File.B;
import static chess.domain.board.position.File.C;
import static chess.domain.board.position.File.E;
import static chess.domain.board.position.File.H;
import static chess.domain.board.position.Rank.EIGHT;
import static chess.domain.board.position.Rank.FIVE;
import static chess.domain.board.position.Rank.FOUR;
import static chess.domain.board.position.Rank.ONE;
import static chess.domain.board.position.Rank.SEVEN;
import static chess.domain.board.position.Rank.SIX;
import static chess.domain.board.position.Rank.THREE;
import static chess.domain.board.position.Rank.TWO;
import static chess.domain.piece.PieceTeam.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.factory.RegularBoardFactory;
import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Positions;
import chess.domain.board.factory.BoardFactory;
import chess.domain.board.position.Rank;
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

public class ChessBoardTest {

    private ChessBoard chessBoard;
    FixedGameFlow gameFlow;
    private final BoardFactory boardFactory = RegularBoardFactory.getInstance();

    @BeforeEach
    void setUp() {
        gameFlow = new FixedGameFlow();
        chessBoard = new ChessBoard(boardFactory.create(), gameFlow);
    }

    @Test
    @DisplayName("체스 보드 생성 테스트 : 개수")
    void init_count() {
        //given
        Map<Position, Piece> piecesByPositions = chessBoard.getBoard();

        //when
        int actual = piecesByPositions.keySet().size();

        //then
        assertThat(actual).isEqualTo(64);
    }

    @Test
    @DisplayName("체스 보드 생성 테스트 : 폰이 있는 행")
    void init_pawns_only() {
        //given
        Map<Position, Piece> piecesByPositions = chessBoard.getBoard();

        // when & then
        List<Piece> expected = Arrays.stream(File.values())
            .map(value -> new Pawn(WHITE))
            .collect(Collectors.toList());

        List<Piece> actual = Arrays.stream(File.values())
            .map(file -> piecesByPositions.get(Positions.findPositionBy(file, TWO)))
            .collect(Collectors.toList());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("체스 보드 생성 테스트 : 폰이 있는 행")
    void init_Except_Pawn() {
        //given
        Map<Position, Piece> piecesByPositions = chessBoard.getBoard();

        //when
        List<Piece> actual = Arrays.stream(File.values())
                .map(file -> piecesByPositions.get(Positions.findPositionBy(file, TWO)))
                .collect(Collectors.toList());

        List<Piece> expected = Arrays.stream(File.values())
            .map(value -> new Pawn(WHITE))
            .collect(Collectors.toList());

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("체스 말이 없는 곳에서 이동 시키면 예외를 던진다.")
    void move_exception() {
        assertThatThrownBy(() -> chessBoard.movePiece(Positions.findPositionBy(A, THREE), Positions.findPositionBy(B, THREE)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE);
    }

    @Test
    @DisplayName("체스 말이 입력한 target으로 정상 이동했는지 확인한다.")
    void move_test() {
        //when
        chessBoard.movePiece(Positions.findPositionBy(A, TWO), Positions.findPositionBy(A, THREE));
        Map<Position, Piece> piecesByPositions = chessBoard.getBoard();

        //then
        assertThat(piecesByPositions.get(Positions.findPositionBy(A, THREE))).isEqualTo(new Pawn(WHITE));
    }

    @ParameterizedTest
    @CsvSource(value = {"ONE:A", "THREE:C"}, delimiter = ':')
    @DisplayName("퀸은 경로에 다른 기물 있으면 이동할 수 없다")
    void isBlocked(Rank rank, File file) {
        assertThatThrownBy(() ->
            chessBoard.movePiece(Positions.findPositionBy(C, ONE), Positions.findPositionBy(file, rank))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"THREE:C", "THREE:A"}, delimiter = ':')
    @DisplayName("나이트는 경로에 다른 기물 있으면 이동할 수 있다")
    void isNonBlocked(Rank rank, File file) {
        assertDoesNotThrow(() ->
            chessBoard.movePiece(Positions.findPositionBy(B, ONE), Positions.findPositionBy(file, rank))
        );
    }

    @DisplayName("기물이 다른 기물의 이동경로를 막고 있다면 이동이 불가하다")
    @Test
    void isBlockedAfterNightMoved() {
        chessBoard.movePiece(Positions.findPositionBy(B, ONE), Positions.findPositionBy(C, THREE));
        assertThatThrownBy(() ->
            chessBoard.movePiece(Positions.findPositionBy(C, TWO), Positions.findPositionBy(C, FOUR))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동 하는 곳에 아군 기물이 있으면 이동이 불가능 하다")
    @Test
    void isMyTeam() {
        assertThatThrownBy(() ->
            chessBoard.movePiece(Positions.findPositionBy(A, ONE), Positions.findPositionBy(A, TWO))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰을 A2 에서 A4로 이동시켰다면 A4에는 폰이 있다")
    @Test
    void move_pawn_and_now_pawn_is_at_target_pos() {
        chessBoard.movePiece(Positions.findPositionBy(A, TWO), Positions.findPositionBy(A, FOUR));
        Piece findPiece = chessBoard.getBoard().get(Positions.findPositionBy(A, FOUR));
        assertThat(findPiece).isInstanceOf(Pawn.class);
    }

    @DisplayName("킹이 잡힐 경우 게임은 종료된다")
    @Test
    void when_king_captured_then_game_end() {
        AlternatingGameFlow gameFlow = new AlternatingGameFlow();
        chessBoard = new ChessBoard(boardFactory.create(), gameFlow);

        chessBoard.movePiece(Positions.findPositionBy(H, TWO), Positions.findPositionBy(H, FOUR)); // 흰 폰
        chessBoard.movePiece(Positions.findPositionBy(E, SEVEN), Positions.findPositionBy(E, FIVE)); // 검은 폰

        chessBoard.movePiece(Positions.findPositionBy(H, ONE), Positions.findPositionBy(H, THREE)); // 흰 룩
        chessBoard.movePiece(Positions.findPositionBy(E, FIVE), Positions.findPositionBy(E, FOUR)); // 검은 폰

        chessBoard.movePiece(Positions.findPositionBy(H, THREE), Positions.findPositionBy(E, THREE)); // 흰 룩
        chessBoard.movePiece(Positions.findPositionBy(E, EIGHT), Positions.findPositionBy(E, SEVEN)); // 검은 킹

        chessBoard.movePiece(Positions.findPositionBy(E, THREE), Positions.findPositionBy(E, FOUR)); // 흰 룩 : 게임 룰 상 이때 체크메이트 !
        chessBoard.movePiece(Positions.findPositionBy(E, SEVEN), Positions.findPositionBy(E, SIX)); // 검은 킹

        chessBoard.movePiece(Positions.findPositionBy(E, FOUR), Positions.findPositionBy(E, SIX)); // 흰 룩

        assertThat(chessBoard.isGamePlaying()).isFalse();
    }

    @Test
    @DisplayName("첫판에 점수를 계산하면 38점이 나온다")
    void when_first_turn_cal_score_then_38() {
        ChessBoard chessBoard = new ChessBoard(boardFactory.create(), new AlternatingGameFlow());
        double score = chessBoard.calculateScore();
        assertThat(score).isEqualTo(38.0);
    }

    @Test
    @DisplayName("폰이 같은 File에 두 개 이상 있을 경우 각 0.5점으로 계산한다.")
    void when_pawns_in_same_file() {
        ChessBoard chessBoard = new ChessBoard(boardFactory.create(), new AlternatingGameFlow());

        chessBoard.movePiece(Positions.findPositionBy(A, TWO), Positions.findPositionBy(A, FOUR));
        chessBoard.movePiece(Positions.findPositionBy(B, SEVEN), Positions.findPositionBy(B, FIVE));
        chessBoard.movePiece(Positions.findPositionBy(A, FOUR), Positions.findPositionBy(B, FIVE));

        //then
        double actual = chessBoard.calculateScore();
        assertThat(actual).isEqualTo(37.0);
    }
}
