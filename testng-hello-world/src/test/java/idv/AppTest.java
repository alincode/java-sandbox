package idv;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest
{
    @Test(groups = "Functional")
    public void testGenerate() throws Exception {
        App app = new App();
        String email = app.generate();
        Assert.assertEquals(email, "feedback@yoursite.com");
    }

    @Test(groups = "Functional")
    public void test1() throws Exception {
        Assert.assertEquals(true, true);
    }

    @Test(groups = "Unit")
    public void test2() throws Exception {
        Assert.assertEquals(true, true);
    }

}
