package temp;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Zeus Feng on 2017/2/27.
 *
 * @author Zeus Feng
 * @date 2017/02/27
 */
public class TempTest {

    @Test
    public void tempTest(){
        //String nowTime = new Timestamp(System.currentTimeMillis()).toString();

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,-1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime=formatter.format(c.getTime());
        //String strStart=mDateTime.substring(0, 16);
        System.out.println(mDateTime);
    }
}
