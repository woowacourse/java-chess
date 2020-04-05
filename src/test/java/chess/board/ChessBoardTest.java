package chess.board;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.game.ChessSet;
import chess.location.Col;
import chess.location.Location;
import chess.location.Row;
import chess.piece.type.*;
import chess.player.Player;
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
        List<Piece> givenPieces = chessBoard.giveMyPiece(Team.BLACK);

        List<Piece> actual = new ArrayList<>();
        putNoble(actual);
        putPawns(actual);

        for (Piece piece : actual) {
            assertThat(givenPieces).contains(piece);
        }
        assertThat(givenPieces).hasSameSizeAs(actual);
    }

    private void putNoble(List<Piece> board) {
        board.add(new Rook(Team.BLACK));
        board.add(new Knight(Team.BLACK));
        board.add(new Bishop(Team.BLACK));
        board.add(new Queen(Team.BLACK));
        board.add(new King(Team.BLACK));
        board.add(new Bishop(Team.BLACK));
        board.add(new Knight(Team.BLACK));
        board.add(new Rook(Team.BLACK));
    }

    private void putPawns(List<Piece> board) {
        for (int i = 0; i < 8; i++) {
            board.add(new Pawn(Team.BLACK));
        }
    }

    @DisplayName("팀 별로 감점해야하는 폰의 점수를 계산한다.")
    @Test
    void calculateReducePawnScore1() {
        ChessBoard chessBoard = ChessBoardCreater.create();
        Player player = new Player(new ChessSet(chessBoard.giveMyPiece(Team.WHITE)), Team.WHITE);
        assertThat(chessBoard.calculateReducePawnScore(player).getValue())
                .isEqualTo(new Score(0).getValue());
    }

    @DisplayName("두 개의 폰이 한 Col에 있을 경우")
    @Test
    void calculateReducePawnScore2() {
        ChessBoard chessBoard = ChessBoardCreater.create();
        Player player = new Player(new ChessSet(chessBoard.giveMyPiece(Team.WHITE)), Team.WHITE);

        Location now = new Location(Row.of(2), Col.of('a'));
        Location after = new Location(Row.of(3), Col.of('b'));
        chessBoard.move(now, after);
        assertThat(chessBoard.calculateReducePawnScore(player).getValue())
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

        Player player = new Player(new ChessSet(chessBoard.giveMyPiece(Team.WHITE)), Team.WHITE);

        assertThat(chessBoard.calculateReducePawnScore(player).getValue())
                .isEqualTo(new Score(1.5).getValue());
    }
}