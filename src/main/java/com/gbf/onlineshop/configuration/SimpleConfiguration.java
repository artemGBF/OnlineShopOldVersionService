package com.gbf.onlineshop.configuration;

import com.gbf.onlineshop.FakeProgramRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleConfiguration {

    @Bean
    public FakeProgramRunner fakeProgramRunner(){
        return new FakeProgramRunner("strochka");
    }

}
