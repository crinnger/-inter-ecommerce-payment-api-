package br.com.crinnger.interbootcamp.ecommerce.payment.config;

import br.com.crinnger.interbootcamp.ecommerce.payment.streaming.CheckoutProcessor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(value = CheckoutProcessor.class)
public class StreamingConfig {
}
