import com.sbs.backend.config.AppConfiguration;
import com.sbs.backend.test.MyApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class TestApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = null;
        try {
            context = new AnnotationConfigApplicationContext(AppConfiguration.class);
            MyApplication application = context.getBean(MyApplication.class);
            application.performDbTasks();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            context.close();
        }
    }
}