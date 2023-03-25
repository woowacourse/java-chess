package domain.jdbc;

import domain.ChessGame;
import domain.chessboard.ChessBoard;
import domain.chessboard.SquareStatus;
import domain.coordinate.MovePosition;
import domain.coordinate.Position;
import domain.coordinate.PositionFactory;
import domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.OutputView;

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

        boolean equals = chessBoardEquals(insertionChessGame, chessGame);

        assertThat(insertionChessGame.getColorTurn()).isEqualTo(chessGame.getColorTurn());
        assertThat(equals).isTrue();
    }

    @Test
    @DisplayName("이미 있는 Chess Game 의 정보를 가져온다.")
    void selectGame() {
        // Given
        ChessGame insertionChessGame = new ChessGame(ChessBoard.generate());
        MovePosition movePosition = MovePosition.of(PositionFactory.createPosition("a2"), PositionFactory.createPosition("a4"));
        insertionChessGame.move(movePosition);
        String saveId = chessGameDao.save(insertionChessGame);

        // When
        ChessGame chessGame = chessGameDao.select(saveId);
        boolean equals = chessBoardEquals(insertionChessGame, chessGame);

        // Then
        assertThat(chessGame.getColorTurn()).isEqualTo(Color.BLACK);
        assertThat(equals).isTrue();
    }

    private boolean chessBoardEquals(ChessGame insertionChessGame, ChessGame chessGame) {
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
