package study;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class StreamSlicingTest {

    private final List<Dish> specialMenu = Arrays.asList(
            new Dish("season fruit", 120),
            new Dish("prawns", 300),
            new Dish("rice", 350),
            new Dish("chicken", 400),
            new Dish("french fries", 530));

    private final List<Dish> reversedMenu = Arrays.asList(
            new Dish("french fries", 530),
            new Dish("chicken", 400),
            new Dish("rice", 350),
            new Dish("prawns", 300),
            new Dish("season fruit", 120));

    @Test
    void filter() {
        List<Dish> filteredMenu = specialMenu.stream()
                .filter(dish -> dish.getCalories() < 320)
                .collect(toList());
        filteredMenu.forEach(System.out::println);
    }

    @Test
    void takeWhile은_참인_동안_받다가_거짓이_나오면_즉시_중단() {
        List<Dish> slicedMenu1 = specialMenu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(toList());
        slicedMenu1.forEach(System.out::println);
        assertThat(slicedMenu1.size()).isEqualTo(2);
    }

    @Test
    void takeWhile_reversed_처음부터_거짓이므로_즉시_중단() {
        List<Dish> reversedMenu1 = reversedMenu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(toList());
        reversedMenu1.forEach(System.out::println);
        assertThat(reversedMenu1.size()).isEqualTo(0);
    }

    @Test
    void dropWhile은_참인_동안_무시하다가_최초로_거짓인_시점부터_싹_다_받기() {
        List<Dish> slicedMenu2 = specialMenu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(toList());
        slicedMenu2.forEach(System.out::println);
        assertThat(slicedMenu2.size()).isEqualTo(3);
    }

    @Test
    void dropWhile_reversed_처음부터_거짓이므로_싹_다_받기__나중에_참이_나와도_무조건_받기() {
        List<Dish> reversedMenu2 = reversedMenu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(toList());
        reversedMenu2.forEach(System.out::println);
        assertThat(reversedMenu2.size()).isEqualTo(5);
    }

    static class Dish {

        private final String name;
        private final int calories;

        public Dish(String name, int calories) {
            this.name = name;
            this.calories = calories;
        }

        public int getCalories() {
            return calories;
        }

        @Override
        public String toString() {
            return "Dish{" + "name='" + name + '\'' + ", calories=" + calories + '}';
        }
    }
}