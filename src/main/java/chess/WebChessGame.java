package chess;

import chess.dao.BoardDao;
import chess.dto.board.BoardInformationDto;
import chess.dao.MysqlConnector;
import chess.dao.PieceDao;
import chess.domain.Board;
import chess.domain.BoardInitializer;
import chess.domain.Symbol;
import chess.domain.command.Command;
import chess.domain.piece.Nothing;
import chess.domain.piece.Piece;
import chess.domain.postion.Position;
import chess.domain.state.Black;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.domain.state.White;
import chess.dto.ResponseDto;
import chess.dto.board.BoardDto;
import chess.dto.board.PieceDto;
import chess.dto.board.PositionDto;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            final int file = position.getFile();
            final int rank = position.getRank();
            final String symbol = piece.getSymbol();
            final String background = piece.getBackground();

            pieceDao.save(file, rank, symbol, background, boardId, MysqlConnector.connect());
        }
    }

    public void findRecentBoard() throws SQLException {
        final BoardDao boardDao = new BoardDao();
        final PieceDao pieceDao = new PieceDao();

        final BoardInformationDto dto = boardDao.findRecentBoard(MysqlConnector.connect());
        final List<PieceDto> pieces = pieceDao.findPieceByBoardId(dto.getId(), MysqlConnector.connect());

        final Board board = makePiecesToBoard(pieces);

        final String turn = dto.getTurn();
        changeState(turn, board);
    }

    private Board makePiecesToBoard(List<PieceDto> pieces) {
        final Map<Position, Piece> cells = new HashMap<>();

        for (PieceDto pieceDto : pieces) {
            addValueInCells(pieceDto, cells);
        }

        return new Board(cells);
    }

    private void addValueInCells(final PieceDto pieceDto, final Map<Position, Piece> cells) {
        final PositionDto positionDto = pieceDto.getPosition();
        final int file = positionDto.getFile();
        final int rank = positionDto.getRank();
        final String symbol = pieceDto.getSymbol();
        final Position position = Position.of(file, rank);
        final Piece piece = Symbol.webSymbolToPiece(symbol);

        if (!(piece instanceof Nothing)) {
            cells.put(position, piece);
        }
    }

    // TODO: 상태 패턴 안으로 넣기
    private void changeState(final String turn, final Board board) {
        if (turn.equals("white")) {
            state = new White(board);
        }

        state = new Black(board);
    }

    public State state() {
        return state;
    }
}
