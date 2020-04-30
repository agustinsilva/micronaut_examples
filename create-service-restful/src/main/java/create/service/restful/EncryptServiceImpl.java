package create.service.restful;

import javax.inject.Singleton;

@Singleton
public class EncryptServiceImpl implements EncryptService {

    @Override
    public EncryptDTO encrypt(final String text){
        return new EncryptDTO(new StringBuilder(text).reverse().toString());
    }
}
