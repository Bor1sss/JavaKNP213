package itstep.learning.ioc;

import com.google.inject.AbstractModule;
import itstep.learning.filters.*;
import itstep.learning.kdf.KdfService;
import itstep.learning.kdf.PbKdf1Service;
import itstep.learning.services.db.DbService;
import itstep.learning.services.db.MySqlDbService;
import itstep.learning.services.hash.HashService;
import itstep.learning.services.hash.Md5HashService;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(HashService.class).to(Md5HashService.class);
        bind(KdfService.class).to(PbKdf1Service.class);
        bind(DbService.class).to(MySqlDbService.class);
    }
}
