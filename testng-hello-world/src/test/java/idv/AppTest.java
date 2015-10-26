package idv;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest
{
    @Test
    public void testGenerate() throws Exception {
        App app = new App();
        String email = app.generate();
        Assert.assertEquals(email, "feedback@yoursite.com");
    }

}
