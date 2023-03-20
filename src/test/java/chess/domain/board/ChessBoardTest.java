package chess.domain.board;

import chess.domain.chess.CampType;
import chess.domain.chess.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import chess.domain.piece.move.piece.PawnMove;
import chess.domain.piece.move.piece.RookMove;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ChessBoardTest {

    @Test
    @DisplayName("체스판을 생성하고, 체스판 말의 수가 32개인지 확인한다.")
    void create() {
        // given
        final ChessBoard chessBoard = ChessBoard.getInstance(new ChessGame());

        // when
        final Map<Position, Piece> board = chessBoard.getBoard();

        // then
        assertThat(board.size())
                .isEqualTo(32);
    }

    @ParameterizedTest(name = "체스판의 해당 위치에 말이 존재하는지 확인한다.")
    @CsvSource(value = {"0:0:true", "3:5:false"}, delimiter = ':')
    void contains(final int rank, final int file, final boolean expected) {
        // given
        final ChessBoard chessBoard = ChessBoard.getInstance(new ChessGame());

        // when, then
        assertThat(chessBoard.contains(new Position(rank, file)))
                .isSameAs(expected);
    }

    @Test
    @DisplayName("특정 위치에 존재하는 체스말을 반환한다.")
    void checkPieceSuccess() {
        // given
        final ChessBoard chessBoard = ChessBoard.getInstance(new ChessGame());

        // when, then
        assertThat(chessBoard.checkPiece(new Position(0, 0)))
                .isEqualTo(new Piece(PieceType.ROOK, CampType.WHITE, new RookMove()));
    }

    @Test
    @DisplayName("없는 위치를 받으면, 예외가 발생한다.")
    void checkPieceFail() {
        // given
        final ChessBoard chessBoard = ChessBoard.getInstance(new ChessGame());

        // when, then
        assertThatThrownBy(() -> chessBoard.checkPiece(new Position(5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("체스말이 존재하는 위치를 입력해 주세요.");
    }

    @Test
    @DisplayName("입력받은 위치에 존재하는 체스말을 제거한다.")
    void removePiece() {
        // given
        final ChessBoard chessBoard = ChessBoard.getInstance(new ChessGame());
        final Position removePosition = new Position(1, 0);

        // when
        chessBoard.removePiece(removePosition);

        // then
        assertThat(chessBoard.contains(removePosition))
                .isFalse();
    }

    @Test
    @DisplayName("입력받은 위치에 체스말을 둔다.")
    void putPiece() {
        // given
        final ChessBoard chessBoard = ChessBoard.getInstance(new ChessGame());
        final Position putPosition = new Position(2, 0);
        final Piece piece = new Piece(PieceType.PAWN, CampType.WHITE, new PawnMove());

        // when
        chessBoard.putPiece(putPosition, piece);

        // then
        assertThat(chessBoard.contains(putPosition))
                .isTrue();
    }

    @ParameterizedTest(name = "특정 말의 종류에 따라서 시작 위치에서 종료 위치로 이동 가능한지 판단한다.")
    @CsvSource(value = {"1:3:false", "3:6:true"}, delimiter = ':')
    void isPossibleRoute(final int rank, final int file, final boolean expected) {
        // given
        final ChessBoard chessBoard = ChessBoard.getInstance(new ChessGame());
        final Position source = new Position(0, 3);
        final Piece piece = chessBoard.checkPiece(source);
        final Position target = new Position(rank, file);

        final Position obstacle = new Position(1, 4);
        chessBoard.removePiece(obstacle);

        // when
        boolean actual = chessBoard.isPossibleRoute(source, target, piece);

        // then
        assertThat(actual)
                .isSameAs(expected);
    }
}
