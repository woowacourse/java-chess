package chess.service;

import chess.dao.BoardDao;
import chess.dao.ChessGameDao;
import chess.domain.board.Position;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.dto.PieceDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessServiceTest {

    private final ChessService chessService = new ChessService(new ChessGameDao(), new BoardDao());

    @Test
    public void moveTest() {
        chessService.init();
        chessService.processMove(Position.of("a2"), Position.of("a4"));

        assertThat(findPiece(chessService.getBoardInformation(), Position.of("a4"))).isEqualTo("white_pawn");
    }

    @Test
    public void moveExceptionTest() {
        chessService.init();

        assertThatThrownBy(() -> {
            chessService.processMove(Position.of("a4"), Position.of("a6"));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    private String findPiece(List<PieceDto> boardInformation, Position a4) {
        for (PieceDto pieceDto : boardInformation) {
            if (pieceDto.getPosition().equals(a4.toString())) {
                return pieceDto.getName();
            }
        }
        return "";
    }


}
