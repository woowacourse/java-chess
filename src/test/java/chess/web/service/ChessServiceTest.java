package chess.web.service;

import chess.board.Board;
import chess.board.piece.Piece;
import chess.board.piece.Pieces;
import chess.board.piece.position.Position;
import chess.web.dao.BoardDao;
import chess.web.service.dto.MoveDto;
import chess.web.service.dto.ScoreDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ChessServiceTest {

    private final BoardDao boardDao = new MockBoardDao();
    private final ChessService chessService = new ChessService(boardDao, new MockPieceDao());
    private final Long boardId = 1L;


    @Test
    @DisplayName("64개의 piece를 갖고 있는 게임을 불러왔을 떄, 그떄의 piece 개수도 64개여야한다.")
    void loadGame() {
        Board board = chessService.loadGame(boardId);
        assertThat(board.getPieces().getPieces().size()).isEqualTo(64);
    }

    @Test
    @DisplayName("초기 보드판에서 a2->a3로 이동하면 처음 a2에 있던 piece는 이동 후, a3에 있는 piece와 같다.")
    void move() {
        Board board = boardDao.findById(boardId).get();
        String from = "a2";
        String to = "a3";
        Piece piece = board.getPieces().findByPosition(Position.from(from));
        MoveDto moveDto = new MoveDto(from, to);
        Board movedBoard = chessService.move(moveDto, boardId);
        Piece movedPiece = movedBoard.getPieces().findByPosition(Position.from(to));
        assertAll(
                () -> assertThat(piece.getType()).isEqualTo(movedPiece.getType()),
                () -> assertThat(piece.getName()).isEqualTo(movedPiece.getName()),
                () -> assertThat(piece.getTeam()).isEqualTo(movedPiece.getTeam())
        );

    }

    @Test
    @DisplayName("piece들이 없는 board를 만든 후, initBoard호출하면 piece들까지 초기화된다.")
    void initBoard() {
        Long saveId = boardDao.save();

        Board initBoard = chessService.initBoard(saveId);
        Pieces pieces = initBoard.getPieces();
        assertThat(pieces.getPieces().size()).isEqualTo(64);
    }

    @Test
    @DisplayName("초기 board들의 점수는 black, white팀 모두 38이다.")
    void getStatus() {
        ScoreDto status = chessService.getStatus(boardId);
        assertThat(status.getBlackTeamScore()).isEqualTo(38D);
        assertThat(status.getWhiteTeamScore()).isEqualTo(38D);
    }
}
