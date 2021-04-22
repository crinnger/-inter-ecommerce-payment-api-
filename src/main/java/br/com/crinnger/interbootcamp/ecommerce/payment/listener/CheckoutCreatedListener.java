package br.com.crinnger.interbootcamp.ecommerce.payment.listener;

import br.com.crinnger.interbootcamp.ecommerce.payment.streaming.CheckoutProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.springframework.cloud.stream.annotation.StreamListener;
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

    @StreamListener(CheckoutProcessor.INPUT)
    public void handler(GenericMessage<GenericRecord> event){
        //CheckoutCreatedEvent checkoutCreatedEvent= CheckoutCreatedEvent.
        //processar pagamento gateway
        // salvar dados do pagamento
        // evoar evento do pagamento
        PaymentCreatedEvent paymentCreatedEvent = PaymentCreatedEvent.newBuilder()
                .setCheckoutCode(UUID.randomUUID().toString())
                .setCheckoutStatus("APPROVED")
                .setPaymentCode(UUID.randomUUID().toString())
                .build();
        checkoutProcessor.output().send(MessageBuilder.withPayload(paymentCreatedEvent).build());
    }
}
