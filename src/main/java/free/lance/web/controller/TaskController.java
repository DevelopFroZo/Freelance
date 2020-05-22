package free.lance.web.controller;

import free.lance.domain.model.Category;
import free.lance.domain.model.PaymentMethod;
import free.lance.domain.model.Task;
import free.lance.domain.service.CategoryService;
import free.lance.domain.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping( value = "/tasks" )
public class TaskController{
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @RequestMapping( value = "/add" )
    public String add( Model model ){
        Task task = new Task();
        List<Category> categories = this.categoryService.findAll();
        List<PaymentMethod> paymentMethods = this.paymentMethodService.findAll();

        model.addAttribute( "task", task );
        model.addAttribute( "categories", categories );
        model.addAttribute( "paymentMethods", paymentMethods );

        return "tasks/add";
    }
}
