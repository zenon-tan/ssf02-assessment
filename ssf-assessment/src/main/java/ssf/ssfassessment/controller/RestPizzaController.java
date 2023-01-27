package ssf.ssfassessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import ssf.ssfassessment.repo.PizzaRepo;

@RestController
@RequestMapping(path = "/order")
public class RestPizzaController {

    @Autowired
    PizzaRepo pRepo;

    @GetMapping(path = "{orderId}")
    public ResponseEntity<String> showOrderById(@PathVariable String orderId) {

        String result = pRepo.findFromRepo(orderId);

        if(result != null) {

            return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(result);

        }

        String notFoundJson = Json.createObjectBuilder()
        .add("message", "Order <%s> not found".formatted(orderId))
        .build().toString();


        return ResponseEntity
        .status((HttpStatus.NOT_FOUND))
        .contentType(MediaType.APPLICATION_JSON)
        .body(notFoundJson);

    }


    
}
