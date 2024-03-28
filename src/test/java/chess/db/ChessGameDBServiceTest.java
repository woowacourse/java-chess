package chess.db;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessGame;
import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.nonsliding.King;
import chess.domain.position.Position;
import chess.dto.PieceDto;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDBServiceTest {
    private PiecesDao piecesDao;

    @BeforeEach
    void setUp() {
        piecesDao = DBFixtures.createPiecesDao();
    }

    @AfterEach
    void tearDown() {
        piecesDao.deleteAll();
    }

    @Test
    @DisplayName("기존에 저장된 데이터가 있는지 확인한다.")
    void hasPreciousData() {
        ChessGameDBService chessGameDbService = DBFixtures.createChessGameDBService();
        piecesDao.create(new PieceDto(1, 1, "WHITE_ROOK"));

        assertThat(chessGameDbService.hasPreviousData()).isTrue();
    }

    @Test
    @DisplayName("기존에 저장된 데이터가 없는지 확인한다.")
    void hasNotPreviousData() {
        ChessGameDBService chessGameDbService = DBFixtures.createChessGameDBService();

        assertThat(chessGameDbService.hasPreviousData()).isFalse();
    }

    @Test
    @DisplayName("저장된 체스 게임을 가져온다.")
    void getCurrentChessGame() {
        Map<Position, Piece> board = Map.of(
                new Position(1, 1), new King(Color.WHITE),
                new Position(1, 8), new King(Color.BLACK)
        );
        ChessGame chessGame = new ChessGame(board, Color.WHITE);

        ChessGameDBService chessGameDbService = DBFixtures.createChessGameDBService();
        chessGameDbService.saveGame(chessGame);

        Assertions.assertAll(
                () -> assertThat(chessGameDbService.getCurrentChessGame().collectBoard()).containsAllEntriesOf(Map.of(
                        new Position(1, 1), PieceType.WHITE_KING,
                        new Position(1, 8), PieceType.BLACK_KING
                )),
                () -> assertThat(chessGameDbService.getCurrentChessGame().turn()).isEqualTo(Color.WHITE)

        );
    }
}
