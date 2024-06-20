import java.util.concurrent.atomic.AtomicInteger;

public class PageViewCounter {
    private static final AtomicInteger viewCount = new AtomicInteger(0);

    public static void main(String[] args) {
        // Симулируем просмотр страницы (вызов этого метода при каждом просмотре)
        incrementPageView();
        incrementPageView();
        incrementPageView();

        // Получаем текущее количество просмотров
        int currentViews = viewCount.get();
        System.out.println("Количество просмотров страницы: " + currentViews);
    }

    // Метод для увеличения счетчика просмотров
    private static void incrementPageView() {
        viewCount.incrementAndGet();
    }
}

