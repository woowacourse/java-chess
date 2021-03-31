package chess.domain.service;

import chess.domain.ChessResult;
import chess.domain.dto.BoardDto;
import chess.domain.dto.PieceDto;
import chess.domain.dto.request.MoveRequest;
import chess.domain.dto.response.BoardAndPieceDto;
import chess.domain.dto.response.Response;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessService {

    private ChessGame chessGame;

    public ChessService() {
        this.chessGame = new ChessGame();
    }

    public Response move(final MoveRequest moveRequest) {
        try {
            chessGame.move(getPositionByCommands(moveRequest.source().split("")),
                getPositionByCommands(moveRequest.target().split("")));

            if (chessGame.isKingDead()) {
                chessGame.changeGameOver();
            }
            chessGame.nextTurn();
            return new Response("200", "성공");
        } catch (UnsupportedOperationException | IllegalArgumentException e) {
            return new Response("401", e.getMessage());
        }
    }

    public Response end() {
        if (chessGame.isGameOver()) {
            return new Response("212", "게임 종료");
        }
        return new Response("200", "게임 진행중");
    }

    public ChessResult chessResult() {
        return new ChessResult(chessGame.board());
    }

    public void restart() {
        chessGame = new ChessGame();
    }

    private Position getPositionByCommands(final String[] commands) {
        return new Position(commands[0], commands[1]);
    }

    public Response start() {
        final BoardDto boardDto = new BoardDto(0, chessGame.nowTurn().teamName(), chessGame.isGameOver());
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
