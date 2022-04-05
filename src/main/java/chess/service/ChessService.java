package chess.service;

import chess.domain.dto.ResponseDto;
import chess.domain.game.board.ChessBoard;
import chess.domain.game.board.ChessBoardFactory;
import chess.domain.piece.ChessPiece;
import chess.domain.position.Position;

import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {

    private ChessBoard chessBoard = null;

    public void start(){
        chessBoard = ChessBoardFactory.initBoard();
        chessBoard.start();
    }

    public void end(){
        chessBoard.end();
    }

    public String move(String source, String target){
        try{
            if(chessBoard.isPlaying()){
                chessBoard.move(new Position(source), new Position(target));
            }
        }catch (IllegalArgumentException e){
            return new ResponseDto(500, e.getMessage()).toString();
        }
        return new ResponseDto(200, null).toString();
    }

    public Map<String, Double> status(){
        return chessBoard.calculateScore().entrySet().stream()
                .collect(Collectors.toMap(m -> m.getKey().toString(), Map.Entry::getValue));
    }

    public String findWinner() {
        return chessBoard.decideWinner().name();
    }

    public boolean isEnd(){
        return chessBoard.isEnd();
    }

    public Map<String, ChessPiece> getCurrentBoard() {
        return chessBoard.convertToMap();
    }
}
