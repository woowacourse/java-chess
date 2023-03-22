package chess.application;

import chess.domain.board.ChessBoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameRepository;
import chess.domain.piece.Color;
import chess.domain.piece.position.PiecePosition;
import chess.infrastructure.persistence.dao.ChessGameDao;
import chess.infrastructure.persistence.dao.PieceDao;
import chess.infrastructure.persistence.repository.JdbcChessGameRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("ChessGameService 은")
class ChessGameServiceTest {

    private final ChessGameDao chessGameDao = new ChessGameDao();
    private final PieceDao pieceDao = new PieceDao();
    private final ChessGameRepository chessGameRepository = new JdbcChessGameRepository(pieceDao, chessGameDao);
    private final ChessGameService chessGameService = new ChessGameService(chessGameRepository);

    @BeforeEach
    void setUp() {
        pieceDao.deleteAll();
        chessGameDao.deleteAll();
    }

    @AfterEach
    void clean() {
        pieceDao.deleteAll();
        chessGameDao.deleteAll();
    }

    @Test
    void 게임을_생성할_수_있다() {
        // given
        final ChessBoardFactory chessBoardFactory = new ChessBoardFactory();

        // when
        final Long id = chessGameService.create(chessBoardFactory);

        // then
        assertThat(chessGameRepository.findById(id)).isNotNull();
    }

    @Test
    void 기물을_움직일_수_있다() {
        // given
        final ChessBoardFactory chessBoardFactory = new ChessBoardFactory();
        final Long id = chessGameService.create(chessBoardFactory);
        final ChessGame chessGame = chessGameRepository.findById(id).get();

        // when
        흰색_왕을_죽인다(chessGame);
        final ChessGame result = chessGameRepository.findById(chessGame.id()).get();

        // then
        assertThat(result.winColor()).isEqualTo(Color.BLACK);
        assertThat(result.pieces().size()).isEqualTo(31);
    }

    private void 흰색_왕을_죽인다(final ChessGame chessGame) {
        chessGameService.movePiece(chessGame.id(), PiecePosition.of("e2"), PiecePosition.of("e4"));
        chessGameService.movePiece(chessGame.id(), PiecePosition.of("d7"), PiecePosition.of("d5"));
        chessGameService.movePiece(chessGame.id(), PiecePosition.of("e1"), PiecePosition.of("e2"));
        chessGameService.movePiece(chessGame.id(), PiecePosition.of("d5"), PiecePosition.of("d4"));
        chessGameService.movePiece(chessGame.id(), PiecePosition.of("e2"), PiecePosition.of("e3"));
        chessGameService.movePiece(chessGame.id(), PiecePosition.of("d4"), PiecePosition.of("e3"));
    }

    @Test
    void 조회_시_게임이_없다면_오류이다() {
        // when & then
        assertThatThrownBy(() -> chessGameService.findById(100L))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
