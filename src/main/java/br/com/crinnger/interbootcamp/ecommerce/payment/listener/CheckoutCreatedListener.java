package br.com.crinnger.interbootcamp.ecommerce.payment.listener;

import br.com.crinnger.interbootcamp.ecommerce.payment.streaming.CheckoutProcessor;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import br.com.crinnger.interbootcamp.ecommerce.checkout.event.CheckoutCreatedEvent;
import br.com.crinnger.interbootcamp.ecommerce.payment.event.PaymentCreatedEvent;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CheckoutCreatedListener {

    private final CheckoutProcessor checkoutProcessor;


    //public PaymentCreatedEvent handler(CheckoutCreatedEvent event){
    //@KafkaListener(topics = "streaming.ecommerce.checkout.created-value", groupId = "inter-ecommerce")
    @StreamListener(CheckoutProcessor.INPUT)
    @SendTo(CheckoutProcessor.OUTPUT)
    public PaymentCreatedEvent listenWithHeaders(
            GenericMessage<GenericRecord> message) {
        Gson gson = new Gson();
        CheckoutCreatedEvent event = gson.fromJson(message.getPayload().toString(),CheckoutCreatedEvent.class);
        //processar pagamento gateway
        // salvar dados do pagamento
        // evoar evento do pagamento
        PaymentCreatedEvent paymentCreatedEvent = PaymentCreatedEvent.newBuilder()
                .setCheckoutCode(event.getCheckoutCode())
                .setCheckoutStatus("APPROVED")
                .setPaymentCode(UUID.randomUUID().toString())
                .build();
        //checkoutProcessor.output().send(MessageBuilder.withPayload(paymentCreatedEvent).build());
        return  paymentCreatedEvent;
    }
}
