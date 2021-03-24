package br.com.indtextbr.services.sigoapierp.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.context.annotation.Bean;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.cloud.openfeign.support.SortJacksonModule;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignDecodeConfiguration {
	@Bean
    public Module PageJacksonModule() {
        return new PageJacksonModule();
    }
	
	@Bean
	public ObjectMapper getObjectMapper() {
		 return new ObjectMapper()
			        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
			        .setPropertyNamingStrategy(SnakeCaseStrategy.SNAKE_CASE)
			        .findAndRegisterModules()
			        .registerModule(new JavaTimeModule())
			        .registerModule(new PageJacksonModule())
			        .registerModule(new SortJacksonModule());
	}
}
