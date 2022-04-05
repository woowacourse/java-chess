package chess.service;

import chess.dao.GameDao;
import chess.dao.MemberDao;
import chess.domain.ChessGame;
import chess.domain.Member;
import chess.domain.Participant;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.state.WhiteTurn;
import java.util.List;
import java.util.stream.Collectors;

public class GameService {
    private final GameDao gameDao;
    private final MemberDao memberDao;

    public GameService(GameDao gameDao, MemberDao memberDao) {
        this.gameDao = gameDao;
        this.memberDao = memberDao;
    }

    public void createGame(Long whiteId, Long blackId) {
        Member white = memberDao.findById(whiteId).orElseThrow(() -> new RuntimeException("찾는 멤버가 없음!"));
        Member black = memberDao.findById(blackId).orElseThrow(() -> new RuntimeException("찾는 멤버가 없음!"));
        gameDao.save(new ChessGame(new WhiteTurn(new Board(BoardInitializer.initBoard())), new Participant(white, black)));
    }

    public List<ChessGame> findPlayingGames() {
        return gameDao.findAll()
                .stream()
                .filter(game -> !game.isEnd())
                .collect(Collectors.toList());
    }


    public ChessGame findByGameId(Long gameId) {
        return gameDao.findById(gameId)
                .orElseThrow(() -> new RuntimeException("찾는 게임이 존재하지 않습니다."));
    }

    public List<ChessGame> findHistorysByMemberId(Long memberId) {
        return gameDao.findHistorysByMemberId(memberId);
    }


    public void update(ChessGame chessGame) {
        gameDao.update(chessGame);
    }
}
