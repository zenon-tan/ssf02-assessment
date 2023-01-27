package ssf.ssfassessment.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObject;
import ssf.ssfassessment.model.Order;

@Repository
public class PizzaRepo {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void saveToRepo(Order order, JsonObject json) {

        redisTemplate.opsForValue().set(order.getOrderId(), json.toString());
    }
    
}
