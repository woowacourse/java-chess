package chess.repository;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.game.ChessGame;
import chess.game.Turn;
import chess.database.dto.ChessGameDto;
import chess.database.BoardDao;
import chess.database.ChessGameDao;
import chess.database.dto.SquareDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGameRepository {

    private final ChessGameDao chessGameDao = new ChessGameDao();
    private final BoardDao boardDao = new BoardDao();

    public void save(ChessGame chessGame, String status) {
        ChessGameDto chessGameDto = new ChessGameDto(chessGame.getId(), status, chessGame.getCurrentTeam().name());
        int saveId = chessGameDao.save(chessGameDto);
        chessGame.setId(saveId);

        List<SquareDto> squareDtos = convertSquareDto(chessGame.getChessBoard());
        boardDao.save(squareDtos, saveId);
    }

    public void update(ChessGame chessGame, String status) {
        ChessGameDto chessGameDto = new ChessGameDto(chessGame.getId(), status, chessGame.getCurrentTeam().name());
        chessGameDao.update(chessGameDto);

        List<SquareDto> squareDtos = convertSquareDto(chessGame.getChessBoard());
        boardDao.update(squareDtos, chessGame.getId());
    }

    public ChessGame findById(int gameId) {
        Turn turn = chessGameDao.findTurnById(gameId);
        Map<Position, Piece> squares = boardDao.findByGameId(gameId);

        Board board = new Board(squares);
        ChessGame chessGame = new ChessGame(board, turn);
        chessGame.setId(gameId);
        return chessGame;
    }

    public List<Integer> findAllGameIds() {
        return chessGameDao.findContinuingGameIds();
    }

    private static List<SquareDto> convertSquareDto(Board board) {
        return board.getBoard().entrySet().stream()
                .map(entry -> SquareDto.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
