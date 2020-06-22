package study;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

class ClassStudy {

    @Test
    void getConstructor() throws NoSuchMethodException {
        Class<Example> c = Example.class;
        Constructor<Example> constructor = Example.class.getConstructor();
        assertThat(constructor).isNotNull();


        constructor = c.getConstructor(int.class);
        assertThat(constructor).isNotNull();

        constructor = c.getConstructor(String.class, BigInteger[].class);
        assertThat(constructor).isNotNull();
    }

    public static class Example {
        public Example() {}

        public Example(int x) {}

        public Example(String s, BigInteger[] integers) {}
    }
}
