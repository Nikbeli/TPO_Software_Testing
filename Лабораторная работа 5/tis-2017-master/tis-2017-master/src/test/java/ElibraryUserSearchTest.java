import context.ChromeContext;
import context.Context;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;
import page.ElibraryAuthorPage;


public class ElibraryUserSearchTest {
    private final static String APP_URL = "https://www.elibrary.ru/authors.asp";

    private static Context context;

    @BeforeAll
    public static void setup() {
        context = new ChromeContext();
        context.start();
        context.getDriver().manage().window().setSize(new Dimension(1600, 900));
    }

    @AfterAll
    public static void quit() {
        context.close();
    }

    @Test
    public void testSearchAuthor() {
        context.getDriver().get(APP_URL);
        String authorName = "Романов Антон Алексеевич";
        String town = "Ульяновск";

        ElibraryAuthorPage page = PageFactory.initElements(context.getDriver(), ElibraryAuthorPage.class);
        page.findPublications(authorName, town).forEach(System.out::println);
    }
}
