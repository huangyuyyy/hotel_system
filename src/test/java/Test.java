import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Test {
    public static void main(String[] args) {
        Locale localeCN = Locale.SIMPLIFIED_CHINESE;
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", localeCN);
        String today = sdf.format(new Date());
        System.out.println(today);
    }
}
