package chess.service;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.dao.entity.GameEntity;
import chess.dao.entity.PieceEntity;
import chess.domain.board.*;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.Piece;
import chess.domain.pieces.PieceFactory;
import chess.domain.pieces.component.Team;
import chess.domain.pieces.component.Type;
import chess.domain.state.*;

import java.util.HashMap;
import java.util.Map;

public class ChessGame {

    private Board board;
    private State state;
    private final PieceDao pieceDao;
    private final GameDao gameDao;

    public ChessGame() {
        pieceDao = new PieceDao();
        gameDao = new GameDao();
        state = new Ready();
    }

    public void start() {
        state.checkStartState();
        if (pieceDao.hasData()) {
            this.board = Board.create(makeMapFromDataBase());
            initTurnFromDataBase();
            return;
        }
        this.board = Board.create(BoardFactory.create());
        initPieceToDataBase();

        this.state = new WhiteTurn();
        gameDao.addTurn(new GameEntity(1, Team.WHITE.getValue()));
    }

    private void initTurnFromDataBase() {
        if (Team.WHITE.getValue().equals(gameDao.findByGameId(1))) {
            this.state = new WhiteTurn();
            return;
        }
        this.state = new BlackTurn();
    }

    private Map<Position, Piece> makeMapFromDataBase() {
        Map<Position, Piece> maps = new HashMap<>();
        for (File file : File.values()) {
            maps = putMap(maps, file);
        }
        return maps;
    }

    private Map<Position, Piece> putMap(Map<Position, Piece> maps, File file) {
        for (Rank rank : Rank.values()) {
            Position position = new Position(rank, file);
            maps.put(position, pieceDao.findByPosition(position));
        }
        return maps;
    }

    private void initPieceToDataBase() {
        for (Position position : board.getBoard().keySet()) {
            pieceDao.addPiece(
                    new PieceEntity(
                            PieceFactory.from(board.getBoard().get(position)),
                            position.getFile(),
                            position.getRank(),
                            1));
        }
    }

    public Board getBoard() {
        return this.board;
    }

    public Map<Team, Double> calculateScore() {
        Map<Team, Double> result = new HashMap<>();
        result.put(Team.WHITE, board.calculateTeamScore(Team.WHITE));
        result.put(Team.BLACK, board.calculateTeamScore(Team.BLACK));
        return result;
    }

    public boolean isEnd() {
        return this.state.isEnd();
    }

    public void move(Position current, Position target) {
        state.move(() -> {
            checkTeam(current);
            board.movePiece(current, target);
        });

        Piece piece = board.getBoard().get(target);
        pieceDao.updateByPiecePosition(current, new EmptyPiece(Team.NEUTRALITY, Type.NO_PIECE));
        pieceDao.updateByPiecePosition(target, piece);
    }

    private void checkTeam(Position position) {
        Team team = board.getTeam(position);
        if (state.getTurn() != team) {
            throw new IllegalArgumentException("현재 차례가 아닙니다.");
        }
    }

    public void changeTurn() {
        this.state = state.changeState();
        gameDao.updateByTurn(1, Team.of(this.state.getTurn().getValue()).getValue());
    }

    public void changeTurnEnd() {
        this.state = new End();
        gameDao.deleteTable();
        pieceDao.deleteTable();
    }

    public boolean isKingDie() {
        board.kingTeams();
        return board.kingTeams().size() != 2;
    }

    public Team winTeam() {
        return board.kingTeams().get(0);
    }
}
