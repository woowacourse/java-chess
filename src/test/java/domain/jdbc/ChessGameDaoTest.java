package domain.jdbc;

import domain.ChessGame;
import domain.chessboard.ChessBoard;
import domain.chessboard.SquareStatus;
import domain.coordinate.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessGameDaoTest {

    private static final String CHESS_GAME_ID = Integer.toString(Integer.MAX_VALUE);
    private final ChessGameDao chessGameDao = new ChessGameDao();

    @Test
    @DisplayName("Connection 을 확인한다.")
    void checkConnection() {
        try (final Connection connection = chessGameDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    @DisplayName("새로운 Chess Game 방을 만든다.")
    void saveChessGame() {
        chessGameDao.save(new ChessGame(ChessBoard.generate()));
    }

    @Test
    @DisplayName("생성한 Chess Game 의 정보를 가져온다.")
    void selectNewGame() {
        ChessGame insertionChessGame = new ChessGame(ChessBoard.generate());
        ChessGame chessGame = chessGameDao.selectNewGame(insertionChessGame);

        boolean allMatch = chessGameEquals(insertionChessGame, chessGame);

        assertThat(insertionChessGame.getColorTurn()).isEqualTo(chessGame.getColorTurn());
        assertThat(allMatch).isTrue();
    }

    private boolean chessGameEquals(ChessGame insertionChessGame, ChessGame chessGame) {
        boolean allMatch = true;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Position findPosition = Position.of(x, y);
                SquareStatus insertionSquareStatus = insertionChessGame.getChessBoard()
                        .findSquare(findPosition).getSquareStatus();
                SquareStatus squareStatus = chessGame.getChessBoard()
                        .findSquare(findPosition).getSquareStatus();

                if (insertionSquareStatus.getColor() != squareStatus.getColor()
                        || insertionSquareStatus.getType() != squareStatus.getType()) {
                    allMatch = false;
                }
            }
        }

        return allMatch;
    }

}
