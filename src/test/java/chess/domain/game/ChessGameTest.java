package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.GameStatus;
import chess.dao.ChessGameDao;
import chess.dao.PiecesDao;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Side;
import chess.domain.piece.type.Piece;
import java.sql.Connection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("흰색 진영이 기물을 옮길 차례일 때, 검은색 진영의 기물을 옮기면 예외가 발생한다.")
    void checkTurnToMove_whiteTurn_throws() {
        // given
        final ChessGame chessGame = new ChessGame(new FakePiecesDao(), new FakeChessGameDao(), GameStatus.INIT);
        chessGame.startNewGame();
        final Position blackPiecePosition = new Position(File.A, Rank.SEVEN);
        final Position targetPosition = new Position(File.A, Rank.SIX);

        // when, then
        assertThatThrownBy(() -> chessGame.movePiece(blackPiecePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 상대방의 말은 이동시킬 수 없습니다.");
    }

    class FakePiecesDao implements PiecesDao {
        @Override
        public Connection getConnection() {
            return null;
        }

        @Override
        public LoadedPiecesDto findAll() {
            return null;
        }

        @Override
        public void insertAll(final List<Piece> pieces) {

        }

        @Override
        public void delete() {

        }
    }

    class FakeChessGameDao implements ChessGameDao {
        @Override
        public Connection getConnection() {
            return null;
        }

        @Override
        public Side selectTurn() {
            return null;
        }

        @Override
        public void updateTurn(final Side side) {

        }

        @Override
        public void delete() {

        }

        @Override
        public void insertTurn(final Side turn) {

        }
    }

}
