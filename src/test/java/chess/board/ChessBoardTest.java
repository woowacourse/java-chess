package chess.board;

import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Rook;
import chess.piece.Team;
import chess.piece.coordinate.Coordinate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

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
        Assertions.assertThat(chessBoard.chessBoard()).hasSize(8);
    }
    
    @Test
    void 경로에_기물이_있으면_이동_불가능하다() {
        chessBoard.move("a1", "a3");
        Piece piece = chessBoard.chessBoard().get(5).pieces().get(0);
        Assertions.assertThat(piece.isSameTeam(Team.EMPTY)).isTrue();
    }
    
    @Test
    void 경로에_기물이_없으면_이동_가능하다() {
        chessBoard.move("a2", "a4");
        Piece piece = chessBoard.chessBoard().get(4).pieces().get(0);
        Assertions.assertThat(piece).isEqualTo(new Pawn(Team.BLACK, Coordinate.createCoordinate(4, 'a')));
    }
    
    @Test
    void 도착지에_상대팀_기물이_있으면_잡아먹는다() {
        chessBoard.move("a2", "a4");
        chessBoard.move("a1", "a3");
        chessBoard.move("a3", "b3");
        chessBoard.move("b3", "b7");
        Piece piece = chessBoard.chessBoard().get(1).pieces().get(1);
        Assertions.assertThat(piece).isEqualTo(new Rook(Team.BLACK, Coordinate.createCoordinate(7, 'b')));
    }
}
