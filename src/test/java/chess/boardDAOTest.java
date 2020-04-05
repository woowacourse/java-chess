package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class boardDAOTest {
    private BoardDAO boardDAO;

    @BeforeEach
    public void setUp() {
        boardDAO = new BoardDAO(DBConnector.getConnection());
    }

    @DisplayName("위치를 나타내는 심볼로 피스를 나타내는 심볼을 가져올 수 있는지 테스트")
    @ParameterizedTest
    @CsvSource({"a1,r", "a2,p", "d8,Q", "h8,R"})
    void getPieceSymbolByPositionSymbolTest(String positionSymbol, String expectedPieceSymbol) throws SQLException {
        assertThat(boardDAO.getPieceSymbolByPositionSymbol(positionSymbol)).isEqualTo(expectedPieceSymbol);
    }

    @DisplayName("해당 위치에 피스가 존재하지 않을 경우 null을 반환하는지 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"a4", "b3", "d6", "h3"})
    void getPieceSymbolByPositionSymbolNullTest(String positionSymbol) throws SQLException {
        assertNull(boardDAO.getPieceSymbolByPositionSymbol(positionSymbol));
    }
}