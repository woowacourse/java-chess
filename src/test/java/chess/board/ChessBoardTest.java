package chess.board;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import chess.location.Col;
import chess.location.Location;
import chess.location.Row;
import chess.piece.type.*;
import chess.score.Score;
import chess.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {
    @DisplayName("생성자 테스트")
    @Test
    void name() {
        ChessBoard chessBoard = ChessBoardCreater.create();
        assertThat(chessBoard).isInstanceOf(ChessBoard.class);
    }

    @DisplayName("팀에 따른 모든 말들을 가져온다.")
    @Test
    void giveMyPiece() {
        ChessBoard chessBoard = ChessBoardCreater.create();
        Map<Location, Piece> givenPieces = chessBoard.giveMyPiece(Team.BLACK);

        Map<Location, Piece> actual = new HashMap<>();
        putNoble(actual, 8);
        putPawns(actual, 7);

        assertThat(givenPieces).isEqualTo(actual);
    }

    private void putNoble(Map<Location, Piece> board, int row) {
        board.put(new Location(row, 'a'), new Rook(Team.BLACK));
        board.put(new Location(row, 'b'), new Knight(Team.BLACK));
        board.put(new Location(row, 'c'), new Bishop(Team.BLACK));
        board.put(new Location(row, 'd'), new Queen(Team.BLACK));
        board.put(new Location(row, 'e'), new King(Team.BLACK));
        board.put(new Location(row, 'f'), new Bishop(Team.BLACK));
        board.put(new Location(row, 'g'), new Knight(Team.BLACK));
        board.put(new Location(row, 'h'), new Rook(Team.BLACK));
    }

    private void putPawns(Map<Location, Piece> board, int row) {
        for (int i = 0; i < 8; i++) {
            board.put(new Location(row, (char) (i + 'a')), new Pawn(Team.BLACK));
        }
    }

    @DisplayName("팀 별로 감점해야하는 폰의 점수를 계산한다.")
    @Test
    void calculateReducePawnScore1() {
        ChessBoard chessBoard = ChessBoardCreater.create();
        assertThat(chessBoard.calculateReducePawnScore(Team.WHITE).getValue())
                .isEqualTo(new Score(0).getValue());
    }

    @DisplayName("두 개의 폰이 한 Col에 있을 경우")
    @Test
    void calculateReducePawnScore2() {
        ChessBoard chessBoard = ChessBoardCreater.create();
        Location now = new Location(Row.of(2), Col.of('a'));
        Location after = new Location(Row.of(3), Col.of('b'));
        chessBoard.move(now, after);
        assertThat(chessBoard.calculateReducePawnScore(Team.WHITE).getValue())
                .isEqualTo(new Score(1).getValue());
    }

    @DisplayName("세 개의 폰이 한 Col에 있을 경우")
    @Test
    void calculateReducePawnScore3() {
        ChessBoard chessBoard = ChessBoardCreater.create();
        Location now = new Location(Row.of(2), Col.of('a'));
        Location after = new Location(Row.of(3), Col.of('b'));

        Location now1 = new Location(Row.of(2), Col.of('c'));
        Location after1 = new Location(Row.of(4), Col.of('c'));

        Location now2 = new Location(Row.of(4), Col.of('c'));
        Location after2 = new Location(Row.of(5), Col.of('b'));

        chessBoard.move(now, after);
        chessBoard.move(now1, after1);
        chessBoard.move(now2, after2);

        assertThat(chessBoard.calculateReducePawnScore(Team.WHITE).getValue())
                .isEqualTo(new Score(1.5).getValue());
    }
}