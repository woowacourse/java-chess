package chess.service;

import chess.domain.dto.ResponseDto;
import chess.domain.game.board.ChessBoard;
import chess.domain.game.board.ChessBoardFactory;
import chess.domain.piece.ChessPiece;
import chess.domain.position.Position;

import java.util.Map;

public class ChessService {

    private ChessBoard chessBoard = null;

    public void start(){
        chessBoard = ChessBoardFactory.initBoard();
        chessBoard.start();
    }

    public Map<String, ChessPiece> getCurrentBoard() {
        return chessBoard.convertToMap();
    }

    public String move(String source, String target){
        try{
            chessBoard.move(new Position(source), new Position(target));
        }catch (IllegalArgumentException e){
            return new ResponseDto(500, e.getMessage()).toString();
        }
        return new ResponseDto(200, null).toString();
    }
}
