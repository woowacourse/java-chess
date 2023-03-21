package chess.domain.board;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.fixture.CoordinateFixture.A_ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessBoardTest {
    private ChessBoard chessBoard;
    
    @BeforeEach
    void setUp() {
        chessBoard = ChessBoard.create();
    }
    
    @Test
    void 체스보드를_생성하면_8개의_행이_생성된다() {
        assertThat(chessBoard.chessBoard()).hasSize(8);
    }
    
    @Test
    void 경로에_기물이_있으면_이동_불가능하다() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessBoard.move(A_ONE, parseCoordinate("a3"), Team.BLACK))
                .withMessage("목적지까지의 경로에 기물이 있어서 목적지로 갈 수 없습니다. 다시 입력해주세요.");
    }
    
    @Test
    void 경로에_기물이_없으면_이동_가능하다() {
        chessBoard.move(parseCoordinate("a2"), parseCoordinate("a4"), Team.BLACK);
        Piece piece = chessBoard.chessBoard().get(4).pieces().get(0);
        assertThat(piece).isEqualTo(new Pawn(Team.BLACK, parseCoordinate("a4")));
    }
    
    @Test
    void 도착지에_상대팀_기물이_있으면_잡아먹는다() {
        chessBoard.move(parseCoordinate("a2"), parseCoordinate("a4"), Team.BLACK);
        chessBoard.move(A_ONE, parseCoordinate("a3"), Team.BLACK);
        chessBoard.move(parseCoordinate("a3"), parseCoordinate("b3"), Team.BLACK);
        chessBoard.move(parseCoordinate("b3"), parseCoordinate("b7"), Team.BLACK);
        Piece piece = chessBoard.chessBoard().get(1).pieces().get(1);
        assertThat(piece).isEqualTo(new Rook(Team.BLACK, parseCoordinate("b7")));
    }
    
    private static Coordinate parseCoordinate(String inputCoordinate) {
        return new Coordinate(inputCoordinate.split(""));
    }
}
