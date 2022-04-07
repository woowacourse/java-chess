package chess.service;

import chess.dao.GameDao;
import chess.dao.MemberDao;
import chess.domain.ChessGame;
import chess.domain.Member;
import chess.domain.Participant;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.state.WhiteTurn;
import chess.dto.GameResultDTO;
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
        gameDao.save(
                new ChessGame(new WhiteTurn(new Board(BoardInitializer.initBoard())), new Participant(white, black)));
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

    public List<GameResultDTO> findHistorysByMemberId(Long memberId) {
        List<ChessGame> games = gameDao.findHistorysByMemberId(memberId);
        return games.stream()
                .map(game -> toGameResultDTO(game, memberId))
                .collect(Collectors.toList());
    }

    private GameResultDTO toGameResultDTO(ChessGame game, Long memberId) {
        String winResult = findWinResult(game, memberId);
        String enemyName = findEnemyName(game, memberId);
        String team = findTeam(game, memberId);
        double myScore = findMyScore(game, memberId);
        double enemyScore = findEnemyScore(game, memberId);
        return new GameResultDTO(winResult, enemyName, team, myScore, enemyScore);
    }

    private static String findWinResult(ChessGame game, Long memberId) {
        if (game.isTerminated()) {
            return "강제종료";
        }
        Long winnerId = game.getWinnerId().orElseThrow(() -> new RuntimeException("승자 로직 체크"));
        if (winnerId.equals(memberId)) {
            return "승";
        }
        return "패";
    }

    private static String findEnemyName(ChessGame game, Long memberId) {
        if (game.getParticipant().getBlackId().equals(memberId)) {
            return game.getParticipant().getWhiteName();
        }
        return game.getParticipant().getBlackName();
    }

    private static String findTeam(ChessGame game, Long memberId) {
        if (game.getParticipant().getBlackId().equals(memberId)) {
            return "흑";
        }
        return "백";
    }

    private static double findMyScore(ChessGame game, Long memberId) {
        if (game.getParticipant().getBlackId().equals(memberId)) {
            return game.getBlackScore();
        }
        return game.getWhiteScore();
    }

    private static double findEnemyScore(ChessGame game, Long memberId) {
        if (game.getParticipant().getBlackId().equals(memberId)) {
            return game.getWhiteScore();
        }
        return game.getBlackScore();
    }


    public void update(ChessGame chessGame) {
        gameDao.update(chessGame);
    }

    public void move(Long gameId, String start, String target) {
        ChessGame chessGame = findByGameId(gameId);
        chessGame.move(start, target);
        update(chessGame);
    }

    public void terminate(Long gameId) {
        ChessGame chessGame = findByGameId(gameId);
        chessGame.terminate();
        update(chessGame);
    }
}
