package chess.service;

import chess.dao.ChessGameDao;
import chess.dao.ChessGameDaoImpl;
import chess.dao.dto.ChessGameDto;
import chess.domain.game.ChessGame;
import java.util.List;
import java.util.stream.Collectors;

public class ChessGameService {

    private static final ChessGameDao chessGameDao = new ChessGameDaoImpl();

    public void save(final ChessGame chessGame) {
        chessGameDao.save(chessGame);
    }

    public List<Long> findAllChessGameId() {
        final List<ChessGameDto> chessGames = chessGameDao.findAll();
        return chessGames.stream()
                .map(ChessGameDto::getId)
                .collect(Collectors.toList());
    }

    public ChessGameDto findById(final Long id) {
        return chessGameDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게임아이디 입니다."));
    }

    public ChessGameDto findLatest() {
        return chessGameDao.findLatest()
                .orElseThrow(() -> new IllegalArgumentException("진행중인 게임이 존재하지 않습니다."));
    }
}
