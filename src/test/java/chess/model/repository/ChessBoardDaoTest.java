package chess.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.domain.board.BoardSquare;
import chess.model.domain.board.ChessGame;
import chess.model.domain.piece.Color;
import chess.model.domain.piece.Pawn;
import chess.model.domain.piece.Piece;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessBoardDaoTest {

    private static final ChessBoardDao CHESS_BOARD_DAO = ChessBoardDao.getInstance();
    private static final int GAME_ID = 1;
    private static final Map<BoardSquare, Boolean> CASTLING_ELEMENTS;

    static {
        Map<BoardSquare, Boolean> castlingElements = new ChessGame().getChessBoard().keySet()
            .stream()
            .collect(Collectors.toMap(boardSquare -> boardSquare, boardSquare -> false));
        castlingElements.put(BoardSquare.of("a1"), true);
        CASTLING_ELEMENTS = Collections.unmodifiableMap(castlingElements);
    }

    @BeforeEach
    void setup() {
        CHESS_BOARD_DAO.insert(GAME_ID, new ChessGame().getChessBoard(), CASTLING_ELEMENTS);
    }

    @AfterEach
    void tearDown() {
        CHESS_BOARD_DAO.delete(GAME_ID);
    }

    @Test
    void getInstance() {
        ChessBoardDao chessBoardDao1 = ChessBoardDao.getInstance();
        ChessBoardDao chessBoardDao2 = ChessBoardDao.getInstance();
        assertThat(chessBoardDao1 == chessBoardDao2).isTrue();
    }

    @Test
    void insert() {
        CHESS_BOARD_DAO.delete(GAME_ID);
        assertThat(CHESS_BOARD_DAO.getBoard(GAME_ID)).isEmpty();
        assertThat(CHESS_BOARD_DAO.getCastlingElements(GAME_ID)).isEmpty();
        assertThat(CHESS_BOARD_DAO.getEnpassantBoard(GAME_ID).getEnPassants()).isEmpty();

        CHESS_BOARD_DAO.insert(GAME_ID, new ChessGame().getChessBoard(), CASTLING_ELEMENTS);
        assertThat(CHESS_BOARD_DAO.getBoard(GAME_ID)).isEqualTo(new ChessGame().getChessBoard());
        assertThat(CHESS_BOARD_DAO.getCastlingElements(GAME_ID).size()).isEqualTo(1);
        assertThat(CHESS_BOARD_DAO.getEnpassantBoard(GAME_ID).getEnPassants()).isEmpty();
    }

    @Test
    void insertBoard() {
        BoardSquare boardSquareAfter = BoardSquare.of("a3");
        Piece pieceBefore = Pawn.getPieceInstance(Color.WHITE);
        CHESS_BOARD_DAO.insertBoard(GAME_ID, boardSquareAfter, pieceBefore);
    }

    @Test
    void deleteBoardSquare() {
        BoardSquare boardSquareBefore = BoardSquare.of("a2");
        CHESS_BOARD_DAO.deleteBoardSquare(GAME_ID, boardSquareBefore);
    }

    @Test
    void getCastlingElements() {
        assertThat(CHESS_BOARD_DAO.getCastlingElements(GAME_ID).size()).isEqualTo(1);

        CHESS_BOARD_DAO.delete(GAME_ID);
        assertThat(CHESS_BOARD_DAO.getCastlingElements(GAME_ID)).isEmpty();
    }

    @Test
    void getEnpassantBoard() {
        assertThat(CHESS_BOARD_DAO.getEnpassantBoard(GAME_ID).getEnPassants()).isEmpty();
    }

    @Test
    void getBoard() {
        assertThat(CHESS_BOARD_DAO.getBoard(GAME_ID)).isEqualTo(new ChessGame().getChessBoard());

        CHESS_BOARD_DAO.delete(GAME_ID);
        assertThat(CHESS_BOARD_DAO.getBoard(GAME_ID)).isEmpty();
    }

    @Test
    void delete() {
        CHESS_BOARD_DAO.delete(GAME_ID);
        assertThat(CHESS_BOARD_DAO.getBoard(GAME_ID)).isEmpty();
        assertThat(CHESS_BOARD_DAO.getCastlingElements(GAME_ID)).isEmpty();
        assertThat(CHESS_BOARD_DAO.getEnpassantBoard(GAME_ID).getEnPassants()).isEmpty();
    }

    @Test
    void copyBoardSquare() {
    }

    @Test
    void updatePromotion() {
    }

    @Test
    void deleteEnpassant() {
    }

    @Test
    void updateEnPassant() {
    }
}