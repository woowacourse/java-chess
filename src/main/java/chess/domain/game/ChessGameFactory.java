package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.dto.ChessGameDto;
import chess.dto.PieceDto;
import java.util.List;

public class ChessGameFactory {

    private ChessGameFactory() {
    }

    public static ChessGame generateChessGame(final ChessGameDto chessGameDto, final List<PieceDto> pieceDtos) {
        final int id = chessGameDto.getId();
        final Turn turn = chessGameDto.getTurn();
        final Board board = BoardFactory.generateBoard(pieceDtos);
        final ChessGame chessGame = new ChessGame(board, turn);
        chessGame.setId(id);
        return chessGame;
    }
}
