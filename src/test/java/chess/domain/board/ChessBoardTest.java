package chess.domain.board;

import chess.domain.chess.CampType;
import chess.domain.chess.ChessGame;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Rook;
import chess.domain.piece.move.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessBoardTest {

    @Test
    @DisplayName("체스판을 생성하고, 체스판 말의 수가 32개인지 확인한다.")
    void create() {
        // given
        final ChessBoard chessBoard = ChessBoard.getInstance(new ChessGame(CampType.WHITE));

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
        final ChessBoard chessBoard = ChessBoard.getInstance(new ChessGame(CampType.WHITE));

        // when, then
        assertThat(chessBoard.contains(new Position(rank, file)))
                .isSameAs(expected);
    }

    @Test
    @DisplayName("특정 위치에 존재하는 체스말을 반환한다.")
    void getPiece() {
        // given
        final ChessBoard chessBoard = ChessBoard.getInstance(new ChessGame(CampType.WHITE));

        // when, then
        assertThat(chessBoard.getPiece(new Position(0, 0)))
                .isEqualTo(new Piece(PieceType.ROOK, CampType.WHITE, new Rook()));
    }

    @Test
    @DisplayName("입력받은 위치에 존재하는 체스말을 제거한다.")
    void removePiece() {
        // given
        final ChessBoard chessBoard = ChessBoard.getInstance(new ChessGame(CampType.WHITE));
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
        final ChessBoard chessBoard = ChessBoard.getInstance(new ChessGame(CampType.WHITE));
        final Position putPosition = new Position(2, 0);
        final Piece piece = new Piece(PieceType.PAWN, CampType.WHITE, new Pawn());

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
        final ChessBoard chessBoard = ChessBoard.getInstance(new ChessGame(CampType.WHITE));
        final Position source = new Position(0, 3);
        final Position target = new Position(rank, file);

        final Position obstacle = new Position(1, 4);
        chessBoard.removePiece(obstacle);

        // when
        boolean actual = chessBoard.isPossibleRoute(source, target);

        // then
        assertThat(actual)
                .isSameAs(expected);
    }

    @Test
    @DisplayName("현재 체스판에서 살아있는 킹들을 조회한다.")
    void getAliveKings() {
        // given
        final ChessBoard chessBoard = ChessBoard.getInstance(new ChessGame(CampType.WHITE));

        // when
        List<Piece> aliveKings = chessBoard.getAliveKings();
        List<Piece> expected = List.of(new Piece(PieceType.KING, CampType.WHITE, new King()),
                new Piece(PieceType.KING, CampType.BLACK, new King()));

        // then
        assertThat(aliveKings)
                .isEqualTo(expected);

        assertThat(aliveKings.size())
                .isSameAs(2);
    }

    @Test
    @DisplayName("입력받은 진영의 체스판을 반환한다.")
    void getBoardByCamp() {
        // given
        final ChessBoard chessBoard = ChessBoard.getInstance(new ChessGame(CampType.WHITE));

        // when
        final Map<Position, Piece> whiteBoard = chessBoard.getBoardByCamp(CampType.WHITE);
        final Map<Position, Piece> blackBoard = chessBoard.getBoardByCamp(CampType.BLACK);

        // then
        assertThat(whiteBoard.size())
                .isEqualTo(16);
        assertThat(blackBoard.size())
                .isEqualTo(16);
    }
}
