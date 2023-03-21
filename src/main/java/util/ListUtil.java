package util;

import java.util.List;

public class ListUtil {

    public static <T> T getLastElement(List<T> list) {
        return list.get(list.size() - 1);
    }
}
