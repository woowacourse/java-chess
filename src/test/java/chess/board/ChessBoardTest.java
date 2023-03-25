package chess.board;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Coordinate;
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
        chessBoard.move(Coordinate.createCoordinate("1","a"), Coordinate.createCoordinate("3","a"));
        Piece piece = chessBoard.chessBoard().get(5).pieces().get(0);
        Assertions.assertThat(piece.isSameTeam(Team.EMPTY)).isTrue();
    }
    
    @Test
    void 경로에_기물이_없으면_이동_가능하다() {
        chessBoard.move(Coordinate.createCoordinate("2","a"), Coordinate.createCoordinate("4","a"));
        Piece piece = chessBoard.chessBoard().get(4).pieces().get(0);
        Assertions.assertThat(piece).isEqualTo(new Pawn(Team.BLACK, Coordinate.createCoordinate("4","a")));
    }
    
    @Test
    void 도착지에_상대팀_기물이_있으면_잡아먹는다() {
        chessBoard.move(Coordinate.createCoordinate("2","a"), Coordinate.createCoordinate("4","a"));
        chessBoard.move(Coordinate.createCoordinate("1","a"), Coordinate.createCoordinate("3","a"));
        chessBoard.move(Coordinate.createCoordinate("3","a"), Coordinate.createCoordinate("3","b"));
        chessBoard.move(Coordinate.createCoordinate("3","b"), Coordinate.createCoordinate("7","b"));
        Piece piece = chessBoard.chessBoard().get(1).pieces().get(1);
        Assertions.assertThat(piece).isEqualTo(new Rook(Team.BLACK, Coordinate.createCoordinate("7", "b")));
    }
}
