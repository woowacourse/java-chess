package chess.service;

import chess.domain.board.Board;
import chess.game.ChessGame;
import chess.game.Turn;
import database.dto.ChessGameDto;
import database.BoardDao;
import database.ChessGameDao;
import database.dto.SquareDto;

import java.util.List;
import java.util.stream.Collectors;

public class ChessGameService {

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
        Board board = boardDao.findByGameId(gameId);
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
