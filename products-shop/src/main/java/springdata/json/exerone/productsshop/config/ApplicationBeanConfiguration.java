package springdata.json.exerone.productsshop.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springdata.json.exerone.productsshop.utils.ValidatorUtil;
import springdata.json.exerone.productsshop.utils.ValidatorUtilImpl;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public ValidatorUtil validatorUtil(){
        return new ValidatorUtilImpl();
    }


}
