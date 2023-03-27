package chess.service;

import chess.domain.board.*;
import chess.domain.entity.PieceDao;
import chess.domain.entity.PieceEntity;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.Piece;
import chess.domain.pieces.component.Team;
import chess.domain.pieces.component.Type;
import chess.domain.state.*;

import java.util.HashMap;
import java.util.Map;

public class ChessGame {

    private Board board;
    private State state;
    private PieceDao pieceDao;

    public ChessGame() {
        pieceDao = new PieceDao();
        state = new Ready();
    }

    public void start() {
        if (pieceDao.hasData()) {
            System.out.println("있어");
            Map<Position, Piece> maps = new HashMap<>();
            for (File file : File.values()) {
                for (Rank rank : Rank.values()) {
                    Position position = new Position(rank, file);
                    maps.put(position, pieceDao.findByPosition(position));
                }
            }
            this.board = Board.create(maps);
        }
        if (!pieceDao.hasData()) {
            System.out.println("옶오");
            this.board = Board.create(BoardFactory.create());
            initPiece();
        }
        state.startGame();
        this.state = new WhiteTurn();
    }

    public void initPiece() {
        for (Position position : board.getBoard().keySet()) {
            pieceDao.addPiece(
                    new PieceEntity(
                            board.getBoard().get(position).getType().getName(),
                            position.getFile(),
                            position.getRank(),
                            1));
        }
    }

    public Board getBoard() {
        return board;
    }

    public Map<Team, Double> calculateScore() {
        Map<Team, Double> result = new HashMap<>();
        result.put(Team.WHITE, board.calculateTeamScore(Team.WHITE));
        result.put(Team.BLACK, board.calculateTeamScore(Team.BLACK));
        return result;
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public void move(Position a, Position b) {
        state.move(() -> {
            checkTeam(a);
            board.movePiece(a, b);
        });
        Piece piece = board.getBoard().get(b);
        pieceDao.updateByPiecePosition(a, new EmptyPiece(Team.NEUTRALITY, Type.NO_PIECE));
        pieceDao.updateByPiecePosition(b, piece);
    }

    private void checkTeam(Position a) {
        Team team = board.getTeam(a);
        if (state.getTurn() != team) {
            throw new IllegalArgumentException();
        }
    }

    public void changeTurn() {
        if (state.getTurn() == Team.WHITE) {
            this.state = new BlackTurn();
            return;
        }
        if (state.getTurn() == Team.BLACK) {
            this.state = new WhiteTurn();
        }
    }

    public void changeTurnEnd() {
        this.state = new End();
    }
}
