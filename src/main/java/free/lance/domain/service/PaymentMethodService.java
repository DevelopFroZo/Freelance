package free.lance.domain.service;

import free.lance.domain.model.PaymentMethod;
import free.lance.domain.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService{
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethod> findAll(){
        return this.paymentMethodRepository.findAll();
    }
}
