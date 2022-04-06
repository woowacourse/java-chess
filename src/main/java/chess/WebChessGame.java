package chess;

import chess.dao.BoardDao;
import chess.dao.MysqlConnector;
import chess.dao.PieceDao;
import chess.domain.BoardInitializer;
import chess.domain.command.Command;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.dto.ResponseDto;
import chess.dto.board.BoardDto;
import chess.dto.board.PieceDto;
import chess.dto.board.PositionDto;
import java.sql.SQLException;
import java.util.List;

public class WebChessGame {
    private State state;

    public WebChessGame(final State state) {
        this.state = state;
    }

    public void start() {
        state = state.start();
    }

    public ResponseDto progress(Command command) {
        try {
            state = command.changeChessState(state);
        } catch (IllegalArgumentException ex) {
            return new ResponseDto(400, ex.getMessage(), state.isGameOver());
        }

        return new ResponseDto(200, "", state.isGameOver());
    }

    public void restart() {
        state = new Ready(new BoardInitializer().init());
        start();
    }

    public void save() throws SQLException {
        final BoardDto boardDto = BoardDto.of(state);
        final BoardDao boardDao = new BoardDao();
        int boardId = boardDao.save(boardDto.getTurn(), MysqlConnector.connect());

        savePieces(boardDto, boardId);
    }

    private void savePieces(final BoardDto boardDto, final int boardId) {
        final List<List<PieceDto>> cells = boardDto.getCells();

        for (List<PieceDto> piecesInRank : cells) {
            savePiece(piecesInRank, boardId);
        }
    }

    private void savePiece(final List<PieceDto> pieces, final int boardId) {
        final PieceDao pieceDao = new PieceDao();

        for (PieceDto piece : pieces) {
            final PositionDto position = piece.getPosition();
            final int rank = position.getRank();
            final int file = position.getFile();
            final String symbol = piece.getSymbol();
            final String background = piece.getBackground();


            pieceDao.save(rank, file, symbol, background, boardId, MysqlConnector.connect());
        }
    }

    public State state() {
        return state;
    }
}
