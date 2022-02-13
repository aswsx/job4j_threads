package ref;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 13/02/2022 - 15:34
 */
public class ShareNotSafe {
    private static final Logger LOG = LoggerFactory.getLogger(ShareNotSafe.class);
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("name");
        cache.add(user);
        Thread first = new Thread(
                () -> user.setName("rename")
        );
        first.start();
        first.join();
        LOG.info(cache.findById(1).getName());
    }
}
