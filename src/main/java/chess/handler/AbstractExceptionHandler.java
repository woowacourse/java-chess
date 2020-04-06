package chess.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ExceptionHandler;

public abstract class AbstractExceptionHandler<T extends Exception> implements ExceptionHandler<T> {
    protected static Gson gson = new GsonBuilder().setPrettyPrinting().create();
}
