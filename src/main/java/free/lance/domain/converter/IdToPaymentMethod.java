package free.lance.domain.converter;

import free.lance.domain.model.PaymentMethod;
import free.lance.domain.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class IdToPaymentMethod implements Converter<Long, PaymentMethod>{
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public PaymentMethod convert( Long id ){
        return this.paymentMethodRepository.findOne( id );
    }
}
