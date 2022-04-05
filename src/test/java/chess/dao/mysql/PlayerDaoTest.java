package chess.dao.mysql;

import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.dao.dto.PlayerDto;

class PlayerDaoTest {

    private MysqlConnector mysqlConnector;

    @BeforeEach
    void setUp() {
        mysqlConnector = new MysqlConnector();
    }

    @DisplayName("데이터 저장 및 조회가 가능해야 한다.")
    @Test
    void saveAndFind() {
        final String colorName = WHITE.getName();
        final String pieces = "a1:Rook,a2:Pawn";

        final PlayerDao playerDao = new PlayerDao(mysqlConnector);
        final Long id = playerDao.save(new PlayerDto(0L, colorName, pieces));

        final PlayerDto playerDto = playerDao.findById(id);
        assertAll(() -> {
                    assertThat(playerDto.getColorName()).isEqualTo(colorName);
                    assertThat(playerDto.getPieces()).isEqualTo(pieces);
                });
    }
}