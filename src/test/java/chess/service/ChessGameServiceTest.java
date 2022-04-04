package chess.service;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.dao.PieceDao;
import chess.dao.PieceDaoImpl;
import chess.dao.TurnDao;
import chess.dao.TurnDaoImpl;
import chess.domain.Position;
import chess.domain.PromotionPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.single.Knight;
import chess.domain.turn.Turn;
import chess.testutil.H2Connection;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameServiceTest {

    private PieceDao pieceDao;
    private TurnDao turnDao;
    private ChessGameService chessGameService;

    @BeforeEach
    void setUp() {
        H2Connection.setUpTable();
        pieceDao = new PieceDaoImpl(H2Connection.getConnection());
        turnDao = new TurnDaoImpl(H2Connection.getConnection());
        chessGameService = new ChessGameService(pieceDao, turnDao);
    }

    @Test
    @DisplayName("Position을 받아 상대 기물이 있는 곳에 move")
    void moveTargetPosition() {
        // given
        turnDao.updateTurn(Turn.END, Turn.WHITE_TURN);
        Position source = Position.of('a', '1');
        Position target = Position.of('b', '2');
        pieceDao.savePieces(Map.of(
                source, new Piece(WHITE, new Pawn(WHITE)),
                target, new Piece(BLACK, new Knight())
        ));

        // when
        chessGameService.move(source, target);
        Piece result = pieceDao.findAllPieces().get(target);

        // then
        assertAll(
                () ->assertThat(result.name()).isEqualTo("pawn"),
                () -> assertThat(result.color()).isEqualTo(WHITE)
        );
    }

    @Test
    @DisplayName("Position을 받아 빈 곳에 move")
    void moveEmptyPosition() {
        // given
        turnDao.updateTurn(Turn.END, Turn.WHITE_TURN);
        Position source = Position.of('a', '1');
        Position target = Position.of('a', '2');
        pieceDao.savePieces(Map.of(
                source, new Piece(WHITE, new Pawn(WHITE))
        ));

        // when
        chessGameService.move(source, target);
        Piece result = pieceDao.findAllPieces().get(target);

        // then
        assertAll(
                () ->assertThat(result.name()).isEqualTo("pawn"),
                () -> assertThat(result.color()).isEqualTo(WHITE)
        );
    }

    @Test
    @DisplayName("pawn 프로모션")
    void promotion() {
        // given
        turnDao.updateTurn(Turn.END, Turn.WHITE_TURN);
        Position source = Position.of('a', '8');
        pieceDao.savePieces(Map.of(
                source, new Piece(WHITE, new Pawn(WHITE))
        ));

        // when
        chessGameService.promotion(PromotionPiece.BISHOP);
        Piece result = pieceDao.findAllPieces().get(source);

        // then
        assertAll(
                () ->assertThat(result.name()).isEqualTo("bishop"),
                () -> assertThat(result.color()).isEqualTo(WHITE)
        );
    }
}
