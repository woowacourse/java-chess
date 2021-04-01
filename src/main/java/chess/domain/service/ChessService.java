package chess.domain.service;

import chess.domain.ChessResult;
import chess.domain.board.Board;
import chess.domain.dao.BoardDao;
import chess.domain.dao.PieceDao;
import chess.domain.dto.BoardDto;
import chess.domain.dto.PieceDto;
import chess.domain.dto.request.MoveRequest;
import chess.domain.dto.response.BoardAndPieceDto;
import chess.domain.dto.response.Response;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessService {

    private final PieceDao pieceDao;
    private final BoardDao boardDao;
    private ChessGame chessGame;

    public ChessService() {
        pieceDao = new PieceDao();
        boardDao = new BoardDao();
    }

    public void init() throws SQLException {
        if (pieceDao.load() == null) {
            pieceDao.save(new Board().unwrap());
        }
        if (boardDao.load() == null) {
            final String name = chessGame.nowTurn().teamName();
            boardDao.save(new BoardDto(name, chessGame.isGameOver()));
        }
        final Map<Position, Piece> chessBoard = pieceDao.load();
        final BoardDto boardDto = boardDao.load();
        chessGame = new ChessGame(new Board(chessBoard), boardDto.team(), boardDto.isGameOver());
    }

    public Response move(final MoveRequest moveRequest) {
        try {
            final Position source = getPositionByCommands(moveRequest.source().split(""));
            final Position target = getPositionByCommands(moveRequest.target().split(""));
            chessGame.move(source, target);

            if (chessGame.isKingDead()) {
                chessGame.changeGameOver();
            }

            final Map<Position, Piece> chessBoard = chessGame.board().unwrap();
            pieceDao.savePiece(source, chessBoard.get(source));
            pieceDao.savePiece(target, chessBoard.get(target));

            chessGame.nextTurn();
            boardDao.save(new BoardDto(chessGame.nowTurn().teamName(), false));
            return new Response("200", "성공");
        } catch (UnsupportedOperationException | IllegalArgumentException | SQLException e) {
            return new Response("401", e.getMessage());
        }
    }

    public Response end() throws SQLException {
        if (chessGame.isGameOver()) {
            boardDao.save(new BoardDto(chessGame.nowTurn().teamName(), true));
            return new Response("212", "게임 종료");
        }
        return new Response("200", "게임 진행중");
    }

    public ChessResult chessResult() {
        return new ChessResult(chessGame.board());
    }

    public void restart() throws SQLException {
        chessGame = new ChessGame();
        pieceDao.deleteAll();
        boardDao.deleteAll();
    }

    private Position getPositionByCommands(final String[] commands) {
        return new Position(commands[0], commands[1]);
    }

    public Response start() {
        final BoardDto boardDto = new BoardDto(chessGame.nowTurn().teamName(), chessGame.isGameOver());
        final Map<Position, Piece> chessBoard = chessGame.board().unwrap();
        final List<PieceDto> pieceDtos = new ArrayList<>();
        chessBoard.forEach((position, piece) -> {
            final String positionValue = position.horizontal().symbol() + position.vertical().symbol();
            String name = "";
            if (!piece.name().equals(".")) {
                if (piece.team().teamName().equals("Black")) {
                    name = "B" + piece.name();
                } else {
                    name = "W" + piece.name().toUpperCase();
                }
            }
            pieceDtos.add(new PieceDto(positionValue, name));
        });
        return new Response("200", "성공", new BoardAndPieceDto(boardDto, pieceDtos));
    }

}
