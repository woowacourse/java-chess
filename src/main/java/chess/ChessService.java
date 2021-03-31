package chess;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Side;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.exception.ChessException;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChessService {

    static private ChessGame chessGame;

    public void initChessGame() {
        chessGame = new ChessGame(Board.getGamingBoard());
        chessGame.start();
    }

    public Response move(RequestPosition requestPosition){
        String from = requestPosition.from();
        String to = requestPosition.to();
        try {
            chessGame.move(Position.from(from), Position.from(to));
            if(chessGame.isGameSet()) {
                Side side = chessGame.state().winner();
                return new Response("300", side.name(), "게임 종료(" +side.name() +" 승리)");
            }
            return new Response("200", "Succeed", chessGame.side().name());
        }catch (ChessException e){
            return new Response("400", e.getMessage(), chessGame.side().name());
        }
    }

    public Map<String, String> getCurrentBoard() {
        Map<Position, Piece> board = chessGame.board().getBoard();
        Map<String, String> boardDTO = new LinkedHashMap<>();

        for(Position position : board.keySet()) {
            String positionDTO = position.stringPosition();
            String pieceDTO;
            Piece piece = board.get(position);
            if(piece.side() == Side.BLACK) {
                pieceDTO = "B" + piece.getInitial();
            }
            else if(piece.side() == Side.WHITE) {
                pieceDTO = "W" + piece.getInitial().toUpperCase();
            }
            else {
                pieceDTO = ".";
            }
            boardDTO.put(positionDTO, pieceDTO);
        }
        return boardDTO;
    }
}
