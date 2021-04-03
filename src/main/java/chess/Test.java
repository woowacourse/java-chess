package chess;

import chess.domain.CommandAsString;
import chess.domain.game.Game;
import chess.domain.game.state.GameState;
import chess.domain.game.state.InitialState;
import chess.dto.GameStateDto;
import com.google.gson.Gson;

public class Test {

    public static void main(String[] args) {
        Gson gson = new Gson();
//        GsonBuilder builder = new GsonBuilder();
//        builder.registerTypeAdapter(PieceStrategy.class, new InterfaceAdapter());
//        Gson gson = builder.create();
        Game game = new Game(new InitialState());
        CommandAsString command = new CommandAsString("start");
        game = game.execute(command);
        game = game.execute(new CommandAsString("move a2 a4"));
        GameState currentState = game.getState();
        GameStateDto gameStateDto = new GameStateDto(currentState);
        String data = gson.toJson(gameStateDto);
//        String data = gson.toJson(currentState);
        System.out.println(data);
    }
}