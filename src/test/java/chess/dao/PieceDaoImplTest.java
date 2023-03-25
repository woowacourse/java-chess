package chess.dao;

import static chess.domain.board.File.A;
import static chess.domain.board.Rank.TWO;
import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.WHITE_PAWN;
import static chess.util.SquareFixture.A_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.dao.dto.ChessGameDto;
import chess.dao.dto.PieceDto;
import chess.domain.piece.Piece;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PieceDaoImplTest {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(new ConnectionManager());
    private ChessGameDto chessGameDto;

    @BeforeEach
    void setUp() {
        final String query = "insert into chess_game(turn, state) values(?, ?)";
        final List<String> parameters = List.of("WHITE", "INIT");
        jdbcTemplate.executeUpdate(query, parameters);

        final ChessGameDaoImpl chessGameDao = new ChessGameDaoImpl(jdbcTemplate);
        chessGameDto = chessGameDao.findAll().get(0);
    }

    @AfterEach
    void clear() {
        jdbcTemplate.executeUpdate("delete from piece", Collections.emptyList());
        jdbcTemplate.executeUpdate("delete from chess_game", Collections.emptyList());
    }

    @Test
    void 체스_기물을_저장한다() {
        final PieceDaoImpl pieceDao = new PieceDaoImpl(jdbcTemplate);
        pieceDao.save(chessGameDto.getId(), A_TWO, new Piece(WHITE, WHITE_PAWN));

        final List<PieceDto> pieces = pieceDao.findAllByChessGameId(chessGameDto.getId());
        assertAll(
                () -> assertThat(pieces).hasSize(1),
                () -> assertThat(pieces.get(0).getChessGameId()).isEqualTo(chessGameDto.getId()),
                () -> assertThat(pieces.get(0).getColor()).isEqualTo("WHITE"),
                () -> assertThat(pieces.get(0).getType()).isEqualTo("WHITE_PAWN"),
                () -> assertThat(pieces.get(0).getFile()).isEqualTo("A"),
                () -> assertThat(pieces.get(0).getRank()).isEqualTo("TWO")
        );
    }

    @Nested
    class findByFileAndRank_메서드는 {

        @Test
        void 파일과_랭크에_해당하는_기물이_존재하면_기물을_조회한다() {
            final PieceDaoImpl pieceDao = new PieceDaoImpl(jdbcTemplate);
            pieceDao.save(chessGameDto.getId(), A_TWO, new Piece(WHITE, WHITE_PAWN));

            final Optional<PieceDto> piece = pieceDao.findByFileAndRank(chessGameDto.getId(), A, TWO);

            assertThat(piece).isNotEmpty();
        }

        @Test
        void 파일과_랭크에_해당하는_기물이_존재하지_않으면_기물을_조회하지_않는다() {
            final PieceDaoImpl pieceDao = new PieceDaoImpl(jdbcTemplate);

            final Optional<PieceDto> piece = pieceDao.findByFileAndRank(chessGameDto.getId(), A, TWO);

            assertThat(piece).isEmpty();
        }
    }

    @Test
    void 기물의_색과_타입을_수정한다() {
        final PieceDaoImpl pieceDao = new PieceDaoImpl(jdbcTemplate);
        pieceDao.save(chessGameDto.getId(), A_TWO, new Piece(WHITE, WHITE_PAWN));
        final Optional<PieceDto> piece = pieceDao.findByFileAndRank(chessGameDto.getId(), A, TWO);

        pieceDao.update(piece.get().getId(), BLACK, KING);

        final Optional<PieceDto> updatePiece = pieceDao.findByFileAndRank(chessGameDto.getId(), A, TWO);
        assertAll(
                () -> assertThat(updatePiece).isNotEmpty(),
                () -> assertThat(updatePiece.get().getId()).isEqualTo(piece.get().getId()),
                () -> assertThat(updatePiece.get().getColor()).isEqualTo("BLACK"),
                () -> assertThat(updatePiece.get().getType()).isEqualTo("KING"),
                () -> assertThat(updatePiece.get().getFile()).isEqualTo("A"),
                () -> assertThat(updatePiece.get().getRank()).isEqualTo("TWO")
        );
    }
}
