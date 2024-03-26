package chess.db;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.color.Color;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.dto.PieceDto;
import chess.dto.TurnDto;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DBServiceTest {
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
        DBService dbService = DBFixtures.createDBService();
        piecesDao.create(new PieceDto(1, 1, "WHITE_ROOK"));

        assertThat(dbService.hasPreviousData()).isTrue();
    }

    @Test
    @DisplayName("기존에 저장된 데이터가 없는지 확인한다.")
    void hasNotPreviousData() {
        DBService dbService = DBFixtures.createDBService();

        assertThat(dbService.hasPreviousData()).isFalse();
    }

    @Test
    @DisplayName("실행 중인 게임을 저장한다.")
    void saveGame() {
        DBService dbService = DBFixtures.createDBService();
        List<PieceDto> pieces = List.of(
                PieceDto.from(new Position(1, 1), PieceType.WHITE_BISHOP),
                PieceDto.from(new Position(1, 2), PieceType.WHITE_FIRST_PAWN)
        );
        TurnDto turn = TurnDto.of(Color.WHITE);

        dbService.saveGame(pieces, turn);

        Assertions.assertAll(
                () -> assertThat(dbService.findPreviousPieces()).containsExactly(
                        PieceDto.from(new Position(1, 1), PieceType.WHITE_BISHOP),
                        PieceDto.from(new Position(1, 2), PieceType.WHITE_FIRST_PAWN)),
                () -> assertThat(dbService.findCurrentTurn()).isEqualTo(TurnDto.of(Color.WHITE))
        );
    }
}
