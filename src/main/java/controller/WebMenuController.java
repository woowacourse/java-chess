package controller;

import domain.ChessGame;
import domain.menu.Menu;
import domain.piece.objects.Piece;
import domain.piece.objects.PieceFactory;
import domain.piece.position.Position;
import domain.state.Running;
import domain.state.Wait;
import dto.PieceDto;
import dto.PiecesDto;
import dto.ResultDto;
import dto.StatusDto;

import java.util.HashMap;
import java.util.Map;

public class WebMenuController {
    private ChessGame game;

    public WebMenuController() {
        game = new ChessGame(new Wait(PieceFactory.createPieces()));
    }

    public WebMenuController(PiecesDto piecesDto) {
        Map<Position, Piece> data = new HashMap<>();
        for (PieceDto pieceDto : piecesDto.getPieces()) {
            Position position = Position.of(pieceDto.getPosition());
            Piece piece = PieceFactory.findPiece(pieceDto.getPieceName());
            data.put(position, piece);
        }
        game = new ChessGame(new Running(data, piecesDto.isTurn()));
    }

    private ResultDto startMenu(String command, ChessGame game, Menu menu) {
        try {
            return new ResultDto(menu.executeWebMenu(command, game), "");
        } catch (RuntimeException e) {
            return new ResultDto(null, e.getMessage());
        }
    }

    public ResultDto getResultDto() {
        return new ResultDto(new PiecesDto(game.getBoard().getPieceMap(),
                new StatusDto(game.blackScore(), game.whiteScore()),
                game.isEnd(), game.getTurn()), "");
    }

    public ResultDto run(String command) {
        String menuButton = command.split(" ")[0];
        Menu menu = Menu.findMenu(menuButton);
        return startMenu(command, game, menu);
    }
}
