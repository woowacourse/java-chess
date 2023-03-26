package chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
        chessBoard.move(Coordinate.createCoordinate("1","a"), Coordinate.createCoordinate("3","a"));
        Piece piece = chessBoard.chessBoard().get(5).pieces().get(0);
        assertThat(piece.isSameTeam(Team.EMPTY)).isTrue();
    }
    
    @Test
    void 경로에_기물이_없으면_이동_가능하다() {
        chessBoard.move(Coordinate.createCoordinate("2","a"), Coordinate.createCoordinate("4","a"));
        Piece piece = chessBoard.chessBoard().get(4).pieces().get(0);
        assertThat(piece).isEqualTo(new Pawn(Team.BLACK, Coordinate.createCoordinate("4","a")));
    }
    
    @Test
    void 도착지에_상대팀_기물이_있으면_잡아먹는다() {
        chessBoard.move(Coordinate.createCoordinate("2","a"), Coordinate.createCoordinate("4","a"));
        chessBoard.move(Coordinate.createCoordinate("1","a"), Coordinate.createCoordinate("3","a"));
        chessBoard.move(Coordinate.createCoordinate("3","a"), Coordinate.createCoordinate("3","b"));
        chessBoard.move(Coordinate.createCoordinate("3","b"), Coordinate.createCoordinate("7","b"));
        Piece piece = chessBoard.chessBoard().get(1).pieces().get(1);
        assertThat(piece).isEqualTo(new Rook(Team.BLACK, Coordinate.createCoordinate("7", "b")));
    }

    @ParameterizedTest
    @CsvSource(value = {"BLACK,true","WHITE,false"})
    void 특정_팀의_왕이_살아있는지_확인(Team team, boolean expected){
        ChessBoard testChessBoard = ChessBoard.create();
        testChessBoard.move(Coordinate.createCoordinate("2","d"),Coordinate.createCoordinate("4","d"));
        testChessBoard.move(Coordinate.createCoordinate("1","d"),Coordinate.createCoordinate("3","d"));
        testChessBoard.move(Coordinate.createCoordinate("3","d"),Coordinate.createCoordinate("3","e"));
        testChessBoard.move(Coordinate.createCoordinate("3","e"),Coordinate.createCoordinate("7","e"));
        testChessBoard.move(Coordinate.createCoordinate("7","e"),Coordinate.createCoordinate("8","e"));
        assertThat(testChessBoard.isKingAlive(team)).isEqualTo(expected);

    }

    @ParameterizedTest
    @CsvSource(value = {"BLACK,38","WHITE,37"})
    void 특정_팀의_점수_계산(Team team, double expected){
        ChessBoard testChessBoard = ChessBoard.create();
        testChessBoard.move(Coordinate.createCoordinate("2","d"),Coordinate.createCoordinate("4","d"));
        testChessBoard.move(Coordinate.createCoordinate("1","d"),Coordinate.createCoordinate("3","d"));
        testChessBoard.move(Coordinate.createCoordinate("3","d"),Coordinate.createCoordinate("3","e"));
        testChessBoard.move(Coordinate.createCoordinate("3","e"),Coordinate.createCoordinate("7","e"));
        assertThat(testChessBoard.calculateFinalPointsByTeam(team)).isEqualTo(expected);
    }


}
