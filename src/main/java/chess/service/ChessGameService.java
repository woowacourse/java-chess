package chess.service;

import chess.dao.BoardDao;
import chess.dao.ChessGameDao;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.GameSwitch;
import chess.domain.game.Turn;
import chess.domain.piece.Team;
import chess.dto.BoardDto;
import chess.dto.ChessGameExceptBoardDto;
import java.util.ArrayList;
import java.util.List;

public class ChessGameService {

    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;

    private final ChessGameDao chessGameDao = new ChessGameDao();
    private final BoardDao boardDao = new BoardDao();

    public void createChessGame(final String name) {
        ChessGame chessGame = ChessGame.createBasic(name);
        chessGameDao.delete(name);
        chessGameDao.save(new ChessGameExceptBoardDto(chessGame));
        boardDao.delete(name);
        boardDao.save(name, new BoardDto(chessGame.getCurrentBoard()));
    }

    public void saveChessGame(final ChessGame chessGame) {
        String name = chessGame.getName();
        chessGameDao.delete(name);
        chessGameDao.save(new ChessGameExceptBoardDto(chessGame));
        boardDao.delete(name);
        boardDao.save(name, new BoardDto(chessGame.getCurrentBoard()));
    }

    public ChessGame loadChessGame(final String name) {
        ChessGameExceptBoardDto dto = chessGameDao.load(name);
        BoardDto boardDto = boardDao.load(name);
        return new ChessGame(
                name,
                new Board(boardDto.getBoard()),
                new GameSwitch(dto.isOn()),
                new Turn(Team.of(dto.getTeamValueOfTurn()))
        );
    }

    public List<ChessGame> loadAllChessGames() {
        List<ChessGame> chessGames = new ArrayList<>();
                List<ChessGameExceptBoardDto> chessGameExceptBoardDtos = chessGameDao.loadAll();
        for (ChessGameExceptBoardDto dto : chessGameExceptBoardDtos) {
            ChessGame chessGame = new ChessGame(
                    dto.getName(),
                    new Board(boardDao.load(dto.getName()).getBoard()),
                    new GameSwitch(dto.isOn()),
                    new Turn(Team.of(dto.getTeamValueOfTurn()))
            );
            chessGames.add(chessGame);
        }
        return chessGames;
    }

    public void deleteChessGame(final String name) {
        chessGameDao.delete(name);
        boardDao.delete(name);
    }

    public void movePiece(final String chessGameName, final String rawSource, final String rawTarget) {
        ChessGame chessGame = loadChessGame(chessGameName);
        chessGame.move(
                rawSource.charAt(COLUMN_INDEX), Character.getNumericValue(rawSource.charAt(ROW_INDEX)),
                rawTarget.charAt(COLUMN_INDEX), Character.getNumericValue(rawTarget.charAt(ROW_INDEX))
        );
        saveChessGame(chessGame);
    }
}
