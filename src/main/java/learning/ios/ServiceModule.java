package learning.ios;

import com.google.inject.AbstractModule;
import learning.services.HashService;
import learning.services.Md5HashService;


public class ServiceModule  extends AbstractModule {
    @Override
    protected void configure() {
        bind(HashService.class).to(Md5HashService.class);
    }


    /*
    Moдyль peєcтpaції cepвіcів
*/
}
