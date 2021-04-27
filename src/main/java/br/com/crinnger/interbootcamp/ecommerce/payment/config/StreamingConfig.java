package br.com.crinnger.interbootcamp.ecommerce.payment.config;

import br.com.crinnger.interbootcamp.ecommerce.payment.streaming.CheckoutProcessor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
@EnableBinding(value = CheckoutProcessor.class)
public class StreamingConfig {
}
