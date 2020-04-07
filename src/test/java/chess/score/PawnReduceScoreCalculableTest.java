package chess.score;

import chess.board.ChessBoard;
import chess.board.ChessBoardCreater;
import chess.game.ChessSet;
import chess.location.Col;
import chess.location.Location;
import chess.location.Row;
import chess.piece.type.Piece;
import chess.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PawnReduceScoreCalculableTest {
    @DisplayName("팀 별로 감점해야하는 폰의 점수를 계산한다.")
    @Test
    void calculateReducePawnScore1() {
        ChessBoard chessBoard = ChessBoardCreater.create();
        Map<Location, Piece> chessBoardPieces = chessBoard.giveMyPiece(Team.WHITE);
        PawnReduceScoreCalculable pawnReduceScoreCalculable = new PawnReduceScoreCalculable();
        ChessSet chessSet = new ChessSet(chessBoardPieces);
        assertThat(pawnReduceScoreCalculable.calculate(chessSet))
                .isEqualTo(new Score(0));
    }

    @DisplayName("두 개의 폰이 한 Col에 있을 경우")
    @Test
    void calculateReducePawnScore2() {
        ChessBoard chessBoard = ChessBoardCreater.create();

        Location now = new Location(Row.of(2), Col.of('a'));
        Location after = new Location(Row.of(3), Col.of('b'));
        chessBoard.move(now, after);

        ChessSet chessSet = new ChessSet(chessBoard.giveMyPiece(Team.WHITE));
        PawnReduceScoreCalculable pawnReduceScoreCalculable = new PawnReduceScoreCalculable();

        assertThat(pawnReduceScoreCalculable.calculate(chessSet).getValue())
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

        ChessSet chessSet = new ChessSet(chessBoard.giveMyPiece(Team.WHITE));
        PawnReduceScoreCalculable pawnReduceScoreCalculable = new PawnReduceScoreCalculable();

        assertThat(pawnReduceScoreCalculable.calculate(chessSet))
                .isEqualTo(new Score(1.5));
    }
}