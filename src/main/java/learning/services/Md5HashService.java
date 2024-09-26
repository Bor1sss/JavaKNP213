package learning.services;

import com.google.inject.Singleton;

@Singleton
public class Md5HashService implements HashService {

    @Override
    public String hash(String string) {
        return "Md5HashService";
    }
}
